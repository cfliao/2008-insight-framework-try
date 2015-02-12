package s2h.platform.node;


/**
 * A node with both message sending and receiving capabilities.
 * 
 * @author Chun-Feng Liao (2007.11.7)
 * @since 2.0
 */
public abstract class LogicNode extends ReceivableNode
{

    private Sendable sender;
    
    //private Log logger = LogFactory.getLog(this.getIdentity());

    public LogicNode(String name)
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
