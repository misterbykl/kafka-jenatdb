package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * misterbaykal
 * <p>
 * 16/01/17 21:55
 */
@PropertySource(value = "file:./conf/application.properties")
@Configuration
public class ServiceConfig {

    /**
     * Service service.
     *
     * @return the service
     * <p>
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:55
     */
    @Bean
    public Service service(@Value("${jenatdb.config.modelname}") String modelName, @Value("${jenatdb.config.path}") String path,
                           @Value("${jenatdb.config.mode}") String dataAccessMode, @Value("${jenatdb.config.serviceuri}") String serviceUri) {
        return new Service(modelName, path, dataAccessMode, serviceUri);
    }
}
