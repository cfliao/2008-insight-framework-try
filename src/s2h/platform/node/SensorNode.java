package s2h.platform.node;


/**
 * 
 * @author Chun-Feng Liao (2008/2/11)
 * @since 2.0
 */
public abstract class SensorNode extends SimpleNode
{
    private Sendable sender;
    
    public SensorNode(String name)
    {
        super(name);
    }
    
    protected Sendable getSender()
    {
        return sender;
    }

    public void setSender(Sendable sender)
    {
        this.sender = sender;
    }

}
