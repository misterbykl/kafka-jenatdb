package kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import util.ApplicationUtil;

import java.io.IOException;
import java.util.Properties;

/**
 * misterbaykal
 * <p>
 * 16/01/17 21:57
 */
@EnableScheduling
@EnableAsync
@PropertySource(value = ApplicationUtil.APPLICATION_PROPERTIES)
@Configuration
public class ConsumerConfig {

    static String CONSUMER_BOOTSTRAP_SERVERS = "bootstrap.servers";
    static String GROUP_ID = "group.id";

    /**
     * Arg property sources placeholder configurer property sources placeholder configurer.
     *
     * @return the property sources placeholder configurer
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:57
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer argPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Create consumer kafka consumer.
     *
     * @param bootstrapServers the bootstrap servers
     * @param groupID          the group id
     * @return the kafka consumer
     * @throws IOException the io exception
     *                     <p>
     *                     misterbaykal
     *                     <p>
     *                     16/01/17 21:57
     */
    @Bean
    public KafkaConsumer<String, String> createConsumer(@Value("${kafka.bootstrap.servers}") String bootstrapServers,
                                                        @Value("${kafka.consumer.groupid}") String groupID) throws IOException

    {
        String KEY_DESERIALIZER = "key.deserializer";
        String KEY_DESERIALIZER_CLASS = "org.apache.kafka.common.serialization.StringDeserializer";
        String VALUE_DESERIALIZER = "value.deserializer";
        String VALUE_DESERIALIZER_CLASS = "org.apache.kafka.common.serialization.StringDeserializer";

        Properties properties = System.getProperties();
        properties.setProperty(CONSUMER_BOOTSTRAP_SERVERS, bootstrapServers);
        properties.setProperty(KEY_DESERIALIZER, KEY_DESERIALIZER_CLASS);
        properties.setProperty(VALUE_DESERIALIZER, VALUE_DESERIALIZER_CLASS);
        properties.setProperty(GROUP_ID, groupID);

        return new KafkaConsumer<>(properties);
    }

    /**
     * Consumer consumer.
     *
     * @param topics   the topics
     * @param consumer the consumer
     * @return the consumer
     * <p>
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:57
     */
    @Bean
    public Consumer consumer(@Value("${kafka.consumer.topic}") String topics, KafkaConsumer<String, String> consumer) {
        return new Consumer(topics, consumer);
    }
}
