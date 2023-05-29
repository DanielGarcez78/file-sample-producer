package br.com.danielgarcez.azure.filesampleproducer.domain.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureCredentialConfiguration {

    @Value("${azure.storage.default-endpoints-protocol}")
    private String protocol;

    @Value("${azure.storage.account-name}")
    private String accountName;

    @Value("${azure.storage.account-key}")
    private String accountKey;

    @Value("${azure.storage.blob.endpoint}")
    private String blobEndpoint;

    public String getBlobStorageConnectionString() {
        return "DefaultEndpointsProtocol=" + protocol + ";"
                + "AccountName=" + accountName + ";"
                + "AccountKey=" + accountKey + ";"
                + "BlobEndpoint=" + blobEndpoint + ";";
    }


}