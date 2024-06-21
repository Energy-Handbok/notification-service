package com.khaphp.notificationservice.config;

import com.khaphp.notificationservice.config.constant.KafkaConfigHelper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableKafka
public class KafkaConsumerConfig {
    @Value("${kafka-server}")
    private String kafkaServer;
    @Bean
    public ConsumerFactory<String, Object> consumerFactory()
    {
        // Creating a map of string-object type
        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);  //127.0.0.1:9092 hay localhost:9092 đều đc
        config.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConfigHelper.KAFKA_GROUP);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");  //có thể dùng * cho all
        // Returning message in JSON format
        return new DefaultKafkaConsumerFactory<>(config);
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> objectListener() {   //này là method dùng để lắng nghe object
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
