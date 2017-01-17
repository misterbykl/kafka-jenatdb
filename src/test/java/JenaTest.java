import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.tdb.TDBFactory;
import org.junit.Test;

/**
 * misterbaykal
 * <p>
 * 17/01/2017 22:35
 */
public class JenaTest {

    private Dataset ds;

    @Test
    public void create() {
        ds = TDBFactory.createDataset("ds");
    }

    @Test
    public void insert() {
        ds = TDBFactory.createDataset("ds");
        ds.begin(ReadWrite.WRITE);
        Model model = ds.getNamedModel("testModel2");

        Statement stmt = model.createStatement
                (
                        model.createResource("a"),
                        model.createProperty("b"),
                        model.createResource("c")
                );

        model.add(stmt);
        ds.commit();
    }
}
