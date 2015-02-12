package s2h.platform.node;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import s2h.platform.annotation.ListenTopic;

/**
 * ReceivableNodeContainer
 * 
 * @author Chun-Feng Liao (2008/2/11)
 */
public class ReceivableNodeContainer implements NodeContainer
{
    private Log logger = LogFactory.getLog(ReceivableNodeContainer.class.getPackage().getName());

    private ReceivableNode node;

    private NodeManager manager;

    private ConnectionFactory jmsFactory;

    private Connection connection;

    public void setNode(ReceivableNode node)
    {
        this.node = node;
    }

    public void setManager(NodeManager manager)
    {
        this.manager = manager;
    }

    public void start()
    {
        try
        {
            connection = jmsFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // �� annotation �`�J topic
            tryInjectionTopicByAnnotation();

            MessageConsumer consumer = session.createConsumer(PlatformTopic.toActiveMQTopic(node.getConsumingTopic()));
            consumer.setMessageListener(node);
            node.activate();
            connection.start();
        }
        catch (JMSException e)
        {
            logger.error("JMS�s������!");
            e.printStackTrace();
            System.exit(1);
        }
        // start the node manager
        new Thread(manager).start();
    }

    /**
     * �յۥ� Annotation �`�J Consuming Topic
     */
    protected void tryInjectionTopicByAnnotation()
    {
        ListenTopic topic = node.getClass().getAnnotation(ListenTopic.class);

        if (topic == null || !(node instanceof Receivable))
        {
            return; // �䤣�� annotation �Τ��O Receivable �N�S���n�~��U�h
        }

        Receivable receivable = (Receivable) node;
        if (PlatformTopic.NULL.equals(topic.topic()) && receivable.getConsumingTopic() == null)
        {
            // TODO: ���򳣨S�]���ɭԻݭn��X�ҥ~��?
            logger.warn("never assign a valid topic, use default topic: " + topic.topic());
        }

        logger.info("injection consuming topic by @annotaion. topic: " + topic.topic());
        receivable.setConsumingTopic(topic.topic());
    }

    public void stop()
    {
        try
        {
            connection.close();
        }
        catch (JMSException e)
        {
        }
    }

    public void setJmsFactory(ConnectionFactory jmsFactory)
    {
        this.jmsFactory = jmsFactory;
    }

}
