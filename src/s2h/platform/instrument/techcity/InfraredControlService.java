package s2h.platform.instrument.techcity;

/**
 * This interface provides key abstractions of Infrared control on the "E-Live Home Controller". In this mode, the
 * frequencies of infrared remote controller provided by appliances should be learned by the "E-Live Home Controller"
 * first (see manual). Supported devices : Appliances that have a infrared remote controller. Infrared mode control
 * command = (<b>ApplianceId</b>, <b>FunctionId</b>)
 * 
 * @author Chun-Feng Liao
 * @version 1.0
 */
public interface InfraredControlService
{
    public void issue(ApplianceId aid, FunctionId fid);
}
