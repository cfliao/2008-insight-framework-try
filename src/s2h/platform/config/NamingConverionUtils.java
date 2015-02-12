package s2h.platform.config;

public class NamingConverionUtils {
	public static String firstPart(String s) {
		String[] sp = s.split("[\\.\\-_]");
		if (sp.length > 1) {
			return sp[0];
		}
		return s;
	}

	public static String dot2Slash(String s) {
		return s.replace('.', '/');
	}

	public static String dot2Dash(String s) {
		return s.replace('.', '-');
	}

	public static String dot2Camel(String s) {
		StringBuilder builder = new StringBuilder();
		for (String ss : s.split("\\.")) {
			if (ss == null || "".equals(ss.trim())) {
				continue;
			}
			String token = ss;
			if (builder.length() == 0 && !"".equals(token.trim())) {
				builder.append(token.trim().toLowerCase());
				continue;
			}
			builder.append(Character.toUpperCase(token.charAt(0)));
			if (token.length() > 1) {
				builder.append(token.substring(1));
			}
		}
		return builder.toString();
	}
}
