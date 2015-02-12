/** 
 * Copyright 2007 National Taiwan University CSIE Dept. Intelligent Robot & Automation Lab
 */
package s2h.platform.node;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * �B�z�ͩR�g���BNodeName�ΦL�s���Y�Ʃy
 * 
 * @author Chun-Feng Liao (2007.11.7)
 * @since 2.0
 */
abstract class BaseNode implements PervasiveNode
{
    private String name;

    private NodeState state = NodeState.DORMANT; // node state �@�}�l�Odormant

    private Log logger = LogFactory.getLog(this.getPackageIdentity());

    final public String getName()
    {
        return name;
    }

    public BaseNode(String name)
    {
        this.name = name;
        printTitle();
    }

    @Override
    final public NodeState getNodeState()
    {
        return state;
    }

    @Override
    final public void activate()
    {
        if (state != NodeState.ACTIVE)
        {
            state = NodeState.ACTIVE;
            logger.info("Node service activated.");
            onActivate();
        } else
        {
            logger.info("Node service already activated.");
        }
    }

    @Override
    final public void rest()
    {
        if (state != NodeState.DORMANT)
        {
            state = NodeState.DORMANT;
            logger.info("Node is dormant.");
            onRest();
        } else
        {
            logger.info("Node is already dormant.");
        }
    }
    
    @Override
    final public void shutdown()
    {
            onShutdown();
    }

    /**
     * �^�����O����
     * 
     * @return Full qualified class name
     */
    public final String getIdentity()
    {
        return getClass().getName();
    }
    
    /**
     * �^�ǮM�����
     * 
     * @return Full qualified package name
     */
    public final String getPackageIdentity()
    {
        return getClass().getPackage().getName();
    }

    protected abstract void onActivate();

    protected abstract void onRest();
    
    protected abstract void onShutdown();

    private void printTitle()
    {
        if (StringUtils.trimToNull(name) != null)
            System.out.println("- " + name + " -");
        else
            throw new IllegalStateException("Node name not assigned!");
    }

}
