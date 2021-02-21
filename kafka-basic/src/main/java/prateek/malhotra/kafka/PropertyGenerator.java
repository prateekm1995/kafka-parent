package prateek.malhotra.kafka;

import java.util.Properties;

import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class PropertyGenerator {
    private Properties kafkaProperties;
    public static final String TOPIC = "test";

    public PropertyGenerator(){
        this.kafkaProperties = new Properties();
        this.kafkaProperties.put("bootstrap.servers", "localhost:9092");
        this.kafkaProperties.put("key.serializer",StringSerializer.class.getName());
    }

    public Properties getStringProducerProperties(){
        this.kafkaProperties.put("value.serializer",StringSerializer.class.getName());
        return this.kafkaProperties;
    }

    public Properties generateAvroProducerProperties(){
        this.kafkaProperties.put("value.sertializer",KafkaAvroSerializer.class.getName());
        return this.kafkaProperties;
    }


}