package s2h.platform.support;

import java.io.UnsupportedEncodingException;

public class CodeConvert
{
    /**
     * �N��JBig5�X�r���নUnicode�X�r��C
     * 
     * @param sInBig5String
     *            (String)��J��Big5�X�r��C
     * @return (String)Unicode�X�r�� �C
     */
    public static String toUnicode(String sInBig5String)
    {
        if (sInBig5String != null)
        {
            try
            {
                return new String(sInBig5String.getBytes("ISO8859_1"), "Big5");
            }
            catch (UnsupportedEncodingException e)
            {
                return sInBig5String;
            }
        } else
        {
            return null;
        }
    }

    /**
     * �N��JUnicode�X�r���নBig5�X�r��C
     * 
     * @param sInUnicodeString
     *            (String)��J��Unicode�X�r��C
     * @return (String)Big5�X�r��C
     */
    public static String toBig5(String sInUnicodeString)
    {
        if (sInUnicodeString != null)
        {
            try
            {
                return new String(sInUnicodeString.getBytes("MS950"), "ISO8859_1");
            }
            catch (UnsupportedEncodingException e)
            {
                return sInUnicodeString;
            }
        } else
        {
            return null;
        }
    }
}
