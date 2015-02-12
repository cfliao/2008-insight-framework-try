package s2h.platform.node.upnp;

import java.io.IOException;

import org.cybergarage.upnp.device.InvalidDescriptionException;

import s2h.platform.node.NodeManager;
import s2h.platform.node.PervasiveNode;

public class UPnPNodeManager implements NodeManager
{
    private UPnPNodeManagerDevice device;

    private String deviceFilePath;

    private PervasiveNode node;
    
    @Override
    public void run()
    {
        try
        {
            device = new UPnPNodeManagerDevice(deviceFilePath,node);
        }
        catch (InvalidDescriptionException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        Runtime.getRuntime().addShutdownHook(new Thread(new UPnPShutdownHook(device)));
        device.start();
    }

    public void setDeviceFilePath(String deviceFilePath)
    {
        this.deviceFilePath = deviceFilePath;
    }

    public void setNode(PervasiveNode node)
    {
        this.node = node;
    }

    
}
