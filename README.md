# JavaVulnLab

> ⚠️ Code volontairement vulnérable, à des fins de test. Ne pas déployer.

Application de test pour Tiresias. Elle couvre ce que l'OWASP BenchmarkJava ne couvre pas : Spring MVC, JAX-RS, JPA, Hibernate, Spring JDBC, Servlet `jakarta`, et les nettoyages (sanitizers).

Le code ne compile pas tel quel, et c'est voulu : Joern analyse les sources seules, il n'a pas besoin des dépendances.

## Règles

- Un cas = un fichier `.java` = une ligne dans `expected.csv`.
- Le nom du cas est le nom du fichier qui contient le sink. Il commence par la vulnérabilité (`Sqli`, `Cmdi`, `Path`, `Xss`, `Xxe`, `Ldapi`, `Deser`, `Ssrf`, `SsrfPartial` pour les cas d'hôte fixé, `ZipSlip`) pour rester unique.
- Les cas sont rangés par vulnérabilité, puis par technologie : `vulnlab/<vulnérabilité>/<techno>/`.
- `vulnlab/common/` contient les classes utilitaires, qui ne sont pas des cas. Un fichier `...Controller` à côté d'un cas sert seulement à l'appeler : le cas est le fichier qui contient le sink.

## Cas

### `sqli` : injection SQL (37 cas)

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
| `SqliWebSocketStomp` | oui | WebSocket Spring STOMP : paramètre de `@MessageMapping` |
| `SqliWebSocketHandler` | oui | WebSocket Spring brut : `TextWebSocketHandler.handleTextMessage` |
| `SqliWebSocketPathParam` | oui | WebSocket Jakarta : `@PathParam` d'une méthode `@OnOpen` |
| `SqliWebSocketSessionOnly` | non | WebSocket Jakarta : seul `session.getId()` (généré par le serveur) est utilisé |
| `SqliSpringCustomRepository` | oui | repository écrit à la main qui concatène l'argument de `findBy...` (la donnée entre DANS la méthode) |
| `SqliSpringRepositoryLookup` | non | requête construite avec un champ lu en base (`findById`), pas avec la valeur de l'utilisateur |
| `SqliSpringRepositoryImpl` | oui | fragment Spring Data (interface + `...Impl`) qui concatène : appelé par l'interface |
| `SqliSpringOrderByAllowlist` | non | `ORDER BY` : `if (!SORTABLE.contains(sort)) throw` |
| `SqliSpringOrderByBlocklist` | oui | liste NOIRE : `if (FORBIDDEN.contains(sort)) throw` |
| `SqliSpringRegexColumn` | non | `sort.matches("[a-zA-Z_]+")` |
| `SqliSpringRegexWeak` | oui | regex qui accepte l'apostrophe et l'espace |
| `SqliSpringGuardConditional` | oui | `if (strict && !SORTABLE.contains(sort))` : sans `strict`, rien n'est vérifié |
| `SqliSpringGuardInside` | non | sink dans la branche `if (SORTABLE.contains(sort))` |
| `SqliSpringCriteriaApi` | non | API Criteria : `em.createQuery(cq)`, valeur liée par `cb.equal` |
| `SqliSpringDateConversion` | non | `LocalDate.parse` avant la concaténation |
| `SqliSpringValidPattern` | non | `@Valid` + `@Pattern("[a-zA-Z_]+")` sur le champ du DTO |
| `SqliSpringPatternNoValid` | oui | `@Pattern` sur le champ mais pas de `@Valid` : jamais vérifié |
| `SqliSpringValidPatternWeak` | oui | `@Valid`, regex qui accepte l'apostrophe |
| `SqliSpringEnumValue` | non | `DocumentType.valueOf(texte).name()` : liste blanche de fait |
| `SqliMyBatisDollar` | oui | MyBatis `@Select` avec `${customer}` |
| `SqliMyBatisHash` | non | MyBatis `@Select` avec `#{customer}` |
| `SqliMyBatisXml` | oui | mapper XML : `ORDER BY ${sort}` |
| `SqliMyBatisXmlHash` | non | mapper XML : `#{customer}` |
| `SqliBatchItemProcessor` | oui | **batch** : ligne de fichier concaténée dans une requête. Attendu en FN, dans `aVerifier` |

### `cmdi` : injection de commandes (8 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `CmdiSpringProcessBuilder` | oui | `new ProcessBuilder("sh", "-c", ... + host)` |
| `CmdiJaxrsRuntimeExec` | oui | `Runtime.exec(new String[] {...})` avec un élément contrôlé |
| `CmdiSpringEncodeForOS` | non | nettoyage ESAPI `encodeForOS` |
| `CmdiSpringParseInt` | non | nettoyage `Integer.parseInt` |
| `CmdiWebSocketJakarta` | oui | WebSocket Jakarta : paramètre de `@OnMessage` |
| `CmdiWebSocketMessageHandler` | oui | WebSocket Jakarta programmatique : `MessageHandler.Whole<String>` |
| `CmdiSpringRegexHost` | non | `Pattern.compile("[a-zA-Z0-9.-]+").matcher(host).matches()` |
| `CmdiSpringCommonsExecCtor` | oui | Commons Exec `new CommandLine(tool)` |

### `pathtraver` : path traversal (20 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `PathSpringFile` | oui | `new File(BASE_DIR, name)` |
| `PathJaxrsNio` | oui | `Paths.get(BASE_DIR, name)` |
| `PathSpringFilenameUtils` | non | nettoyage `FilenameUtils.getName` |
| `PathSpringEsapiValidator` | non | nettoyage ESAPI `getValidFileName` |
| `PathSpringRepositoryLookup` | non | entité Lombok lue par `findById(id).orElseThrow(...)` : le chemin vient de la base |
| `PathSpringServiceField` | non | `@Service` : l'entrée part dans un appel inconnu, le chemin vient d'un champ `@Value` |
| `PathSpringUploadOriginalName` | oui | `MultipartFile.getOriginalFilename()` dans `new File(dir, ...)` |
| `PathSpringUploadCleanPath` | oui | `StringUtils.cleanPath` ne protège pas |
| `PathSpringUploadGetName` | non | `FilenameUtils.getName(getOriginalFilename())` |
| `PathSpringLookupOrElse` | oui | `findById(id).map(...).orElse(id)` : la valeur de l'utilisateur ressort si rien n'est trouvé |
| `PathSpringSingletonField` | oui | singleton qui garde le nom dans un champ modifié hors du constructeur (limite probable : champ suivi d'une méthode à l'autre) |
| `PathSpringSessionBean` | oui | bean `@SessionScope` : pas un singleton, ses champs contiennent des données de l'utilisateur |
| `PathSpringRegexName` | non | `name.matches("[a-zA-Z0-9_-]+")` |
| `PathSpringRegexDot` | oui | regex qui accepte le point : `..` remonte d'un dossier |
| `PathSpringCanonicalNio` | non | `resolve(name).normalize()` puis `startsWith(BASE)` |
| `PathSpringCheckBeforeNormalize` | oui | `startsWith` vérifié AVANT `normalize()` |
| `PathSpringCanonicalFile` | non | `getCanonicalPath().startsWith(dir + File.separator)` |
| `PathSpringCanonicalNoSeparator` | oui | comparaison de chaînes sans séparateur : `/var/app/reports-archive` passe |
| `PathSpringTableLookup` | non | `TEMPLATES.get(kind)` : la clé choisit l'entrée d'une table fixe (second ordre) |
| `PathSpringBase64Name` | non | nom encodé en Base64 URL : `..` impossible |
| `PathServletContextResource` | oui | `getServletContext().getResourceAsStream("/WEB-INF/reports/" + name)` |
| `PathSpringClassPathResource` | oui | `new ClassPathResource("templates/" + name)` |

### `xss` : cross-site scripting (8 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `XssServletWriter` | oui | `response.getWriter().println(... + name)` |
| `XssServletPrintWriterVar` | oui | `PrintWriter out = response.getWriter(); out.print(...)` |
| `XssSpringResponseWriter` | oui | handler Spring qui écrit dans `HttpServletResponse` |
| `XssSpringHtmlUtils` | non | nettoyage Spring `HtmlUtils.htmlEscape` |
| `XssServletOwaspEncoder` | non | nettoyage OWASP Java Encoder `Encode.forHtml` |
| `XssServletFileWriter` | non | `PrintWriter` vers un fichier de log : pas la réponse HTTP |
| `XssServletSplit` | oui | `split(" ")[0]` : Joern perd la donnée, retrouvée par un pont (`Flows.scala`) |
| `XssSpringJsoupClean` | non | `Jsoup.clean(comment, Safelist.basic())` |

### `xxe` : XML External Entity (31 cas)

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
| `XxeServletXInclude` | oui | DTD interdite, mais `setXIncludeAware(true)` : `xi:include` lit un fichier sans DTD |
| `XxeSpringOxmDefault` | non | Spring OXM `Jaxb2Marshaller` par défaut |
| `XxeSpringOxmSupportDtd` | oui | Spring OXM avec `setSupportDtd(true)` et `setProcessExternalEntities(true)` |
| `XxeSpringInjectedFactory` | non | fabrique injectée par `@Autowired`, protégée dans un `@Bean` (`LabBeansConfig`) |
| `XxeBatchTasklet` | oui | **batch** : `Tasklet` qui lit un fichier déposé, StAX non protégé. Aucune source HTTP : attendu en FN, dans la rubrique `aVerifier` |
| `XxeSpringDom4jParseText` | oui | dom4j `DocumentHelper.parseText` : `SAXReader` non protégé en interne |
| `XxeServletProjectConstant` | non | nom de l'option dans une constante du projet |
| `XxeSpringXsltDtdOnly` | oui | XSLT : `ACCESS_EXTERNAL_DTD` seul, `xsl:import` passe |
| `XxeSpringXsltBothAccess` | non | XSLT : `ACCESS_EXTERNAL_DTD` et `ACCESS_EXTERNAL_STYLESHEET` |
| `XxeSpringStaxDefensive` | non | `StaxUtils.createDefensiveInputFactory()` de Spring |

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

### `deser` : désérialisation non sûre (24 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `DeserServletObjectInputStream` | oui | `ObjectInputStream` sur le corps de la requête, cast après coup |
| `DeserServletObjectInputFilter` | non | JEP 290 : `setObjectInputFilter` en liste blanche (`...;!*`) |
| `DeserServletFilterAllowAll` | oui | filtre posé mais qui accepte tout (`*`) |
| `DeserServletLookAhead` | non | sous-classe du projet : `resolveClass` refuse les classes inconnues |
| `DeserServletLoggingStream` | oui | sous-classe qui redéfinit `resolveClass` sans rien refuser |
| `DeserServletValidatingOis` | non | Commons IO `ValidatingObjectInputStream` + `accept(Order.class)` |
| `DeserServletConstantData` | non | flux non filtré, mais octets d'une ressource embarquée |
| `DeserSpringCookieBase64` | oui | objet sérialisé en Base64 dans un cookie |
| `DeserSpringXmlDecoder` | oui | `java.beans.XMLDecoder` : toujours dangereux |
| `DeserSpringXStream` | oui | XStream sans liste blanche (≤ 1.4.17) |
| `DeserSpringXStreamAllowTypes` | non | XStream : `allowTypes` dans le constructeur (champ) |
| `DeserSpringXStreamAnyType` | oui | XStream : `addPermission(AnyTypePermission.ANY)` rouvre tout |
| `DeserSpringSnakeYaml` | oui | SnakeYAML `new Yaml().load(...)` (1.x) |
| `DeserSpringSnakeYamlSafe` | non | SnakeYAML avec `SafeConstructor` |
| `DeserSpringJacksonDefaultTyping` | oui | Jackson `enableDefaultTyping()` |
| `DeserSpringJacksonLaissezFaire` | oui | Jackson `activateDefaultTyping(LaissezFaire...)` via le builder, dans un champ |
| `DeserSpringJacksonPlain` | non | Jackson par défaut, classe fixée par le code |
| `DeserSpringJacksonTypeInfo` | oui | `@JsonTypeInfo(use = Id.CLASS)` sur un champ `Object` de la classe cible |
| `DeserSpringCommonsLang` | oui | Commons Lang `SerializationUtils.deserialize` |
| `DeserSpringKryo` | oui | Kryo `setRegistrationRequired(false)` |
| `DeserSpringKryoRegistered` | non | Kryo 5 par défaut : classes enregistrées seulement |
| `DeserSpringInjectedMapper` | oui | `ObjectMapper` injecté par le constructeur, configuré dans un `@Bean` (`LabBeansConfig`) |
| `DeserBatchTasklet` | oui | **batch** : `ObjectInputStream` sur un fichier déposé. Attendu en FN, dans la rubrique `aVerifier` |
| `DeserJmsObjectMessage` | oui | **écouteur JMS** : `ObjectMessage.getObject()`. Attendu en FN, dans `aVerifier` |

### `ssrf` : requête côté serveur (35 cas)

La colonne « Portée » indique ce que l'utilisateur contrôle : l'**hôte** (SSRF complète), le **chemin** ou les **paramètres** (SSRF partielle, l'hôte étant fixé par le code ou la configuration).

| Cas | Vulnérable | Portée | Ce qui est testé |
|---|---|---|---|
| `SsrfServletUrlOpenStream` | oui | hôte | `new URL(param).openStream()` |
| `SsrfServletHttpClient` | oui | hôte | `java.net.http`, adresse dans un en-tête |
| `SsrfServletAllowlist` | non | | liste blanche sur `url.getHost()` avant l'appel |
| `SsrfSpringRestTemplate` | oui | hôte | `RestTemplate.getForObject(url, ...)` |
| `SsrfSpringWebClient` | oui | hôte | `webClient.get().uri(url)` |
| `SsrfSpringApacheHttpGet` | oui | hôte | Apache HttpClient `new HttpGet(url)` |
| `SsrfSpringOkHttp` | oui | hôte | OkHttp `new Request.Builder().url(url)` |
| `SsrfSpringJsoup` | oui | hôte | `Jsoup.connect(url)` |
| `SsrfSpringHostConcat` | oui | hôte | `"https://" + host + "/api"` |
| `SsrfSpringPrefixNoSlash` | oui | hôte | `"https://api.interne" + path` : `@evil.com` change l'hôte |
| `SsrfSpringStartsWithNoSlash` | oui | hôte | `startsWith("https://api.interne")` : contournable |
| `SsrfSpringContainsCheck` | oui | hôte | `url.contains("api.interne")` : contournable |
| `SsrfSpringMatchesNoSlash` | oui | hôte | `matches("https://api\\.interne.*")` : `api.interne.evil.com` passe |
| `SsrfSpringStartsWithSlash` | non | | `startsWith("https://api.interne/")` |
| `SsrfSpringMatchesHost` | non | | `matches("https://api\\.interne/.*")` |
| `SsrfSpringFixedHostQuery` | oui | paramètres | `"https://api.interne/users?id=" + id` |
| `SsrfSpringConstantBase` | oui | chemin | constante `static final` + `id` |
| `SsrfSpringFormatFixedHost` | oui | chemin | `String.format("https://api.interne/users/%s", id)` |
| `SsrfSpringUriTemplate` | oui | chemin | gabarit `RestTemplate` `{id}` |
| `SsrfSpringUriBuilderQuery` | non | | `UriComponentsBuilder...queryParam("q", q)` : valeur encodée |
| `SsrfPartialValueBase` | oui | chemin | `baseUrl` (`@Value`) + `"/users/" + id` |
| `SsrfPartialConstructorValue` | oui | chemin | `@Value` injecté par le constructeur |
| `SsrfPartialConfigProps` | oui | chemin | `properties.getBaseUrl()` (`@ConfigurationProperties`) |
| `SsrfPartialLiteralPath` | oui | chemin | `"https://api.interne/users/" + id` |
| `SsrfPartialRestTemplateVar` | oui | chemin | gabarit `RestTemplate` : mode `URI_COMPONENT`, `/` non encodé |
| `SsrfPartialWebClientVar` | oui | chemin | gabarit `WebClient` : `/` encodé, mais `..` reste `..` |
| `SsrfPartialQueryParam` | oui | paramètres | `"?q=" + q` : `&role=admin` ajoute un paramètre |
| `SsrfPartialEncodedSegment` | oui | chemin | `UriUtils.encodePathSegment` : `..` n'est pas encodé |
| `SsrfPartialEncodedQuery` | non | | `URLEncoder.encode` dans un paramètre |
| `SsrfPartialUuidPath` | non | | `UUID.fromString` avant le chemin |
| `SsrfPartialLongVariable` | non | | `@PathVariable Long` dans un gabarit |
| `SsrfSpringBlocklist` | oui | hôte | liste NOIRE d'hôtes : `127.1`, `[::1]`, DNS passent |
| `SsrfSpringIdRegex` | non | | hôte fixé, `id.matches("[a-zA-Z0-9_-]{1,40}")` |
| `SsrfSpringDtoInteger` | non | | champ `Integer` d'un DTO Lombok dans les paramètres |
| `SsrfSpringDtoString` | oui | paramètres | champ `String` du même DTO |

### `zipslip` : nom d'une entrée d'archive dans un chemin (5 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `ZipSlipUnsafe` | oui | `new File(dest, entry.getName())` |
| `ZipSlipNormalized` | non | `dest.resolve(entry.getName()).normalize()` puis `startsWith(dest)` |
| `ZipSlipCanonicalNoSeparator` | oui | `getCanonicalPath().startsWith(dest.getCanonicalPath())` sans séparateur |
| `ZipSlipGetName` | non | `FilenameUtils.getName(entry.getName())` |
| `ZipSlipCommonsCompress` | oui | archive tar (Commons Compress), `Paths.get(dest, entry.getName())` |

## Mesure

Depuis la racine de Tiresias :

```bash
python -m Tests.benchmark ~/gitdepo/JavaVulnLab --expected ~/gitdepo/JavaVulnLab/expected.csv --rule sqli
python -m Tests.benchmark ~/gitdepo/JavaVulnLab --expected ~/gitdepo/JavaVulnLab/expected.csv --rule cmdi
python -m Tests.benchmark ~/gitdepo/JavaVulnLab --expected ~/gitdepo/JavaVulnLab/expected.csv --rule pathtraver
python -m Tests.benchmark ~/gitdepo/JavaVulnLab --expected ~/gitdepo/JavaVulnLab/expected.csv --rule xss
python -m Tests.benchmark ~/gitdepo/JavaVulnLab --expected ~/gitdepo/JavaVulnLab/expected.csv --rule xxe
python -m Tests.benchmark ~/gitdepo/JavaVulnLab --expected ~/gitdepo/JavaVulnLab/expected.csv --rule ldapi
python -m Tests.benchmark ~/gitdepo/JavaVulnLab --expected ~/gitdepo/JavaVulnLab/expected.csv --rule deser
python -m Tests.benchmark ~/gitdepo/JavaVulnLab --expected ~/gitdepo/JavaVulnLab/expected.csv --rule ssrf
python -m Tests.benchmark ~/gitdepo/JavaVulnLab --expected ~/gitdepo/JavaVulnLab/expected.csv --rule zipslip
```
