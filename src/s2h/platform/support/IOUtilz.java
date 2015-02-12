package s2h.platform.support;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IOUtilz extends IOUtils {

	static Log log = LogFactory.getLog(IOUtilz.class);

	public static String readLinesToString(InputStream in) {
		try {
			StringBuffer buf = new StringBuffer();
			for (Object o : readLines(in)) {
				buf.append(o).append('\n');
			}
			if (buf.charAt(buf.length() - 1) == '\n') {
				buf.deleteCharAt(buf.length() - 1);
			}
			return buf.toString();
		} catch (IOException e) {
			log.error("", e);
		}
		return "";
	}
}
