package com.arbitrage.scannerbatch.service.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatchService {


    private final JobLauncher jobLauncher;


    private final Job job;


    private final JobOperator jobOperator;

    private Long jobExecutionId ;

    public void startBatchJob(String token,boolean centralized, String apiKey) throws Exception {
        // Job starting logic...
        jobExecutionId = jobLauncher.run(job, new JobParametersBuilder()
                .addString("token", token)
                .addString("centralized", String.valueOf(centralized))
                .addString("apiKey", apiKey)
                .toJobParameters()).getId();
    }

    public void stopBatchJob() throws Exception {
        if (jobExecutionId != null) {
            jobOperator.stop(jobExecutionId);
        }
    }
}
