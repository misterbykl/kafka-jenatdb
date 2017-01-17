import config.MainConfig;
import kafka.ConsumerConfig;
import org.slf4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ServiceConfig;
import util.ApplicationUtil;
import util.ExceptionUtil;
import util.LogUtil;

/**
 * misterbaykal
 * <p>
 * 16/01/17 21:32
 */
public class Main {
    private static Logger LOG = LogUtil.getRootLogger();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     *             <p>
     *             misterbaykal
     *             <p>
     *             16/01/17 21:40
     */
    public static void main(String[] args) {
        ApplicationUtil.setSystemProperties();
        System.out.println("kafka-jenatdb is starting...");
        AnnotationConfigApplicationContext applicationContext = null;
        try {
            applicationContext = new AnnotationConfigApplicationContext();
            applicationContext.register(MainConfig.class, ConsumerConfig.class, ServiceConfig.class);
            applicationContext.refresh();
            applicationContext.registerShutdownHook();
            System.out.println("kafka-jenatdb has started");

        } catch (Exception e) {
            System.out.println("Exception while starting the app");
            ExceptionUtil.getStackTraceString(e, "main");
        }
    }
}
