package prateek.malhotra.kafka.kafkaconsumers;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import prateek.malhotra.kafka.PropertyGenerator;

public class StringConsumer {
    public static void main(String[] args) {
        PropertyGenerator props = new PropertyGenerator();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props.getStringConsumerProperties("ConsumerGroup1"));
        consumer.subscribe(Collections.singletonList(PropertyGenerator.TOPIC));
        Runtime.getRuntime().addShutdownHook(new Thread(consumer::wakeup));

        try{
            System.out.println("Consumer Starting");
            while(true){
                ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(1000));
                records.forEach(record -> {
                    System.out.println(record.toString());
                });
                consumer.commitAsync((metadata,exception) -> {
                    if(exception != null){
                        System.out.println("commit failed");
                    }
                    System.out.println("commit succesful:" + metadata.toString());
                });
            }

        }catch(WakeupException exp) {
            System.out.println("Consumer Shutting Down");
        }
        finally{
            try{
                consumer.commitSync();
            }finally {
                consumer.close();
            }
        }
    }
}
