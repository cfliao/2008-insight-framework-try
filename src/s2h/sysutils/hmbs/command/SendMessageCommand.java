package s2h.sysutils.hmbs.command;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import s2h.platform.node.PlatformTopic;
import s2h.platform.node.Sendable;
import s2h.sysutils.hmbs.CommandFailedException;
import s2h.sysutils.hmbs.Console;
import s2h.sysutils.hmbs.ConsoleCommand;

public class SendMessageCommand extends ConsoleCommand
{
    private ApplicationContext context;

    private Sendable sender;

    public SendMessageCommand()
    {
        super(NAME, DESC, HELP);
        context = new ClassPathXmlApplicationContext("s2h/sysutils/hmbs/command/shell-config.xml");
        sender = (Sendable) context.getBean("sender");
    }

    private static final String NAME = "sendmsg";

    private static final String DESC = "send a message to a specific topic";

    private static final String HELP = "sendmsg <TOPIC> <TEXT MESSAGE>";

    public void execute(String[] args) throws CommandFailedException
    {
        try
        {
            sender.send(args[1], PlatformTopic.fromString(args[0]));

            Console.out.println("message sent.");
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
        Console.out.println("syntax:\nsendmsg <TOPIC> <MESSAGE>");
    }

    protected void initialize() throws Exception
    {
    }

    public static void main(String[] args) throws CommandFailedException
    {
        new SendMessageCommand().execute(new String[] { "123", "RAW_DATA" });
    }
}
