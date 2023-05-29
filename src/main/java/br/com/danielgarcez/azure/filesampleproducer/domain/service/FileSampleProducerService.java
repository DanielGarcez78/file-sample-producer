package br.com.danielgarcez.azure.filesampleproducer.domain.service;

import br.com.danielgarcez.azure.filesampleproducer.domain.model.FileSampleProducerParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileSampleProducerService {

    private final BlobStorageService blobStorageService;
    @Value("${local.output-file-directory}")
    private String path;

    @Autowired
    public FileSampleProducerService(BlobStorageService blobStorageService) {
        this.blobStorageService = blobStorageService;
    }

    public void generateFileSamples(FileSampleProducerParam fileSampleProducerParam) throws IOException {

        String content = "";
        for (int index = 0; index < fileSampleProducerParam.getQuantity(); index++) {
            String fileName = getFileName(fileSampleProducerParam.getQuantity(), index);
            content = "A";
            try (FileOutputStream fileOutputStream = new FileOutputStream(this.path + "/" + fileName)) {
                fileOutputStream.write(content.getBytes());
                this.blobStorageService.uploadFileToBlobStorage(this.path, fileName);
            }
        }
    }

    private String getFileName(int quantity, int index) {
        return "FileSample_" + String.format("%0" + String.valueOf(quantity).length() + "d", (index + 1)) + ".txt";
    }
}