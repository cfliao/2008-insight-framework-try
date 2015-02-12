package s2h.sysutils.msgs;

import java.io.IOException;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.device.InvalidDescriptionException;

import s2h.platform.node.PlatformTopic;
import s2h.platform.node.Sendable;

public class MessageSenderUPnPAdapter extends Device implements ActionListener
{
    private final static String DESCRIPTION_FILE_NAME = "s2h/sysutils/msgs/messageSenderDevice.xml";

    private Sendable sender;

    public MessageSenderUPnPAdapter() throws InvalidDescriptionException, IOException
    {
        super(DESCRIPTION_FILE_NAME);

        Action getPowerAction = getAction("Send");
        getPowerAction.setActionListener(this);

        // ServiceList serviceList = getServiceList();
        // Service service = serviceList.getService(0);
        // service.setQueryListener(this);

        // content = getStateVariable("content");
    }

    // private StateVariable content;

    // public void setPowerState(String state)
    // {
    // if ("1".equals(state) || "true".equals(state))
    // {
    // // on();
    // } else
    // {
    // // off();
    // }
    // }

    // public String getPowerState()
    // {
    // // if (onFlag == true)
    // // return "1";
    // return "0";
    // }

    public boolean actionControlReceived(Action action)
    {
        String actionName = action.getName();

        boolean ret = false;

        if ("Send".equals(actionName))
        {
            Argument contentArg = action.getArgument("content");
            // System.out.println(contentArg.getValue());
            Argument topicArg = action.getArgument("topic");
            sender.send(contentArg.getValue(), PlatformTopic.fromString(topicArg.getValue()));
            ret = true;
        }
        // if ("SetPower".equals(actionName))
        // {
        // Argument powerArg = action.getArgument("Power");
        // String state = powerArg.getValue();
        // setPowerState(state);
        // state = getPowerState();
        // Argument resultArg = action.getArgument("Result");
        // resultArg.setValue(state);
        // ret = true;
        // }

        return ret;
    }

    // public boolean queryControlReceived(StateVariable stateVar)
    // {
    // stateVar.setValue(getPowerState());
    // return true;
    // }

    public static void main(String[] args)
    {

    }

    public void setSender(Sendable sender)
    {
       this.sender = sender;
    }

}
