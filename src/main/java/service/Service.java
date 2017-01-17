package service;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.tdb.TDBFactory;
import org.slf4j.Logger;
import util.ExceptionUtil;
import util.LogUtil;

/**
 * misterbaykal
 * <p>
 * 16/01/17 21:55
 */
public class Service {
    private static Logger LOG = LogUtil.getRootLogger();
    private Dataset ds;
    private String modelName = null;

    /**
     * Instantiates a new Service.
     *
     * @param modelName the model name
     * @param path      the path
     *                  <p>
     *                  misterbaykal
     *                  <p>
     *                  16/01/17 21:56
     */
    Service(String modelName, String path) {
        this.modelName = modelName;
        ds = TDBFactory.createDataset(path);
    }


    /**
     * Parse message.
     *
     * @param value the value
     *              <p>
     *              misterbaykal
     *              <p>
     *              16/01/17 22:32
     */
    public void parseMessage(String value) {
        try {
            String[] data = value.split(" ");
            this.insertMessage(data[0], data[1], data[2]);
        } catch (Exception e) {
            System.out.println("Exception while parsing kafka message");
            ExceptionUtil.getStackTraceString(e, "parseMessage");
        }
    }

    /**
     * Insert message.
     *
     * @param subject  the subject
     * @param property the property
     * @param object   the object
     *                 <p>
     *                 misterbaykal
     *                 <p>
     *                 16/01/17 22:04
     */
    private void insertMessage(String subject, String property, String object) {
        try {
            ds.begin(ReadWrite.WRITE);
            Model model = ds.getNamedModel(this.modelName);

            Statement stmt = model.createStatement
                    (
                            model.createResource(subject),
                            model.createProperty(property),
                            model.createResource(object)
                    );

            model.add(stmt);
            ds.commit();
        } catch (Exception e) {
            System.out.println("Exception while inserting message into jena tdb");
            ExceptionUtil.getStackTraceString(e, "insertMessage");
        }
    }
}
