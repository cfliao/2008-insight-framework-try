package s2h.platform.node.upnp;

import java.io.IOException;

import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.InvalidDescriptionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import s2h.platform.support.UUIDUtils;

public class UPnPNodeManagerOld
{
    private final static String DEVICE_DESC_TEMPLATE_PATH = "s2h/platform/node/upnp/device.xml";

    private Device device;

    private Element deviceElement;

    private Namespace ns;

    public UPnPNodeManagerOld()
    {
        SAXBuilder builder = new SAXBuilder();
        try
        {
            Document doc = builder.build(new ClassPathResource(DEVICE_DESC_TEMPLATE_PATH).getFile());
            ns = Namespace.getNamespace("urn:schemas-upnp-org:device-1-0");

            deviceElement = doc.getRootElement().getChild("device", ns);
            Element udnElement = deviceElement.getChild("UDN", ns);
            udnElement.setText("uuid:" + UUIDUtils.getUUID());
           
        }
        catch (JDOMException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void setDeviceType(String deviceType)
    {
        Element deviceTypeElement = deviceElement.getChild("deviceType", ns);
        deviceTypeElement.setText("urn:schemas-upnp-org:device:" + deviceType + ":1");

    }

    public void setServiceType(String serviceType)
    {
        Element element = deviceElement.getChild("serviceList", ns).getChild("service", ns).getChild("serviceType", ns);
        element.setText("urn:schemas-upnp-org:service:" + serviceType + ":1");
    }

    public void setFriendlyName(String friendlyName)
    {
        Element friendlyNameElement = deviceElement.getChild("friendlyName", ns);
        friendlyNameElement.setText(friendlyName);

    }

    public void setModelName(String modelName)
    {
        Element element = deviceElement.getChild("modelName", ns);
        element.setText(modelName);
        Element serviceId = deviceElement.getChild("serviceList", ns).getChild("service", ns).getChild("serviceId", ns);
        serviceId.setText("urn:schemas-upnp-org:serviceId:" + modelName + ":1");
        Element controlUrl = deviceElement.getChild("serviceList", ns).getChild("service", ns).getChild("controlURL", ns);
        controlUrl.setText("/service/" + modelName + "/control");
        Element eventSubUrl = deviceElement.getChild("serviceList", ns).getChild("service", ns).getChild("eventSubURL", ns);
        eventSubUrl.setText("/service/" + modelName + "/eventSub");
    }

    public void output()
    {
        XMLOutputter output = new XMLOutputter();
        System.out.println(output.outputString(deviceElement.getDocument()));
    }

    public void start()
    {
        device = new Device();
        //XMLOutputter output = new XMLOutputter();
        try
        {
            device.loadDescription(new ClassPathResource(DEVICE_DESC_TEMPLATE_PATH).getFile());
            //device.loadDescriptionContent(output.outputString(deviceElement.getDocument()));
        }
        catch (InvalidDescriptionException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
       //System.out.println("start");
        device.start();
        
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // new UPnPNodeManager1();

        ApplicationContext context = new ClassPathXmlApplicationContext("s2h/platform/node/upnp/app-config.xml");
        UPnPNodeManagerOld manager = (UPnPNodeManagerOld) context.getBean("nodeManager");

        manager.output();
        manager.start();
    }

}
