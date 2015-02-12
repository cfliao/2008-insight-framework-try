package s2h.platform.instrument.techcity;

import static s2h.platform.support.PlatformConfig.CONTROL_SERVER_IP;
import static s2h.platform.support.PlatformConfig.CONTROL_SERVER_PORT;

import java.util.HashMap;
import java.util.Map;


/**
 * Hc12ControlService
 *
 * @author Chun-Feng Liao (2007/11/9)
 */
public class Hc12ControlService implements BinaryControlService, InfraredControlService
{
    //private Log logger = LogFactory.getLog(Hc12ControlService.class.getPackage().getName());

    private HC12Controller controlServer;
    private String controlServerID;
    
    public Hc12ControlService(String controlServerID)
    {
        this.controlServerID = controlServerID;
    }
    
    private void validateServerState()
    {
        if (controlServer == null)
            throw new IllegalStateException("please call init() of service first!");
    }

    public void stop()
    {
        controlServer = null;
    }

    public void start()
    {
        controlServer = new HC12Controller(CONTROL_SERVER_IP, CONTROL_SERVER_PORT, controlServerID);

        onTable.put(DeviceId.ZONE_1_DEVICE_1, Short.valueOf(HC12ControlConstants.KEY_A__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_2, Short.valueOf(HC12ControlConstants.KEY_B__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_3, Short.valueOf(HC12ControlConstants.KEY_C__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_4, Short.valueOf(HC12ControlConstants.KEY_D__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_5, Short.valueOf(HC12ControlConstants.KEY_E__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_6, Short.valueOf(HC12ControlConstants.KEY_F__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_7, Short.valueOf(HC12ControlConstants.KEY_G__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_8, Short.valueOf(HC12ControlConstants.KEY_H__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_9, Short.valueOf(HC12ControlConstants.KEY_I__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_10, Short.valueOf(HC12ControlConstants.KEY_J__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_11, Short.valueOf(HC12ControlConstants.KEY_K__ON));
        onTable.put(DeviceId.ZONE_1_DEVICE_12, Short.valueOf(HC12ControlConstants.KEY_L__ON));

        onTable.put(DeviceId.ZONE_2_DEVICE_1, Short.valueOf(HC12ControlConstants.KEY_LAMP2_1__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_2, Short.valueOf(HC12ControlConstants.KEY_LAMP2_2__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_3, Short.valueOf(HC12ControlConstants.KEY_LAMP2_3__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_4, Short.valueOf(HC12ControlConstants.KEY_LAMP2_4__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_5, Short.valueOf(HC12ControlConstants.KEY_LAMP2_5__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_6, Short.valueOf(HC12ControlConstants.KEY_LAMP2_6__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_7, Short.valueOf(HC12ControlConstants.KEY_LAMP2_7__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_8, Short.valueOf(HC12ControlConstants.KEY_LAMP2_8__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_9, Short.valueOf(HC12ControlConstants.KEY_LAMP2_9__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_10, Short.valueOf(HC12ControlConstants.KEY_LAMP2_10__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_11, Short.valueOf(HC12ControlConstants.KEY_LAMP2_11__ON));
        onTable.put(DeviceId.ZONE_2_DEVICE_12, Short.valueOf(HC12ControlConstants.KEY_LAMP2_12__ON));

        offTable.put(DeviceId.ZONE_1_DEVICE_1, Short.valueOf(HC12ControlConstants.KEY_A__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_2, Short.valueOf(HC12ControlConstants.KEY_B__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_3, Short.valueOf(HC12ControlConstants.KEY_C__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_4, Short.valueOf(HC12ControlConstants.KEY_D__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_5, Short.valueOf(HC12ControlConstants.KEY_E__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_6, Short.valueOf(HC12ControlConstants.KEY_F__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_7, Short.valueOf(HC12ControlConstants.KEY_G__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_8, Short.valueOf(HC12ControlConstants.KEY_H__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_9, Short.valueOf(HC12ControlConstants.KEY_I__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_10, Short.valueOf(HC12ControlConstants.KEY_J__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_11, Short.valueOf(HC12ControlConstants.KEY_K__OFF));
        offTable.put(DeviceId.ZONE_1_DEVICE_12, Short.valueOf(HC12ControlConstants.KEY_L__OFF));

        offTable.put(DeviceId.ZONE_2_DEVICE_1, Short.valueOf(HC12ControlConstants.KEY_LAMP2_1__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_2, Short.valueOf(HC12ControlConstants.KEY_LAMP2_2__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_3, Short.valueOf(HC12ControlConstants.KEY_LAMP2_3__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_4, Short.valueOf(HC12ControlConstants.KEY_LAMP2_4__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_5, Short.valueOf(HC12ControlConstants.KEY_LAMP2_5__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_6, Short.valueOf(HC12ControlConstants.KEY_LAMP2_6__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_7, Short.valueOf(HC12ControlConstants.KEY_LAMP2_7__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_8, Short.valueOf(HC12ControlConstants.KEY_LAMP2_8__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_9, Short.valueOf(HC12ControlConstants.KEY_LAMP2_9__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_10, Short.valueOf(HC12ControlConstants.KEY_LAMP2_10__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_11, Short.valueOf(HC12ControlConstants.KEY_LAMP2_11__OFF));
        offTable.put(DeviceId.ZONE_2_DEVICE_12, Short.valueOf(HC12ControlConstants.KEY_LAMP2_12__OFF));

        applianceTable.put(ApplianceId.AMP, Short.valueOf(HC12ControlConstants.DEV_AMP));
        applianceTable.put(ApplianceId.AUX, Short.valueOf(HC12ControlConstants.DEV_AUX));
        applianceTable.put(ApplianceId.CATV, Short.valueOf(HC12ControlConstants.DEV_CATV));
        applianceTable.put(ApplianceId.CD, Short.valueOf(HC12ControlConstants.DEV_CD));
        applianceTable.put(ApplianceId.DVD, Short.valueOf(HC12ControlConstants.DEV_DVD));
        applianceTable.put(ApplianceId.STB, Short.valueOf(HC12ControlConstants.DEV_STB));
        applianceTable.put(ApplianceId.TV, Short.valueOf(HC12ControlConstants.DEV_TV));

        functionTable.put(FunctionId.POWER, Short.valueOf(HC12ControlConstants.POWER));
        functionTable.put(FunctionId.ENTER, Short.valueOf(HC12ControlConstants.MENU));
        functionTable.put(FunctionId.PLAY, Short.valueOf(HC12ControlConstants.PLAY));
        functionTable.put(FunctionId.FORWARD, Short.valueOf(HC12ControlConstants.FORWARD));
        functionTable.put(FunctionId.BACKWARD, Short.valueOf(HC12ControlConstants.BACKWARD));
        functionTable.put(FunctionId.UP, Short.valueOf(HC12ControlConstants.UP));
        functionTable.put(FunctionId.DOWN, Short.valueOf(HC12ControlConstants.DOWN));
        functionTable.put(FunctionId.LEFT, Short.valueOf(HC12ControlConstants.LEFT));
        functionTable.put(FunctionId.RIGHT, Short.valueOf(HC12ControlConstants.RIGHT));
        functionTable.put(FunctionId.PAUSE, Short.valueOf(HC12ControlConstants.PAUSE));
        functionTable.put(FunctionId.STOP, Short.valueOf(HC12ControlConstants.STOP));
        functionTable.put(FunctionId.VOL_UP, Short.valueOf(HC12ControlConstants.VOL_UP));
        functionTable.put(FunctionId.VOL_DOWN, Short.valueOf(HC12ControlConstants.VOL_DOWN));
        functionTable.put(FunctionId.NEXT_CHANNEL, Short.valueOf(HC12ControlConstants.CH_UP));
        functionTable.put(FunctionId.PREVIOUS_CHANNEL, Short.valueOf(HC12ControlConstants.CH_DOWN));
        functionTable.put(FunctionId.INPUT, Short.valueOf(HC12ControlConstants.TV_VIDEO));
        functionTable.put(FunctionId.MUTE, Short.valueOf(HC12ControlConstants.MUTE));
        functionTable.put(FunctionId.NEXT, Short.valueOf(HC12ControlConstants.NEXT));
        functionTable.put(FunctionId.PREVIOUS, Short.valueOf(HC12ControlConstants.PREVIOUS));
        functionTable.put(FunctionId._1, Short.valueOf(HC12ControlConstants._1));
        functionTable.put(FunctionId._2, Short.valueOf(HC12ControlConstants._2));
        functionTable.put(FunctionId._3, Short.valueOf(HC12ControlConstants._3));
        functionTable.put(FunctionId._4, Short.valueOf(HC12ControlConstants._4));
        functionTable.put(FunctionId._5, Short.valueOf(HC12ControlConstants._5));
        functionTable.put(FunctionId._6, Short.valueOf(HC12ControlConstants._6));
        functionTable.put(FunctionId._7, Short.valueOf(HC12ControlConstants._7));
        functionTable.put(FunctionId._8, Short.valueOf(HC12ControlConstants._8));
        functionTable.put(FunctionId._9, Short.valueOf(HC12ControlConstants._9));
        functionTable.put(FunctionId._10, Short.valueOf(HC12ControlConstants._10));
        functionTable.put(FunctionId._11, Short.valueOf(HC12ControlConstants._11));
        functionTable.put(FunctionId._12, Short.valueOf(HC12ControlConstants._12));

    }

    private static Map<DeviceId, Short> onTable = new HashMap<DeviceId, Short>();

    private static Map<DeviceId, Short> offTable = new HashMap<DeviceId, Short>();

    private static Map<ApplianceId, Short> applianceTable = new HashMap<ApplianceId, Short>();

    private static Map<FunctionId, Short> functionTable = new HashMap<FunctionId, Short>();

    public void turnOff(DeviceId did)
    {
        validateServerState();
        try
        {
            //logger.debug("device " + did + " is turned off");
            controlServer.binaryControl(offTable.get(did).shortValue());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void turnOn(DeviceId did)
    {
        validateServerState();
        try
        {
            //logger.debug("device " + did + " is turned on");
            controlServer.binaryControl(onTable.get(did).shortValue());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void issue(ApplianceId aid, FunctionId fid)
    {
        validateServerState();
        try
        {
            //logger.debug("ApplianceId: " + aid + ", FunctionId: " + fid);
            controlServer.infraredControl(applianceTable.get(aid).shortValue(), functionTable.get(fid).shortValue());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
