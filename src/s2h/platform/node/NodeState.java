package s2h.platform.node;

/**
 * 
 * @author Chun-Feng Liao (2007.11.7)
 * @since 2.0
 */
public enum NodeState
{
    ACTIVE("ACTIVE"), DORMANT("DORMANT"), INSTALLED("INSTALLED");
    
    @SuppressWarnings("unused")
    private final String state;
    
    private NodeState(String name)
    {
        state = name;
    }
    
    public String toString()
    {
        return state;
    }
}
