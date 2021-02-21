package prateek.malhotra.kafka.kafkaproducers;

import java.util.Scanner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import prateek.malhotra.kafka.PropertyGenerator;

public class StringProducer {
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PropertyGenerator props = new PropertyGenerator();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props.getStringProducerProperties());
        StringProducer stringProducer = new StringProducer();

        while (true) {
            try {
                producer.send(stringProducer.getRecord(), (metadata, exception) -> {
                    if (exception != null) {
                        exception.printStackTrace();
                    } else {
                        System.out.println(metadata.toString());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private ProducerRecord<String, String> getRecord() {
        System.out.println("Enter new message");
        String message = sc.nextLine();
        return new ProducerRecord<String, String>(PropertyGenerator.TOPIC, message);
    }

}
