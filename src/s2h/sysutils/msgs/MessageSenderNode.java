package s2h.sysutils.msgs;

import s2h.platform.annotation.UPnPParameter;
import s2h.platform.node.NodeRunner;
import s2h.platform.node.SensorNode;
import s2h.platform.node.upnp.UPnPShutdownHook;

@UPnPParameter(friendlyName = "Message Dispatching Node")
public class MessageSenderNode extends SensorNode
{
    private MessageSenderUPnPAdapter upnp;

    public MessageSenderNode()
    {
        super(MessageSenderNode.class.getName());
    }

    public static void main(String[] args)
    {
        new NodeRunner(MessageSenderNode.class).execute();
    }

    @Override
    protected void onShutdown()
    {
        upnp.stop();
        upnp.byebye();
    }

    @Override
    protected void onActivate()
    {
        try
        {
            if (upnp == null)
            {
                upnp = new MessageSenderUPnPAdapter();
                upnp.setSender(getSender());
                Runtime.getRuntime().addShutdownHook(new Thread(new UPnPShutdownHook(upnp)));
            }
            upnp.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRest()
    {
        upnp.stop();
        upnp.byebye();
    }

}
