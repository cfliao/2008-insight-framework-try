/*
 * PushTrace.java
 *
 * Created on October 14, 2001, 2:34 PM
 */

package s2h.sysutils.hmbs;

/**
 * @author nzjuneja
 * @version
 */
public class PushTrace
{

    private static Throwable t;

    // private static String _msg;

    public static void push(String msg, Throwable to) throws CommandFailedException
    {
        t = to;
        throw new CommandFailedException(msg + to.getMessage());
    }

    public static void push(Throwable to)
    {
        t = to;
    }

    @SuppressWarnings("static-access")
    public static void pop()
    {
        if (t != null)
            t.printStackTrace();
        else
        {
            Thread.currentThread().dumpStack();
        }
        t = null;
        Console.out.println(" DONE");
    }
}
