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
 *  可以使用"_"來代表空白 
 * 
 * @author try
 */
public class TtsCommand extends ConsoleCommand
{
    private ApplicationContext context;

    private Sendable sender;

    public TtsCommand()
    {
        super(NAME, DESC, HELP);
        context = new ClassPathXmlApplicationContext("s2h/sysutils/hmbs/command/shell-config.xml");
        sender = (Sendable) context.getBean("sender");
    }

    private static final String NAME = "tts";

    private static final String DESC = "send a message to tts topic";

    private static final String HELP = "tts <the_text_message_seperate_with_underscore>";

    public void execute(String[] args) throws CommandFailedException
    {
        try
        {
            String prompt = args[0];

            if (StringUtils.trimToNull(prompt) != null)
            {
                prompt = StringUtils.replace(prompt, "_", " ");
                
                sender.send(prompt, PlatformTopic.HCI_TTS);

                Console.out.println("tts message sent:" + prompt);
            } else
                printSyntax();
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
    }

    protected void initialize() throws Exception
    {
    }

    public static void main(String[] args) throws CommandFailedException
    {
        new TtsCommand().execute(new String[]
        {
            "是的"
        });
    }
}
