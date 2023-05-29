package br.com.danielgarcez.azure.filesampleproducer.domain.service;

import br.com.danielgarcez.azure.filesampleproducer.domain.configuration.AzureCredentialConfiguration;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BlobStorageService {

    private final AzureCredentialConfiguration azureCredentialConfiguration;

    public BlobStorageService(AzureCredentialConfiguration azureCredentialConfiguration) {
        this.azureCredentialConfiguration = azureCredentialConfiguration;
    }

    @Value("${azure.storage.blob.container}")
    private String container;

    public void uploadFileToBlobStorage(String path, String fileName) {

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(this.azureCredentialConfiguration.getBlobStorageConnectionString())
                .buildClient();

        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(this.container);

        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        blobClient.uploadFromFile(path + "/" + fileName, true);
    }

}