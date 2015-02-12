package s2h.platform.node;

import javax.jms.MessageListener;

public interface Receivable extends MessageListener
{
    public void setConsumingTopic(PlatformTopic consumingTopic);
    
    public PlatformTopic getConsumingTopic();
}
