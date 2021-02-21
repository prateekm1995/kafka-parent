package prateek.malhotra.kafka;

import java.util.Properties;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class PropertyGenerator {
    private Properties kafkaProperties;
    public static final String TOPIC = "test";

    public PropertyGenerator(){
        this.kafkaProperties = new Properties();
        this.kafkaProperties.put("bootstrap.servers", "localhost:9092");
    }

    public Properties getStringProducerProperties(){
        this.kafkaProperties.put("key.serializer",StringSerializer.class.getName());
        this.kafkaProperties.put("value.serializer",StringSerializer.class.getName());
        return this.kafkaProperties;
    }

    public Properties getAvroProducerProperties(){
        this.kafkaProperties.put("value.sertializer",KafkaAvroSerializer.class.getName());
        return this.kafkaProperties;
    }

    public Properties getStringConsumerProperties(String consumerGroup){
        this.kafkaProperties.put("key.deserializer",StringDeserializer.class.getName());
        this.kafkaProperties.put("value.deserializer",StringDeserializer.class.getName());
        this.kafkaProperties.put("group.id", consumerGroup);
        this.kafkaProperties.put("enable.auto.commit",false);
        return this.kafkaProperties;
    }
}