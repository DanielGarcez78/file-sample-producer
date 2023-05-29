package br.com.danielgarcez.azure.filesampleproducer.api.controller;

import br.com.danielgarcez.azure.filesampleproducer.domain.model.FileSampleProducerParam;
import br.com.danielgarcez.azure.filesampleproducer.domain.service.FileSampleProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FileSampleProducerController {

    private final FileSampleProducerService fileSampleProducerService;

    @Autowired
    public FileSampleProducerController(FileSampleProducerService fileSampleProducerService) {
        this.fileSampleProducerService = fileSampleProducerService;
    }

    @GetMapping("/files")
    public ResponseEntity<?> getFileSamples() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/generate-files")
    public ResponseEntity<?> generateFileSamples(@RequestBody FileSampleProducerParam fileSampleProducerParam) {
        try {
            this.fileSampleProducerService.generateFileSamples(fileSampleProducerParam);
            return ResponseEntity.ok().body(Optional.of("File samples generated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Optional.of(e.getMessage()));
        }
    }
}
