package s2h.platform.node.upnp;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.StateVariable;
import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.control.QueryListener;
import org.cybergarage.upnp.device.InvalidDescriptionException;

import s2h.platform.node.PervasiveNode;
import s2h.platform.support.UUIDUtils;

/**
 * @author Chun-Feng Liao
 * @since 2007.12.27
 */
public class UPnPNodeManagerDevice extends Device implements ActionListener, QueryListener
{
    private final static long SHUTDOWN_WAIT_TIME = 2000;

    private Log logger = LogFactory.getLog(getClass().getName());

    private String applicationName;

    // private MessageDispatcher messageDispatcher;

    private PervasiveNode node; // 要管理的對象

    private StateVariable state;

    public UPnPNodeManagerDevice(String deviceFilePath, PervasiveNode node) throws InvalidDescriptionException, IOException
    {
        super(deviceFilePath);
        init(node);
    }

    @SuppressWarnings("unchecked")
	public UPnPNodeManagerDevice(String deviceDescription, String serviceName, String serviceDescription, PervasiveNode node) throws InvalidDescriptionException {
    	super();
    	loadDescription(deviceDescription);
    	logger.debug("device description is: " + deviceDescription);

		Service service = getService(serviceName);
		if (service != null && service.loadSCPD(serviceDescription)) {
			logger.info("load service description success.");
			logger.debug("service description is: " + serviceDescription);
		}

		for (Iterator<Service> it = getServiceList().iterator(); it.hasNext();) {
			it.next().setQueryListener(this);
		}

		init(node);

    }

	private void init(PervasiveNode node) {
		this.setUDN("uuid:"+UUIDUtils.getUUID());
        this.node = node;
        getAction("Activate").setActionListener(this);
        getAction("Rest").setActionListener(this);
        getAction("GetState").setActionListener(this);
        getAction("Shutdown").setActionListener(this);
        applicationName = node.getClass().getPackage().getName();
        logger.info("UPnP Node Manager for [" + applicationName + "] activated.");
        state = getStateVariable("state");
        state.setValue(node.getNodeState().toString());
	}

    @Override
    public boolean actionControlReceived(Action action)
    {
        String actionName = action.getName();

        boolean ret = false;

        if ("Activate".equals(actionName))
        {
            node.activate();
            state.setValue(node.getNodeState().toString());
            ret = true;
        } else if ("Rest".equals(actionName))
        {
            node.rest();
            state.setValue(node.getNodeState().toString());
            ret = true;
        } else if ("GetState".equals(actionName))
        {
            Argument returnArg = action.getArgument("state");
            returnArg.setValue(state.getValue());
            ret = true;
            // logger.debug("GetNodeState");
        } else if ("Shutdown".equals(actionName))
        {
            // System.out.println("shutdown");
            new Thread() {
                public void run()
                {
                    try
                    {
                        Thread.sleep(SHUTDOWN_WAIT_TIME);
                        logger.info("Shutting down in " + SHUTDOWN_WAIT_TIME + " ms...");
                    }
                    catch (InterruptedException e)
                    {
                    }
                    System.exit(0);
                }
            }.start();

            ret = true;
        }

        return ret;
    }

    @Override
    public boolean queryControlReceived(StateVariable stateVar)
    {
        boolean ret = false;
        if ("state".equals(stateVar.getName()))
        {
            ret = true;
        }
        return ret;
    }

}
