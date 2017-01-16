package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import service.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * misterbaykal
 * <p>
 * 16/01/17 21:58
 */
public class Consumer {
    private List<String> topicList;

    @Autowired
    private KafkaConsumer<String, String> consumer;
    @Autowired
    private Service service;


    /**
     * Instantiates a new Consumer.
     *
     * @param topics   the topics
     * @param consumer the consumer
     *                 <p>
     *                 misterbaykal
     *                 <p>
     *                 16/01/17 21:58
     */
    Consumer(String topics, KafkaConsumer<String, String> consumer) {
        this.topicList = Arrays.asList(topics.split(","));
        this.consumer = consumer;
        this.consumer.subscribe(this.topicList);

        String server = System.getProperties().getProperty(ConsumerConfig.CONSUMER_BOOTSTRAP_SERVERS);
        String group = System.getProperties().getProperty(ConsumerConfig.GROUP_ID);
        System.out.println("Kafka consumer started. - Server: " + server + ", Group: " + group + ". Topics: " + topics);
    }

    /**
     * Consume.
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:58
     */
    @Scheduled(fixedDelayString = "10")
    public void consume() {
        ConsumerRecords<String, String> records = consumer.poll(0);
        if (!records.isEmpty()) {
            for (ConsumerRecord<String, String> record : records) {
                if (this.getTopicList().get(0).equals(record.topic())) {
                    this.service.insertMessage(record.value());
                }
            }
        }
    }

    /**
     * Get Topics
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:58
     */
    private List<String> getTopicList() {
        return this.topicList;
    }
}
