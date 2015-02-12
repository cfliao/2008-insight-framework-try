package s2h.platform.node;


/**
 * ¾A¥Î¹ï¶H: SensorNode
 *
 * @author Chun-Feng Liao (2008/2/11)
 */
public class SimpleNodeContainer implements NodeContainer
{
   // private Log logger = LogFactory.getLog(NodeContainer.class.getPackage().getName());

    private SimpleNode node;

    private NodeManager manager;

    public void setNode(SimpleNode node)
    {
        this.node = node;
    }

    public void setManager(NodeManager manager)
    {
        this.manager = manager;
    }

    public void start()
    {
        node.activate();
        // start the node manager
        new Thread(manager).start();
    }

    public void stop()
    {
            node.rest();
    }

}
