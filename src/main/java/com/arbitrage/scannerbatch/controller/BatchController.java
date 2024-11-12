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

    private BatchService batchService;

    @GetMapping("/startBatch")
    public ResponseEntity<String> startBatch(@RequestParam String token, @RequestParam boolean centralized) {
        try {
            batchService.startBatchJob(token, centralized);
            return ResponseEntity.ok("Batch job has been invoked");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Batch job failed to start");
        }
    }
}
