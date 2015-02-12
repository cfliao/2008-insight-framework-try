package s2h.sysutils.hmbs.command;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import s2h.platform.node.PlatformTopic;
import s2h.platform.node.Sendable;
import s2h.sysutils.hmbs.CommandFailedException;
import s2h.sysutils.hmbs.Console;
import s2h.sysutils.hmbs.ConsoleCommand;

/**
 * @author try
 */
public class ControlCommand extends ConsoleCommand
{
    private ApplicationContext context;

    private Sendable sender;

    public ControlCommand()
    {
        super(NAME, DESC, HELP);
        context = new ClassPathXmlApplicationContext("s2h/sysutils/hmbs/command/shell-config.xml");
        sender = (Sendable) context.getBean("sender");
    }

    private static final String NAME = "control";

    private static final String DESC = "control ambient devices";

    private static final String HELP = "control <CONTROL_COMMAND>";

    public void execute(String[] args) throws CommandFailedException
    {
        try
        {
            String cmd = StringUtils.trimToNull(args[0]);

            if (cmd == null || "?".equals(cmd))
            {
                printSyntax();
            } else
            {
                sender.send(cmd, PlatformTopic.COMMAND);

                Console.out.println("control command sent:" + cmd);
            }
        }
        catch (IndexOutOfBoundsException ex)
        {
            printSyntax();
        }
        catch (IllegalArgumentException ex)
        {
            printSyntax();
        }
    }

    private void printSyntax()
    {
        Console.out.println("syntax:\n" + HELP);
        Console.out.println("\nTV Control:\n");
        Console.out.println("  TV_ON, TV_OFF, TV_PREV, TV_NEXT, TV_VOL_UP, TV_VOL_DOWN, TV_MUTE");
        Console.out.println("\nAir Control:\n");
        Console.out.println("  FAN-N_ON, FAN-N_OFF, FAN-S_ON, FAN-S_OFF");
        Console.out.println("  AIR-PUMP-N_ON, AIR-PUMP-N_OFF, AIR-PUMP-S_ON, AIR-PUMP-S_OFF");
        Console.out.println("\nLight Control:\n");
        Console.out.println("  CENTER-LIGHT_ON, CENTER-LIGHT_OFF, LIVINGROOM-LIGHT_ON, LIVINGROOM-LIGHT_OFF");
        Console.out.println("  TV-LIGHT_ON, TV-LIGHT_OFF, KITCHEN-LIGHT_ON, KITCHEN-LIGHT_OFF");
        Console.out.println("  BEDROOM-1-LIGHT_ON, BEDROOM-1-LIGHT_OFF");
        Console.out.println("  BEDROOM-2-LIGHT_ON, BEDROOM-2-LIGHT_OFF");
        Console.out.println("  TOILET-LIGHT_ON, TOILET-LIGHT_OFF, GARDEN-LIGHT_ON, GARDEN-LIGHT_OFF");
    }

    protected void initialize() throws Exception
    {
    }

    public static void main(String[] args) throws CommandFailedException
    {
        new ControlCommand().execute(new String[] { "¬Oªº" });
    }
}
