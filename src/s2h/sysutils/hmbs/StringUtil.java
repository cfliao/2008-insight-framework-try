/*
 * StringUtil.java
 *
 * Created on February 9, 2002, 3:46 PM
 */

package s2h.sysutils.hmbs;

import java.util.Enumeration;
import java.util.Properties;

/**
 * @author Niraj Juneja
 * @version
 */
public class StringUtil
{

    public static Properties subList(Properties prop, String pattern)
    {

        Properties aprop = new Properties();
        Enumeration<?> _enum = prop.propertyNames();
        while (_enum.hasMoreElements())
        {
            String str = (String) _enum.nextElement();
            if (str.startsWith(pattern))
                aprop.put(str, prop.getProperty(str));

        }
        return aprop;
    }

}
