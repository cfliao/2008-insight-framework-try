package s2h.platform.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import s2h.platform.node.NodeState;

/**
 * @author Chun-Feng Liao (2007/11/10)
 */
public class TopicContentFactory
{
    // 使用字串建構的用意在於將速度做最佳化，使用StringBuilder的速度快了將近5倍
    public static String createContextTopicContent(String subject, Map<String, String> contexts)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"subject\":\"").append(subject).append("\", ");

        Set<String> keys = contexts.keySet();
        for (String key : keys)
        {
            sb.append("\"").append(key).append("\":\"").append(contexts.get(key)).append("\", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append("}");
        return sb.toString();
    }

    // ssh.CONTEXT {"subject":"HOME", "context":"BURGLAR"}
    // public static String createContextTopicContent(String subject, Map context)
    // {
    // return new StringBuilder().append("{\"subject\":\"").append(subject).append("\",
    // \"value\":\"").append(context).append("\" }").toString();
    // }

    // ssh.ADMIN {"id":"ssh.application.aircon", "action":"STOP"}
    public static String createAdminTopicContent(String nodeId, String action)
    {
        return new StringBuilder().append("{\"type\":\"NODE_MANAGER\",\"id\":\"").append(nodeId).append("\", \"value\":\"").append(action.toUpperCase()).append("\" }").toString();
    }

    // ssh.COMMAND.DISPLAY {"id":"DISPLAY1", "action":"PLAY"}
    public static String createDisplayTopicContent(String id, String action)
    {
        return new StringBuilder().append("{\"id\":\"").append(id).append("\", \"command\":\"").append(action).append("\" }").toString();
    }
    
 // ssh.COMMAND.DISPLAY {"id":"DISPLAY1", "action":"PLAY"}
    public static String createDisplayTransferTopicContent(String id, String action,String targetId)
    {
        return new StringBuilder().append("{\"id\":\"").append(id).append("\", \"command\":\"").append(action).append("\", \"display_id\":\"").append(targetId).append("\"}").toString();
    }

    // ssh.COMMAND {"command":"TV_ON"}
    public static String createCommandTopicContent(String command)
    {
        return new StringBuilder().append("{\"value\":\"").append(command).append("\"}").toString();
        // return new JSONObject().put("command", command).toString();
    }

    public static void main(String[] args)
    {
        System.out.println("CONTEXT");
        Map<String, String> contexts = new HashMap<String, String>();
        contexts.put("burglar", "true");
        System.out.println(TopicContentFactory.createContextTopicContent("home", contexts));

        System.out.println("RAW_DATA");
        Map<String, String> measurements = new HashMap<String, String>();
        measurements.put("humidity", "50");
        measurements.put("temperature", "30.2");
        System.out.println(TopicContentFactory.createSensorTopicContent("taroko", "0", measurements));

        System.out.println("COMMAND");
        System.out.println(TopicContentFactory.createCommandTopicContent("TV_OFF"));

        System.out.println("COMMAND.DISPLAY");
        System.out.println(TopicContentFactory.createDisplayTopicContent("DISPLAY1", "PLAY"));

        System.out.println("ADMIN-nm");
        System.out.println(TopicContentFactory.createDisplayTopicContent("s2h.application.aircon.bl313", "STOP"));

        System.out.println("ADMIN-ndir-result");
        Map<String, String> dir = new HashMap<String, String>();
        dir.put("app1", "ACITVE");
        dir.put("app2", "STOPPED");
        System.out.println(TopicContentFactory.createNodeDirectoryResultTopicContent(dir));

        System.out.println("ADMIN-ndir-register");
        System.out.println(TopicContentFactory.createRegisterNodeDirectoryTopicContent("app", NodeState.ACTIVE));

        System.out.println("ADMIN-ndir-unregister");
        System.out.println(TopicContentFactory.createUnRegisterNodeDirectoryTopicContent("app"));

        System.out.println("ADMIN-ndir-queryall");
        System.out.println(TopicContentFactory.createNodeDirectoryTopicContent("QUERY_ALL"));
        
    }

    public static String createSensorTopicContent(String type, String id, Map<String, String> measurements)
    {
        StringBuilder sb = new StringBuilder();
        // {"sensor":"2","measurements":[{"humidity":"50"},{"temperature":"30.2"}],"sensorType":"taroko"}
        sb.append("{\"type\":\"").append(type).append("\", \"id\":\"").append(id).append("\", ");

        Set<String> keys = measurements.keySet();
        for (String key : keys)
        {
            sb.append("\"").append(key).append("\":\"").append(measurements.get(key)).append("\", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append("}");
        return sb.toString();
    }

    // {"type":"NODE_DIR_MANAGER", "function":"REGISTER","name":"SERVICE_NAME","state":"ACTIVE"}
    public static String createRegisterNodeDirectoryTopicContent(String nodeName, NodeState state)
    {
        return new StringBuilder().append("{\"type\":\"").append(PlatformConfig.NODE_DIRECTORY_MANAGER_TYPE_ID).append("\", \"function\":\"REGISTER\", \"name\":\"").append(nodeName).append(
                "\", \"state\":\"").append(state).append("\" }").toString();
    }

    public static String createUnRegisterNodeDirectoryTopicContent(String nodeName)
    {
        return new StringBuilder().append("{\"type\":\"").append(PlatformConfig.NODE_DIRECTORY_MANAGER_TYPE_ID).append("\", \"function\":\"UNREGISTER\", \"name\":\"").append(nodeName).append("\" }")
                .toString();
    }

    // {"type":"NODE_DIR_MANAGER", "function":"QUERY_ALL" }
    public static String createNodeDirectoryTopicContent(String command)
    {
        return new StringBuilder().append("{\"type\":\"").append(PlatformConfig.NODE_DIRECTORY_MANAGER_TYPE_ID).append("\", \"function\":\"").append(command).append("\" }").toString();
    }
    
    public static String createNodeDirectoryResultTopicContent(Map<String, String> serviceDir)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // JSONArray array = new JSONArray();
        Set<String> keys = serviceDir.keySet();
        for (String key : keys)
        {
            sb.append("{\"").append(key).append("\":\"").append(serviceDir.get(key)).append("\"},");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        if (serviceDir.size() <= 0)
            return "[]";
        else
            return sb.toString();
    }

    // public static void main(String[] args)
    // {
    // StopWatch timer = new StopWatch();
    // Map<String, String> measurements = new HashMap<String, String>();
    // measurements.put("humidity", "50");
    // measurements.put("temperature", "30.2");
    // timer.start();
    // for (int i = 0; i < 10000; i++)
    // {
    //
    // MessageContentFactory.createSensorMessageContent("taroko", "2", measurements);
    // }
    // timer.stop();
    // System.out.println(timer.getTime());
    // timer.reset();
    // timer.start();
    // for (int i = 0; i < 10000; i++)
    // {
    //
    // MessageContentFactory.createSensorMessageContent2("taroko", "2", measurements);
    // }
    // timer.stop();
    //
    // System.out.println(timer.getTime());
    // }
}