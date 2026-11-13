# JavaVulnLab

> ⚠️ Code volontairement vulnérable, à des fins de test. Ne pas déployer.

Application de test pour Tiresias. Elle couvre ce que l'OWASP BenchmarkJava ne couvre pas : Spring MVC, JAX-RS, JPA, Hibernate, Spring JDBC, Servlet `jakarta`, et les nettoyages (sanitizers).

Le code ne compile pas tel quel, et c'est voulu : Joern analyse les sources seules, il n'a pas besoin des dépendances.

## Règles

- Un cas = un fichier `.java` = une ligne dans `expected.csv`.
- Le nom du cas est le nom du fichier qui contient le sink. Il commence par la vulnérabilité (`Sqli`, `Cmdi`, `Path`, `Xss`, `Xxe`, `Ldapi`, `Deser`, `Ssrf`, `SsrfPartial` pour les cas d'hôte fixé, `ZipSlip`, `Entete`, `Journal`...) pour rester unique.
- Les cas sont rangés par vulnérabilité, puis par technologie : `vulnlab/<vulnérabilité>/<techno>/`.
- `vulnlab/common/` contient les classes utilitaires, qui ne sont pas des cas. Un fichier `...Controller` à côté d'un cas sert seulement à l'appeler : le cas est le fichier qui contient le sink.

## Cas

### `sqli` : injection SQL (40 cas)

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
| `SqliJsfBean` | oui | JSF : propriété saisie (`#{invoiceSearchBean.keyword}`) concaténée dans `createNativeQuery` |
| `SqliJsfRequestParamMap` | oui | JSF : `getRequestParameterMap().get("id")` concaténé dans une requête |
| `SqliSpringInheritedEntityManager` | oui | `entityManager` déclaré dans la classe mère `AbstractLabRepository` |

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

### `xss` : cross-site scripting (50 cas)

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
| `XssJspParamEl` | oui | JSP : `${param.q}` dans le texte |
| `XssJspCOut` | non | JSP : `<c:out value="${param.q}"/>` |
| `XssJspEscapeXml` | non | JSP : `${fn:escapeXml(param.q)}` |
| `XssJspCOutUnescaped` | oui | JSP : `<c:out ... escapeXml="false"/>` |
| `XssJspScriptlet` | oui | JSP : `<%= request.getParameter("name") %>` |
| `XssJspScriptletEncoded` | non | JSP : `<%= Encode.forHtml(...) %>` |
| `XssJspScriptletLocal` | oui | JSP : variable de scriptlet issue de `getParameter` |
| `XssJspCSet` | oui | JSP : `<c:set var="query" value="${param.q}"/>` puis `value="${query}"` |
| `XssJspModelAttr` | oui | contrôleur `model.addAttribute("message", ...)` + vue `${message}` |
| `XssJspModelAttrCOut` | non | idem, vue avec `<c:out>` |
| `XssJspModelAttrOtherView` | non | la JSP écrit `${otherMessage}`, mais le contrôleur renvoie une autre vue |
| `XssJspFormObject` | oui | `@ModelAttribute("search")` + vue `${search.keyword}` |
| `XssJspFormObjectInt` | non | objet de formulaire implicite, la vue n'écrit qu'un `Integer` |
| `XssServletForward` | oui | servlet `request.setAttribute` + forward, JSP `<%= request.getAttribute(...) %>` |
| `XssTagHandler` | oui | balise Java `SimpleTagSupport` qui écrit son attribut, utilisée avec `${param.comment}` |
| `XssTagFile` | oui | fichier `.tag` qui écrit `${text}`, utilisé avec `${param.label}` |
| `XssRestString` | oui | `@RestController` qui renvoie une `String` HTML |
| `XssRestStringJson` | non | idem avec `produces = APPLICATION_JSON_VALUE` |
| `XssRestStringEscaped` | non | idem avec `HtmlUtils.htmlEscape` |
| `XssResponseBodyEntity` | oui | `@ResponseBody ResponseEntity<String>` |
| `XssControllerView` | non | `@Controller` sans `@ResponseBody` : la `String` est un nom de vue |
| `XssJsfEscapeFalse` | oui | JSF : `h:inputTextarea` lié à `#{commentBean.comment}`, réécrit avec `escape="false"` |
| `XssJsfEscaped` | non | JSF : même chose sans `escape="false"` (échappé) |
| `XssJsfViewParam` | oui | JSF : `f:viewParam` puis `escape="false"` |
| `XssJsfRequestParamMap` | oui | JSF : getter calculé avec `getRequestParameterMap().get("name")` |
| `XssJsfManagedProperty` | oui | JSF : `@ManagedProperty("#{param.title}")` |
| `XssJsfOutputLink` | oui | JSF : `h:outputLink value="#{profileBean.website}"` (URL `javascript:`) |
| `XssJsfIntegerProp` | non | JSF : propriété `Integer` réécrite avec `escape="false"` |
| `XssJsfParam` | oui | JSF : `#{param.q}` avec `escape="false"` |
| `XssJspFmtParam` | oui | `<fmt:param value="${param.name}"/>` (fmt:message n'échappe rien) |
| `XssJspSpringMessage` | oui | `<spring:message arguments="${param.name}"/>` |
| `XssJspSpringMessageEscaped` | non | idem avec `htmlEscape="true"` |
| `XssJspEscapedUrl` | oui | `href="<c:out value='${param.next}'/>"` : échappé, mais `javascript:` passe |
| `XssJspFixedUrl` | non | `href="/factures/${fn:escapeXml(param.id)}"` |
| `XssJspEventHandler` | oui | `onclick="f('${fn:escapeXml(param.id)}')"` : attendu dans `aVerifier` (FN) |
| `XssJspScriptQuoted` | non | `<script>var q = '${fn:escapeXml(param.q)}';</script>` |
| `XssJspModelAddAll` | oui | `model.addAllAttributes(map)` + vue `${greeting}` |
| `XssJspModelAttributeMethod` | oui | méthode `@ModelAttribute("lastSearch")` + vue `${lastSearch}` |
| `XssTagHandlerInherited` | oui | balise Java héritant de `LabBaseTag extends SimpleTagSupport` |
| `XssJsfModelBean` | oui | JSF : bean `@Model`, `escape="false"` |
| `XssJsfEditableSelect` | oui | JSF : `p:selectOneMenu editable="true"`, `escape="false"` |
| `XssJsfSelectNotEditable` | non | JSF : `h:selectOneMenu` (valeur contrôlée par JSF), `escape="false"` |

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

### `ssrf` : requête côté serveur (37 cas)

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
| `SsrfSpringProxyPath` | oui | proxy : `backendUrl + request.getRequestURI().substring(...)` dans deux gestionnaires (portée chemin, un résultat regroupé) |
| `SsrfSpringBodyNotUrl` | non | corps reçu envoyé en 2e argument de `postForObject`, URL de configuration (filtre ArgumentVoisin) |

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

### `vue` : points suspects Vue.js (9 cas)

Mesurés par `Scripts/vue_scan.py` (sans Joern) ; les points « à vérifier » comptent comme détections.

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `VueVHtml` | oui | `v-html="comment.body"` |
| `VueVHtmlSanitized` | non | `v-html` d'une propriété calculée avec `DOMPurify.sanitize` |
| `VueMustache` | non | `{{ comment.body }}` (échappé) |
| `VueDynamicHref` | oui | `:href="profile.website"` |
| `VueStaticHref` | non | `` :href="`/factures/${id}`" ``, `:src="'/img/' + logo"` |
| `VueDynamicTemplate` | oui | ``template: `<h1>${title}</h1>` `` |
| `VueInnerHtml` | oui | `this.$refs.box.innerHTML = this.message` |
| `VueRedirect` | oui | `window.location.href = returnUrl` |
| `VueSafeDom` | non | `textContent`, `innerHTML = ''`, `setTimeout(() => ...)`, redirection fixe |

### `expr` : injection d'expression ou de script (12 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `ExprSpelParse` | oui | `parser.parseExpression(formula).getValue()` |
| `ExprSpelSimpleContext` | non | SpEL évalué avec `SimpleEvaluationContext.forReadOnlyDataBinding()` |
| `ExprSpelVariable` | non | expression fixe `#name.toUpperCase()`, valeur passée en variable |
| `ExprElProcessor` | oui | `new ELProcessor().eval(expression)` |
| `ExprValidatorTemplate` | oui | `buildConstraintViolationWithTemplate("..." + value)` sans version de HV connue : attendu dans `aVerifier` (FN) |
| `ExprValidatorParameter` | non | valeur passée par `addMessageParameter`, gabarit fixe `{value}` |
| `ExprScriptEngine` | oui | `ScriptEngine.eval("..." + rule + "...")` |
| `ExprValidatorTemplateEl` | oui | message construit avec la valeur + `enableExpressionLanguage(...)` |
| `ExprSpelSimpleContextMethods` | oui | `SimpleEvaluationContext...withInstanceMethods()` |
| `ExprElFactoryChain` | oui | `getApplication().getExpressionFactory().createValueExpression(..., expr, ...)` |
| `ExprGroovyEvalX` | oui | `Eval.x(42, formula)` : l'expression est le 2e argument |
| `ExprGroovyEvalXData` | non | `Eval.x(label, "x.toUpperCase()")` |

### `jndi` : injection JNDI (8 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `JndiLookup` | oui | `new InitialContext().lookup(resource)` |
| `JndiTemplateLookup` | oui | Spring `JndiTemplate.lookup(name)` |
| `JndiFixedPrefix` | non | `lookup("java:comp/env/jdbc/" + datasource)` |
| `JndiAllowlist` | non | liste blanche avant `lookup(name)` |
| `JndiDoLookup` | oui | `InitialContext.doLookup(name)` |
| `JndiProviderUrl` | oui | `env.put(Context.PROVIDER_URL, server)` |
| `JndiProviderUrlFixed` | non | `env.put(Context.PROVIDER_URL, "ldap://annuaire.interne:389/" + unit)` |
| `JndiJmx` | oui | `new JMXServiceURL(target)` |

### `redirect` : redirection ouverte (9 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `RedirectSendRedirect` | oui | `response.sendRedirect(returnUrl)` |
| `RedirectSpringView` | oui | `return "redirect:" + next` |
| `RedirectSlashOnly` | oui | `return "redirect:/" + path` (`//pirate.fr`) |
| `RedirectFixedPath` | non | `return "redirect:/factures/" + id` |
| `RedirectRelativeCheck` | non | `startsWith("/")` et refus de `//` avant `sendRedirect` |
| `RedirectLocationHeader` | oui | `setHeader("Location", url)` |
| `RedirectJaxrs` | oui | `Response.seeOther(URI.create(url))` |
| `RedirectJspTag` | oui | `<c:redirect url="${param.next}"/>` |
| `NomVueRedirect` | non | `"redirect:/accueil?retour=" + back` (hôte fixe) |

### `nomvue` : nom de vue ou page incluse choisi par l'utilisateur (6 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `XssControllerView` | oui | `return "sections/" + section` (`@Controller`) |
| `NomVueModelAndView` | oui | `new ModelAndView("pages/" + page)` |
| `NomVueAllowlist` | non | liste blanche avant `return "help/" + section` |
| `NomVueRedirect` | non | `"redirect:..."` : redirection, pas un nom de vue |
| `NomVueJspInclude` | oui | `<jsp:include page="${param.section}"/>` |
| `NomVueJsfInclude` | oui | `<ui:include src="#{param.view}"/>` |

### `entete` : en-tête de réponse HTTP choisi par l'utilisateur (10 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `EnteteServletCustom` | oui | `setHeader("X-Request-Source", param)` (servlet) |
| `EnteteCorsReflect` | oui | `Access-Control-Allow-Origin` recopie `@RequestHeader("Origin")` |
| `EnteteCorsAllowlist` | non | origine vérifiée par `ALLOWED_ORIGINS.contains(origin)` |
| `EnteteContentDisposition` | oui | `ResponseEntity...header(CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")` |
| `EnteteContentDispositionEncoded` | non | nom passé par `URLEncoder.encode` |
| `EnteteContentType` | oui | `response.setContentType(type)` |
| `EnteteNameChosen` | oui | `addHeader(header, "1")` : nom choisi par l'utilisateur |
| `EnteteJaxrs` | oui | JAX-RS `Response.ok().header("X-Trace-Id", trace)` |
| `EnteteSpringHttpHeaders` | oui | `HttpHeaders.add("Refresh", "0;url=" + next)` |
| `EnteteNumeric` | non | paramètre `int` |

### `bfla` : contrôle d'accès aux fonctions (18 cas)

`vulnlab/bfla/SecurityConfig.java` (pas un cas) limite une chaîne Spring Security à `/bfla/**` : `/bfla/admin/**` → ADMIN, `GET /bfla/reports/**` → MANAGER, `/bfla/public/**` → permitAll, le reste → authentifié, avec `@EnableMethodSecurity`. `src/main/webapp/WEB-INF/web.xml` exige ADMIN sur `/legacy/admin/*`. `src/main/resources/legacy-security.xml` (lu par Tiresias, non chargé par l'application) configure `/legacy-xml/**` en XML.

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `BflaAdminByUrl` | non | `DELETE /bfla/admin/users/{id}` : rôle par la règle d'URL |
| `BflaAdminAuthenticatedOnly` | oui | `POST /bfla/management/roles` : fonction d'administration, authentifié seulement |
| `BflaReportsVerbGap` | oui | règle MANAGER pour GET seulement, `POST /bfla/reports/{id}/archive` |
| `BflaReportsGetOk` | non | `GET /bfla/reports/{id}` : rôle MANAGER |
| `BflaMissingPreAuthorize` | oui | `exportAll` sans `@PreAuthorize` alors que les autres méthodes l'ont |
| `BflaPreAuthorizeAll` | non | toutes les méthodes avec `@PreAuthorize` |
| `BflaPublicReset` | oui | `POST /bfla/public/reset-all-passwords` en permitAll |
| `BflaPublicContact` | non | `POST /bfla/public/contact` en permitAll, pas sensible |
| `BflaProgrammaticCheck` | non | `request.isUserInRole("ADMIN")` dans le gestionnaire |
| `BflaJaxrsMissingRole` | oui | JAX-RS : `@RolesAllowed` sur `list`, oublié sur `deleteAll` |
| `BflaServletAdmin` | non | servlet `/legacy/admin/stats` : rôle par `web.xml` |
| `BflaServletPurge` | oui | servlet `/legacy/purge` : aucune contrainte |
| `BflaServiceLayerRole` | non | contrôleur sans contrôle, `@PreAuthorize` sur la méthode du service appelée |
| `BflaServiceLayerMissing` | oui | `/bfla/management/accounts/{id}/suspend` : ni contrôleur ni service ne vérifient le rôle |
| `BflaServiceInterfaceImpl` | non | appel par une interface, `@PreAuthorize` sur l'implémentation |
| `BflaMetaAnnotation` | non | annotation maison `@AdminOnly`, elle-même annotée `@PreAuthorize` |
| `BflaXmlAdmin` | non | `/legacy-xml/admin/**` : `hasRole('ADMIN')` dans `src/main/resources/legacy-security.xml` |
| `BflaXmlOpen` | oui | `/legacy-xml/**` en `permitAll` (XML) : suppression d'utilisateur ouverte |

### `bola` : accès à un objet sans contrôle du propriétaire (15 cas)

`vulnlab/bola/Invoice.java` (entité) et `InvoiceRepository.java` (Spring Data) servent aux cas ; ils n'en sont pas.

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `BolaFindById` | oui | `@PathVariable Long id` -> `invoiceRepository.findById(id)` |
| `BolaEntityManagerDelete` | oui | `em.find(Invoice.class, orderId)` puis `em.remove` |
| `BolaServiceLayer` | oui | identifiant transmis à un service qui lit sans contrôle |
| `BolaPrincipalUnused` | oui | `Principal` lu mais jamais comparé |
| `BolaEntityBinding` | oui | `@PathVariable("id") Invoice invoice` chargée par Spring |
| `BolaServletParam` | oui | servlet : `request.getParameter("accountId")` -> `accountDao.load(...)` |
| `BolaRequestParamId` | oui | `@RequestParam("invoiceId")` -> `getReferenceById` |
| `BolaOwnerFinder` | non | `findByIdAndOwner(id, principal.getName())` |
| `BolaOwnerCompared` | non | propriétaire comparé, `AccessDeniedException` |
| `BolaPostAuthorize` | non | `@PostAuthorize("returnObject.owner == authentication.name")` |
| `BolaHasPermission` | non | `@PreAuthorize("hasPermission(#id, 'Invoice', 'read')")` |
| `BolaAdminOnly` | non | réservé à `hasRole('ADMIN')` |
| `BolaServiceOwnerCheck` | non | `@AuthenticationPrincipal`, comparaison dans le service |
| `BolaNonIdParam` | non | recherche par titre, pas un identifiant |
| `BolaExplicitCheck` | non | `ownershipService.checkOwnership(id)` avant la lecture |

### `bopla` : propriétés d'objet modifiables ou exposées (13 cas)

`vulnlab/bopla/Member.java` (entité sans protection), `SafeMember.java` (Jackson : `READ_ONLY`, `@JsonIgnore`), `Purchase.java` (liée à un `Member`) et `BoplaStore.java` (repositories) servent aux cas ; ils n'en sont pas.

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `BoplaRequestBodyEntity` | oui | `@RequestBody Member` puis `save` (role, balance, id, password modifiables) |
| `BoplaModelAttributeEntity` | oui | `@ModelAttribute Member` puis `save` |
| `BoplaImplicitBinding` | oui | paramètre `Member` sans annotation (`@Controller`) puis `save` |
| `BoplaDto` | non | DTO `MemberUpdate`, seul l'email est recopié |
| `BoplaJsonIgnoreSafe` | non | `SafeMember` : id et rôle `READ_ONLY`, mot de passe `@JsonIgnore` |
| `BoplaInitBinder` | non | `@InitBinder` `setAllowedFields("email", "username")` |
| `BoplaReturnEntity` | oui | `Member` renvoyé en JSON (mot de passe) |
| `BoplaReturnResponseEntityList` | oui | `ResponseEntity<List<Member>>` |
| `BoplaReturnSafe` | non | `SafeMember` renvoyé, mot de passe `@JsonIgnore` |
| `BoplaReturnDto` | non | vue `MemberView` (record) |
| `BoplaNestedExposure` | oui | `Purchase` renvoyé avec `buyer.password` |
| `BoplaJsonView` | non | `@JsonView` sur le gestionnaire |
| `BoplaNotSaved` | non | `@RequestBody Member` lu, jamais enregistré |

### `journal` : injection dans les journaux (11 cas)

| Cas | Vulnérable | Ce qui est testé |
|---|---|---|
| `JournalSlf4jConcat` | oui | SLF4J, message concaténé |
| `JournalSlf4jParam` | oui | SLF4J paramétré `{}` (non échappé) |
| `JournalLog4j2` | oui | Log4j 2 |
| `JournalJul` | oui | `java.util.logging`, `log(Level.INFO, ...)`, servlet |
| `JournalLombok` | oui | Lombok `@Slf4j` (champ `log` généré) |
| `JournalOnlyLf` | oui | seul `\n` retiré, `\r` reste |
| `JournalHtmlEscape` | oui | `HtmlUtils.htmlEscape` : laisse les retours à la ligne |
| `JournalCrlfRemoved` | non | `replaceAll("[\r\n]", "_")` |
| `JournalEscapeJava` | non | `StringEscapeUtils.escapeJava` |
| `JournalNumeric` | non | `@PathVariable long` |
| `JournalConstantMessage` | non | message constant, la donnée n'est pas journalisée |

