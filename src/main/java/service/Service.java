package service;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
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
    private final String LOCAL = "local";
    private final String HTTP = "http";
    private static Logger LOG = LogUtil.getRootLogger();
    private String serviceUri = null;
    private String dataAccessMode = null;
    private Dataset ds;
    private DatasetAccessor dsAccessor;
    private Model model = null;


    /**
     * Instantiates a new Service.
     *
     * @param modelName      the model name
     * @param path           the path
     * @param dataAccessMode the data access mode
     * @param serviceUri     the service uri
     *                       <p>
     *                       <p>
     *                       misterbaykal
     *                       <p>
     *                       20/01/17 15:20
     */
    Service(String modelName, String path, String dataAccessMode, String serviceUri) {
        this.dataAccessMode = dataAccessMode;
        this.serviceUri = serviceUri;
        if (LOCAL.equalsIgnoreCase(dataAccessMode)) {
            this.ds = TDBFactory.createDataset(path);
            this.model = ds.getNamedModel(modelName);
        } else if (HTTP.equalsIgnoreCase(dataAccessMode)) {
            this.dsAccessor = DatasetAccessorFactory.createHTTP(serviceUri);
            this.model = dsAccessor.getModel();
        }
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

            if (LOCAL.equalsIgnoreCase(dataAccessMode)) {
                Statement stmt = model.createStatement
                        (
                                model.createResource(subject),
                                model.createProperty(property),
                                model.createResource(object)
                        );
                model.add(stmt);
                ds.commit();
            } else if (HTTP.equalsIgnoreCase(dataAccessMode)) {
                Resource sub = model.createResource(subject);
                Resource obj = model.createResource(object);
                Property pro = model.createProperty(this.serviceUri, property);
                final Statement stmt = model.createStatement(sub, pro, obj);
                model.add(stmt);
                dsAccessor.add(model);
            }
        } catch (Exception e) {
            System.out.println("Exception while inserting message into jena tdb");
            ExceptionUtil.getStackTraceString(e, "insertMessage");
        }
    }
}