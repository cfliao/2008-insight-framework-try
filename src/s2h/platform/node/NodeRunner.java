package s2h.platform.node;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NodeRunner
{
    protected NodeContainer app;
    protected String configFilePath;

    /**
     * @param nodeClazz the node class
     * suppose the default config file locate at the same package with node class
     * and the default container name is `container'
     */
    public NodeRunner(Class<? extends PervasiveNode> nodeClazz) {
		this(nodeClazz.getPackage().getName().replace('.', '/') + "/app-config.xml", "container");
	}

    private NodeRunner(String path, String containerName)
    {
        this.configFilePath = path.startsWith("/") ? path : "/" + path;
        ApplicationContext context = new ClassPathXmlApplicationContext(configFilePath);
        app = (NodeContainer) context.getBean(containerName);
    }

    public void execute() {

		// check the pre-condition
		if (app == null) {
			throw new java.lang.IllegalArgumentException("找不到設定檔:" + configFilePath);
		}

		// when anything is OK, start the node.
		app.start();
	}
}
