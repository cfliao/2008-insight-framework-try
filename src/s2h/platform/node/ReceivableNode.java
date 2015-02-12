package s2h.platform.node;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Place hoilder for ActuatorNode and LogicNode
 * 
 * @author Chun-Feng Liao (2008/2/11)
 */
public abstract class ReceivableNode extends BaseNode implements Receivable
{
    public ReceivableNode(String name)
    {
        super(name);
    }

    private Log logger = LogFactory.getLog(this.getIdentity());
    private PlatformTopic consumingTopic;
    
    @Override
    final public void onMessage(Message msg)
    {
        // process message only when the service is active
        if (getNodeState().equals(NodeState.ACTIVE))
        {
            processMessage(PlatformMessage.fromTextMessage((TextMessage) msg));
        }
    }

    abstract protected void processMessage(PlatformMessage message);

    @Override
    public void setConsumingTopic(PlatformTopic consumingTopic)
    {
        this.consumingTopic = consumingTopic;
        logger.info("Listening to Topic: " + this.getConsumingTopic().toString());
    }

    @Override
    public PlatformTopic getConsumingTopic()
    {
        return consumingTopic;
    }
}
