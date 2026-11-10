package vulnlab.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Propriétés de configuration : api.orders.base-url=https://api.interne */
@ConfigurationProperties(prefix = "api.orders")
public class ApiProperties {
    private String baseUrl;

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
}
