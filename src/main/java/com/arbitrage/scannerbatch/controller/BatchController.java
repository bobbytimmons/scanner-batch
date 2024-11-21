package com.arbitrage.scannerbatch.controller;

import com.arbitrage.scannerbatch.service.starter.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BatchController {


    private final BatchService batchService;

    @GetMapping("/startBatch")
    public ResponseEntity<String> startBatch(@RequestParam String token, @RequestParam boolean centralized, @RequestParam("x-cg-pro-api-key") String apiKey) {
        try {
            batchService.startBatchJob(token, centralized, apiKey);
            return ResponseEntity.ok("Batch job has been invoked");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Batch job failed to start");
        }
    }

    @GetMapping("/stopBatch")
    public ResponseEntity<String> stopBatch() {
        try {
            batchService.stopBatchJob();
            return ResponseEntity.ok("Batch job has been stopped");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Batch job failed to stop");
        }
    }
}
