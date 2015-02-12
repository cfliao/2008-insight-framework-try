package s2h.platform.node;

import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.command.ActiveMQTopic;

/**
 * 統一管理現有Topic的名稱
 *
 * @author Chun-Feng Liao (2007.11.7)
 * @since 2.0
 */
public enum PlatformTopic
{
    RAW_DATA("ssh.RAW_DATA"),
    HCI_SR("ssh.HCI.SR"),
    HCI_TTS("ssh.HCI.TTS"),
    CONTEXT("ssh.CONTEXT"),
    COMMAND("ssh.COMMAND"),
    DISPLAY("ssh.COMMAND.DISPLAY"),
    ADMIN("ssh.ADMIN"),
    NULL("ssh.NULL")
    ;

    @SuppressWarnings("unused")
    private final String topicName;

    private PlatformTopic(String name)
    {
        topicName = name;
    }

    public String toString()
    {
        return topicName;
    }


    /**
     * 提供字串轉成PlatformTopic的功能，例如輸入一"RAW_DATA"字串，就可以得到Platform.RAW_DATA
     *
     * @param 要轉成PlatformTopic的字串
     * @return PlatformTopic
     */
    public static PlatformTopic fromString(String key)
    {
        return table.get(key);
    }

    public static ActiveMQTopic toActiveMQTopic(PlatformTopic topic)
    {
       return new ActiveMQTopic(topic.toString());
    }

    private final static Map<String, PlatformTopic> table = new HashMap<String, PlatformTopic>();

    static
    {
        table.put("RAW_DATA", PlatformTopic.RAW_DATA);
        table.put("CONTEXT", PlatformTopic.CONTEXT);
        table.put("COMMAND", PlatformTopic.COMMAND);
        table.put("DISPLAY", PlatformTopic.DISPLAY);
        table.put("HCI.SR", PlatformTopic.HCI_SR);
        table.put("HCI.TTS", PlatformTopic.HCI_TTS);
        table.put("ADMIN", PlatformTopic.ADMIN);
    }
}
