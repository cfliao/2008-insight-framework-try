package s2h.platform.node;


/**
 * @author Chun-Feng Liao (2007.11.7)
 * @since 2.0
 */
public interface Sendable
{
    public void send(final String message, PlatformTopic topicName);
}