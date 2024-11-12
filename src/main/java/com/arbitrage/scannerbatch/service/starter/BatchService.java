package com.arbitrage.scannerbatch.service.starter;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatchService {

    private JobLauncher jobLauncher;
    private Job job;

    public JobExecution startBatchJob(String token, Boolean centralized) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("token", token)
                .addString("centralized", centralized.toString())
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        return jobLauncher.run(job, jobParameters);
    }
}
