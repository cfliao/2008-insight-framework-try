package s2h.platform.node;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import s2h.platform.support.PlatformConfig;


public class MessageSender implements Sendable
{

    private Log logger = LogFactory.getLog(MessageSender.class.getPackage().getName());

    private JmsTemplate jmsTemplate;

    @Override
    public void send(final String message, PlatformTopic topicName)
    {
        if (!validate(topicName))
        {
            throw new IllegalArgumentException("no such Topic:" + topicName);
        }

        try
        {
            if (PlatformTopic.HCI_TTS.equals(topicName) || PlatformTopic.HCI_SR.equals(topicName))
            {
                jmsTemplate.send(new ActiveMQTopic(topicName.toString()), new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException
                    {
                        Message m = session.createTextMessage(PlatformConfig.SPEECH_MSG_IDENTIFIER);
                        m.setStringProperty(PlatformConfig.SPEECH_PROP_PLALCEHOLDER_KEY, message);
                        return m;
                    }
                });
            } else
            {
                jmsTemplate.convertAndSend(new ActiveMQTopic(topicName.toString()), message);
                logger.debug("Sending message to (" + topicName + ") :-->" + message + "<--");
            }
        }
        catch (JmsException ex)
        {
            logger.warn("Can't connect to HMB, message not delivered.");
        }

    }

    private boolean validate(PlatformTopic topic)
    {
        boolean result = false;
        PlatformTopic[] topics = PlatformTopic.values();
        for (PlatformTopic h : topics)
        {
            if (h.equals(topic))
            {
                result = true;
                break;
            }
        }
        return result;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

    public JmsTemplate getJmsTemplate()
    {
        return jmsTemplate;
    }

}
