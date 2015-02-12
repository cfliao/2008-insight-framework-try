package s2h.platform.node;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Wrapper class of JMS TextMessage. Used to remove the dependency on JMS.
 * 
 * @author Chun-Feng Liao (2007.11.7)
 * @since 2.0
 */
public class PlatformMessage
{
    private Log logger = LogFactory.getLog(PlatformMessage.class.getPackage().getName());
    
    private TextMessage message;
    
    public static PlatformMessage fromTextMessage(TextMessage message)
    {
        PlatformMessage pm = new PlatformMessage();
        pm.message = message;
        return pm;
    }

    private PlatformMessage()
    {
        super();
    }

    public String getContent()
    {
        try
        {
            return message.getText();
        }
        catch (JMSException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return null;
    }

    public String getStringProperty(String name)
    {
        try
        {
            return message.getStringProperty(name);
        }
        catch (JMSException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    public Object getObjectProperty(String name)
    {
        try
        {
            return message.getObjectProperty(name);
        }
        catch (JMSException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

}
