package s2h.platform.instrument.techcity;

/**
 * This interface provides key abstractions of Lighting / Switching control on the "E-Live Home Controller". In this
 * mode, the device can switch between on / off state.<br>
 * Supported devices : light control (LS-201,LS-202) and wireless power switch (PS-101, PS-201). Binary mode control
 * command = (<b>ZoneId</b>, <b>DeviceId</b>)
 * 
 * @author Chun-Feng Liao
 * @version 1.0
 */
public interface BinaryControlService 
{
    public void turnOn(DeviceId did);

    public void turnOff(DeviceId did);
}
