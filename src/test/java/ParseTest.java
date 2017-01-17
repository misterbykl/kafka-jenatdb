import org.junit.Assert;
import org.junit.Test;

/**
 * misterbaykal
 * <p>
 * 17/01/2017 23:32
 */
public class ParseTest {
    @Test
    public void parse()
    {
        String value = ":a1 :b1 :c1";
        String[] data = value.split(" ");
        Assert.assertEquals(":a1", data[0]);
        Assert.assertEquals(":b1", data[1]);
        Assert.assertEquals(":c1", data[2]);
    }
}
