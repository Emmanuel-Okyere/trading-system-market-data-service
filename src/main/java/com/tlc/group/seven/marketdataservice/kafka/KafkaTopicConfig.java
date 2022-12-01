package com.tlc.group.seven.marketdataservice.kafka;

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

    @Bean
    public NewTopic logDataTopic(){
        return TopicBuilder
                .name("log-data")
                .partitions(3)
                .build();
    }
}