package s2h.platform.mom;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import s2h.platform.support.UUIDUtils;

public class HomeMessageBus2
{

    public static void main(String[] args) throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("s2h/platform/mom/app-config.xml");
        BrokerService broker = (BrokerService) context.getBean("broker");
        broker.setBrokerName(UUIDUtils.getUUID());
        broker.setSupportFailOver(true);
        System.out.println("broker name: "+broker.getBrokerName());
        broker.start();
        
        
        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }

}
