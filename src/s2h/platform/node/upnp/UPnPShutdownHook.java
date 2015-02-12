package s2h.platform.node.upnp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cybergarage.upnp.Device;

public class UPnPShutdownHook implements Runnable
{
    private Device upnpDevice;

    private Log logger = LogFactory.getLog(this.getClass().getName());

    public UPnPShutdownHook(Device device)
    {
        upnpDevice = device;
    }

    @Override
    public void run()
    {
        logger.info("shutdowning device:" + upnpDevice.getFriendlyName());
        synchronized (upnpDevice)
        {
            if (upnpDevice != null)
            {
                upnpDevice.stop();
            }
        }
    }

}
