package s2h.sysutils.hmbs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

public class Shell {

	public static void main(String[] args) {
		String command;
		BufferedReader userIn = new BufferedReader(new InputStreamReader(
				System.in));
		while (true) {
			try {

				command = userIn.readLine();
				IOUtils.write(command+"\n", System.out);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
