package s2h.platform.node;

/**
 * Key Abstraction of atomic message-oriented software entity.
 * 
 * @author Chun-Feng Liao (2007.11.7)
 * @since 2.0
 */
public interface PervasiveNode 
{
  public String getName();
  
  public NodeState getNodeState();
  
  public abstract void activate();

  public abstract void rest();

  public abstract void shutdown();

}
