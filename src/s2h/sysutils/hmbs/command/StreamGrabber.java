package s2h.sysutils.hmbs.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import s2h.sysutils.hmbs.Console;

class StreamGobbler extends Thread
{
    InputStream is;

    String type;

    StreamGobbler(InputStream is, String type)
    {
        this.is = is;
        this.type = type;
    }

    public void run()
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            //Console.out.println("\n"+type + ">");
            while ((line = br.readLine()) != null)
                Console.out.println(line);
            //System.out.println(type + ">" + line);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
