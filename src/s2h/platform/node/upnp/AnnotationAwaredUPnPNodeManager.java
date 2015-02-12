package s2h.platform.node.upnp;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cybergarage.upnp.device.InvalidDescriptionException;

import s2h.platform.annotation.UPnPParameter;
import s2h.platform.config.UPnPConfig;
import s2h.platform.config.UPnPConfigFactory;
import s2h.platform.node.NodeManager;
import s2h.platform.node.PervasiveNode;

public class AnnotationAwaredUPnPNodeManager implements NodeManager
{
	static Log log = LogFactory.getLog(AnnotationAwaredUPnPNodeManager.class);
    private UPnPNodeManagerDevice device;
    private PervasiveNode node;

    @Override
    public void run()
    {

    	UPnPParameter params = node.getClass().getAnnotation(UPnPParameter.class);
    	log.debug("UPnPParameter: "+ params);

    	UPnPConfig cfg = UPnPConfigFactory.createConfig(params);
    	if(StringUtils.isBlank(params.modelName()) || StringUtils.isBlank(params.friendlyName())){
    		cfg = UPnPConfigFactory.createConfig(params, node.getClass().getPackage());
    	}

    	log.debug("UPnPConfig.toString: "+ cfg);

        try {
			device = new UPnPNodeManagerDevice(
					cfg.getDeviceDescription(), cfg.getServiceName(), cfg.getServiceDescription(), node);
		} catch (InvalidDescriptionException e) {
			log.error("", e);
		}

        Runtime.getRuntime().addShutdownHook(new Thread(new UPnPShutdownHook(device)));
        device.start();
    }

    public void setNode(PervasiveNode node)
    {
        this.node = node;
    }


}
