# JavaVulnLab

> ⚠️ Code volontairement vulnérable, à des fins de test. Ne pas déployer.

Application de test pour Tiresias. Elle couvre ce que l'OWASP BenchmarkJava ne couvre pas : Spring MVC, JAX-RS, JPA, Hibernate, Spring JDBC, Servlet `jakarta`, et les nettoyages (sanitizers).

Le code ne compile pas tel quel, et c'est voulu : Joern analyse les sources seules, il n'a pas besoin des dépendances.

## Règles

- Un cas = un fichier `.java` = une ligne dans `expected.csv`.
- Le nom du cas est le nom du fichier qui contient le sink. Il commence par la vulnérabilité (`Sqli`, `Cmdi`, `Path`, `Xss`) pour rester unique.
- Les cas sont rangés par vulnérabilité, puis par technologie : `vulnlab/<vulnérabilité>/<techno>/`.
- `vulnlab/common/` contient les classes utilitaires, qui ne sont pas des cas.

## Cas

### `sqli` : injection SQL (17 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `SqliSpringImplicitParam` | oui | paramètre `String` sans annotation (`@RequestParam` implicite) |
| `SqliSpringImplicitModelAttribute` | oui | objet sans annotation (`@ModelAttribute` implicite), lu par un getter |
| `SqliSpringWebRequest` | oui | `WebRequest.getParameter` |
| `SqliSpringNamedParameterJdbc` | oui | sink `NamedParameterJdbcTemplate` |
| `SqliSpringJdbcClient` | oui | sink `JdbcClient.sql` (Spring 6.1) |
| `SqliSpringJpaNative` | oui | sink `EntityManager.createNativeQuery` |
| `SqliSpringPathVariableLong` | non | paramètre `Long` |
| `SqliSpringPreparedQuery` | non | requête paramétrée (`?`) |
| `SqliSpringRequestAttribute` | non | `@RequestAttribute` : donnée côté serveur |
| `SqliSpringParseInt` | non | nettoyage `Integer.parseInt` |
| `SqliJaxrsQueryParam` | oui | `@QueryParam` → JPA `createQuery` |
| `SqliJaxrsPathParam` | oui | `@PathParam` → Hibernate `Session.createQuery` |
| `SqliJaxrsEntityBody` | oui | corps de la requête (paramètre non annoté) |
| `SqliJaxrsUriInfo` | oui | `@Context UriInfo` → `getQueryParameters().getFirst(...)` |
| `SqliJaxrsBeanParam` | oui | `@BeanParam`, lu par un getter |
| `SqliJaxrsSetParameter` | non | JPQL paramétré (`:name` + `setParameter`) |
| `SqliServletJakarta` | oui | `jakarta.servlet` → JDBC `Statement.execute` |

### `cmdi` : injection de commandes (4 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `CmdiSpringProcessBuilder` | oui | `new ProcessBuilder("sh", "-c", ... + host)` |
| `CmdiJaxrsRuntimeExec` | oui | `Runtime.exec(new String[] {...})` avec un élément contrôlé |
| `CmdiSpringEncodeForOS` | non | nettoyage ESAPI `encodeForOS` |
| `CmdiSpringParseInt` | non | nettoyage `Integer.parseInt` |

### `pathtraver` : path traversal (4 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `PathSpringFile` | oui | `new File(BASE_DIR, name)` |
| `PathJaxrsNio` | oui | `Paths.get(BASE_DIR, name)` |
| `PathSpringFilenameUtils` | non | nettoyage `FilenameUtils.getName` |
| `PathSpringEsapiValidator` | non | nettoyage ESAPI `getValidFileName` |

### `xss` : cross-site scripting (6 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `XssServletWriter` | oui | `response.getWriter().println(... + name)` |
| `XssServletPrintWriterVar` | oui | `PrintWriter out = response.getWriter(); out.print(...)` |
| `XssSpringResponseWriter` | oui | handler Spring qui écrit dans `HttpServletResponse` |
| `XssSpringHtmlUtils` | non | nettoyage Spring `HtmlUtils.htmlEscape` |
| `XssServletOwaspEncoder` | non | nettoyage OWASP Java Encoder `Encode.forHtml` |
| `XssServletFileWriter` | non | `PrintWriter` vers un fichier de log : pas la réponse HTTP |

## Mesure

Depuis la racine de Tiresias :

```bash
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule sqli
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule cmdi
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule pathtraver
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule xss
```
