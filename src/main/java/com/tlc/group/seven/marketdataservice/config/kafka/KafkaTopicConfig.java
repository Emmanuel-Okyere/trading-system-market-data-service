package com.tlc.group.seven.marketdataservice.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {

    @Bean
    public NewTopic marketDataTopic(){
        return TopicBuilder
                .name("market-data")
                .partitions(3)
                .build();
    }
}