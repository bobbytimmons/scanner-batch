package com.arbitrage.scannerbatch.batch.writer;

import com.arbitrage.scannerbatch.service.processor.dto.MaxGapTickers;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MaxGapTickersKafkaWriter implements ItemWriter<MaxGapTickers> {

    private final KafkaTemplate<String, MaxGapTickers> kafkaTemplate;
    private final String topic;

    public MaxGapTickersKafkaWriter(KafkaTemplate<String, MaxGapTickers> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void write(Chunk<? extends MaxGapTickers> items) {
        for (MaxGapTickers item : items) {
            kafkaTemplate.send(topic, item);
        }
    }
}
