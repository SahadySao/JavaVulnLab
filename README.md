# JavaVulnLab

> ⚠️ Code volontairement vulnérable, à des fins de test. Ne pas déployer.

Application de test pour Tiresias. Elle couvre ce que l'OWASP BenchmarkJava ne couvre pas : Spring MVC, JAX-RS, JPA, Hibernate, Spring JDBC, Servlet `jakarta`, et les nettoyages (sanitizers).

Le code ne compile pas tel quel, et c'est voulu : Joern analyse les sources seules, il n'a pas besoin des dépendances.

## Règles

- Un cas = un fichier `.java` = une ligne dans `expected.csv`.
- Le nom du cas est le nom du fichier qui contient le sink. Il commence par la vulnérabilité (`Sqli`, `Cmdi`, `Path`, `Xss`, `Xxe`, `Ldapi`) pour rester unique.
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

### `xss` : cross-site scripting (7 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `XssServletWriter` | oui | `response.getWriter().println(... + name)` |
| `XssServletPrintWriterVar` | oui | `PrintWriter out = response.getWriter(); out.print(...)` |
| `XssSpringResponseWriter` | oui | handler Spring qui écrit dans `HttpServletResponse` |
| `XssSpringHtmlUtils` | non | nettoyage Spring `HtmlUtils.htmlEscape` |
| `XssServletOwaspEncoder` | non | nettoyage OWASP Java Encoder `Encode.forHtml` |
| `XssServletFileWriter` | non | `PrintWriter` vers un fichier de log : pas la réponse HTTP |
| `XssServletSplit` | oui | `split(" ")[0]` : Joern perd la donnée, retrouvée par un pont (`Flows.scala`) |

### `xxe` : XML External Entity (22 cas)

BenchmarkJava ne contient aucun cas XXE : ce labo est la seule mesure de cette règle.

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `XxeServletDocumentBuilder` | oui | DOM (`DocumentBuilderFactory`) par défaut |
| `XxeServletInlineFactory` | oui | `newInstance().newDocumentBuilder().parse(...)` d'un seul bloc |
| `XxeServletTransformer` | oui | XSLT (`Transformer.transform`) par défaut |
| `XxeSpringSaxParser` | oui | SAX (`SAXParser.parse`) par défaut |
| `XxeSpringXmlInputFactory` | oui | StAX (`XMLInputFactory`) par défaut, fichier envoyé (`MultipartFile`) |
| `XxeSpringDom4j` | oui | dom4j (`SAXReader`) par défaut |
| `XxeSpringJdom2` | oui | JDOM2 (`SAXBuilder`) par défaut |
| `XxeSpringXPathInputSource` | oui | `XPath.evaluate(expr, InputSource)` : parseur interne non protégeable |
| `XxeSpringSchemaValidator` | oui | `Validator.validate` par défaut |
| `XxeSpringFeatureFalse` | oui | `disallow-doctype-decl` à **false** : la valeur compte |
| `XxeSpringExpandEntitiesOnly` | oui | `setExpandEntityReferences(false)` seul : insuffisant selon l'OWASP |
| `XxeSpringOnlyGeneralEntities` | oui | entités générales désactivées, mais pas les entités paramètres |
| `XxeServletDisallowDoctype` | non | `disallow-doctype-decl` à true |
| `XxeServletConstantXml` | non | parseur non protégé, mais XML constant |
| `XxeSpringSecureProcessing` | non | `FEATURE_SECURE_PROCESSING` à true |
| `XxeSpringAccessExternalDtd` | non | `ACCESS_EXTERNAL_DTD` à `""` |
| `XxeSpringStaxNoDtd` | non | StAX : `SUPPORT_DTD` à false |
| `XxeSpringExternalEntitiesOff` | non | SAX : entités générales **et** paramètres désactivées |
| `XxeSpringDom4jSafe` | non | dom4j : `disallow-doctype-decl` à true |
| `XxeSpringFieldFactory` | non | fabrique protégée dans le constructeur (champ) |
| `XxeSpringHelperFactory` | non | fabrique protégée dans une méthode utilitaire |
| `XxeSpringSchemaSecure` | non | `SchemaFactory` avec `FEATURE_SECURE_PROCESSING` |

### `ldapi` : injection LDAP (8 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `LdapiServletDirContext` | oui | JNDI `DirContext.search` avec un filtre concaténé |
| `LdapiServletFilterArgs` | non | JNDI, filtre paramétré `{0}` : valeur échappée par l'API |
| `LdapiServletEsapi` | non | nettoyage ESAPI `encodeForLDAP` |
| `LdapiSpringLdapTemplate` | oui | Spring `LdapTemplate.search(base, filtre, mapper)` concaténé |
| `LdapiSpringQueryFilter` | oui | Spring `LdapQueryBuilder.filter(...)` concaténé |
| `LdapiSpringQueryWhere` | non | Spring `where("uid").is(uid)` : valeur échappée |
| `LdapiSpringLdapEncoder` | non | nettoyage Spring `LdapEncoder.filterEncode` |
| `LdapiSpringUnboundId` | oui | UnboundID `Filter.create(...)` concaténé |

## Mesure

Depuis la racine de Tiresias :

```bash
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule sqli
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule cmdi
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule pathtraver
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule xss
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule xxe
python -m Tests.benchmark ~/gitRep/JavaVulnLab --expected ~/gitRep/JavaVulnLab/expected.csv --rule ldapi
```
