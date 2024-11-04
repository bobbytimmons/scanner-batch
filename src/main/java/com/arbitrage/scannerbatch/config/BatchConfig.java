package com.arbitrage.scannerbatch.config;

import com.arbitrage.scannerbatch.batch.reader.ExchangePriceItemReader;
import com.arbitrage.scannerbatch.service.client.dto.Ticker;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job job(Step step) {
        return new JobBuilder("job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(ExchangePriceItemReader reader) {
        return new StepBuilder("step", jobRepository)
                .<Ticker, Ticker>chunk(10, transactionManager)
                .reader(reader)
                .processor(items -> items) // No-op processor, replace with actual processor if needed
                .writer(items -> {
                    // Define your writer logic here
                })
                .build();
    }
}
