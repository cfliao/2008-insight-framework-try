package s2h.platform.mom;

import org.apache.activemq.broker.BrokerService;

public class HomeMessageBus
{

    public static void main(String[] args) throws Exception
    {
        BrokerService broker = new BrokerService();
        broker.setUseJmx(false);
        broker.setSupportFailOver(true);
        broker.addConnector("tcp://localhost:61616");
        //broker.addNetworkConnector("discovery:(multicast://default)");
        broker.addNetworkConnector("multicast://default");
        broker.setPersistent(false);
        //broker.addConnector(new NIOTransport());
        broker.start();

        // now lets wait forever to avoid the JVM terminating immediately
        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }

}
