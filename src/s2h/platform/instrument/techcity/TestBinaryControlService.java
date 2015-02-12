package s2h.platform.instrument.techcity;

import s2h.platform.support.PlatformConfig;


/**
 * TestBinaryControlService
 *
 * @author Chun-Feng Liao (2007/11/9)
 */
public class TestBinaryControlService
{

    public static void main(String[] args) throws InterruptedException
    {
      Hc12ControlService controlService = new Hc12ControlService(PlatformConfig.BL313_CONTROL_SERVER_ID);
      
      controlService.start();
      controlService.issue(ApplianceId.TV, FunctionId.NEXT_CHANNEL);
      Thread.sleep(3000);
      controlService.issue(ApplianceId.TV, FunctionId.PREVIOUS_CHANNEL);
//      int cmd = 0;
//      while (true)
//      {
//          // int cmd = rand.nextInt(2);
//          switch (cmd)
//          {
//          case 0:
//              controlService.turnOn(ZoneId._1,DeviceId._3);
//              System.out.println("on");
//              break;
//          case 1:
//              controlService.turnOff(ZoneId._1,DeviceId._3);
//              System.out.println("off");
//              break;
//          }
//          Thread.sleep(3000);
//          cmd = (cmd + 1) % 2;
//      }
      
    }

}
