package s2h.platform.support;

import java.io.UnsupportedEncodingException;

public class CodeConvert
{
    /**
     * ±N¿é¤JBig5½X¦r¦êÂà¦¨Unicode½X¦r¦ê¡C
     * 
     * @param sInBig5String
     *            (String)¿é¤JªºBig5½X¦r¦ê¡C
     * @return (String)Unicode½X¦r¦ê ¡C
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
     * ±N¿é¤JUnicode½X¦r¦êÂà¦¨Big5½X¦r¦ê¡C
     * 
     * @param sInUnicodeString
     *            (String)¿é¤JªºUnicode½X¦r¦ê¡C
     * @return (String)Big5½X¦r¦ê¡C
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
