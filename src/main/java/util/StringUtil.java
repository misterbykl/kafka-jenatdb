package util;

/**
 * misterbaykal
 * <p>
 * 16/01/17 21:52
 */
public class StringUtil {
    /**
     * Append string.
     *
     * @param argStringArr the arg string arr
     * @return the string
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:52
     */
    public static String append(Object... argStringArr) {
        String result = null;
        if (argStringArr != null) {
            StringBuilder builder = new StringBuilder();
            for (Object str : argStringArr) {
                if (str == null) {
                    builder.append("NULL");
                } else {
                    builder.append(str.toString());
                }

            }
            result = builder.toString();
        }
        return result;

    }
}
