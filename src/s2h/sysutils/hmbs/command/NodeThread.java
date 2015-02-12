package s2h.sysutils.hmbs.command;

import java.io.IOException;

import org.apache.commons.lang.SystemUtils;

public class NodeThread extends Thread
{
    // private File f = new File(SystemUtils.getUserDir() + "\\bat");

    private String nodename;

    public NodeThread(String nodename)
    {
        this.nodename = nodename;
    }

    public void run()
    {
        Runtime r = Runtime.getRuntime();
        try
        {
            // Process proc = r.exec(new String[] { "cmd", "/C", "start",
            // "/MIN", "\"" + nodename + "\"", nodename + ".bat" }, null,
            // SystemUtils.getUserDir());
            Process proc = r.exec(new String[] { "cmd", "/C", nodename + ".bat" }, null, SystemUtils.getUserDir());
            // any error message?
            StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");

            // any output?
            StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");

            // kick them off
            errorGobbler.start();
            outputGobbler.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
