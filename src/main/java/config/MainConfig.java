package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * misterbaykal
 * <p>
 * 16/01/17 21:53
 */
@Configuration
@EnableScheduling
public class MainConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(this.taskExecutor());
    }

    /**
     * Task executor executor.
     *
     * @return the executor
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:53
     */
    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(4);
    }

}
