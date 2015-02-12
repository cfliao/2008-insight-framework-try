package s2h.platform.support;

public final class PlatformConfig {
	// Network Settings

	// public static final String HOME_SERVER_IP = "192.168.4.100";
    public static final String HMB_URL = "tcp://localhost:61616";
    
    
	public static final String CONTROL_SERVER_IP = "192.168.4.101";

	public static final int CONTROL_SERVER_PORT = 2100;
	
	// Custom Socket Servers
	public static final int FLASH_SERVER_PORT = 6667;
	public static final int WEB_CAM_GATEWAY_SERVER_PORT = 6767;
	public static final int MOBILE_GATEWAY_SERVER_PORT = 6867;

	//public static final String CONTROL_SERVER_ID = "42000015"; // BL313
	public static final String BL313_CONTROL_SERVER_ID = "42000015"; // BL313
	public static final String OPENLAB_CONTROL_SERVER_ID = "42000008"; // OpenLab

	// is SMS enabled ? if it is enabled, the short message will be send, we have to pay NT$3 per message
    public static final boolean SMS_ENABLED = false;
	
	// HMB Settings

    // Protocol
    
    public static String NODE_MANAGER_TYPE_ID = "NODE_MANAGER";
    public static String NODE_DIRECTORY_MANAGER_TYPE_ID = "NODE_DIR_MANAGER";
    
	/**
	 * Global RDF Namespace
	 */
	public static final String GLOBAL_RDF_NS = "http://www.ntu.edu.tw/ssh/platform/";

	/**
	 * SPEECH message place holder, BIG5 words only work properly in message
	 * properties so we put BIG5-based recognized results in the propertie map
	 * of jms messages.Clients should get these results by
	 * SPEECH_PROP_PLALCEHOLDER_KEY
	 */
	public static final String SPEECH_PROP_PLALCEHOLDER_KEY = "content";

	/**
	 * used by speech message processor
	 */
	public static final String SPEECH_MSG_IDENTIFIER = "HCI_SR_TTS";

	public static final int MAX_SENSOR_VALUE_FRACTION_DIGITS = 2;
}
