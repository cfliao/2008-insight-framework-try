package s2h.platform.instrument.techcity;


/**
 * HC12ControlConstants
 *
 * @author ¬ö©¾¼Ý
 */
public class HC12ControlConstants {
	public static final byte TYPE_HC12 = (byte)0xC4;
	// ---------------------------------------------------------------------------
	public static final byte HEADER2_CODE = (byte)0xBB;
	// ---------------------------------------------------------------------------
	public static final byte VER_0 = (byte)0x00; // bit7-6 'V'
	public static final byte VER_1 = (byte)0x40;
	public static final byte VER_2 = (byte)0x80;
	public static final byte VER_3 = (byte)0xC0; 
	public static final byte PADDING = (byte)0x20; // bit5 'P'
	
	// ---------------------------------------------------------------------------
	public static final byte CMD_INFOSERVER_INQ_CODE			= 1;
	public static final byte CMD_RET_INFOSERVER_INQ_CODE_OK		= 2;
	public static final byte CMD_RET_INFOSERVER_INQ_CODE_ERR	= 3;
	public static final byte CMD_INFOSERVER_INQ_REG				= 4;
	public static final byte CMD_RET_INFOSERVER_INQ_REG_OK		= 5;
	public static final byte CMD_RET_INFOSERVER_INQ_REG_ERR		= 6;
	
	public static final byte CMD_CLIENT_INQ_REG						= 61;
	public static final byte CMD_RET_CLIENT_INQ_REG_OK				= 62;
	public static final byte CMD_RET_CLIENT_INQ_REG_ERR				= 63;
	public static final byte CMD_RET_CLIENT_INQ_REG_EXIST			= 64;
	public static final byte CMD_RET_CLIENT_INQ_REG_DEVICE_ID_ERR	= 65;
	public static final byte CMD_RET_CLIENT_INQ_REG_PW_ERR			= 66;
	public static final byte CMD_RET_CLIENT_INQ_REG_MD5_ERR			= 67;
	public static final byte CME_RET_CLIENT_INQ_REG_NO_SPACE		= 68;
	public static final byte CMD_RET_CLIENT_INQ_REG_UK_ERR			= 69;
	public static final byte CMD_RET_CLIENT_TIMEOUT_DISCONNECT		= 70;
	
	public static final byte CMD_CLIENT_INQ_EXIT			= 71;
	public static final byte CMD_RET_CLIENT_INQ_EXIT_OK		= 72;
	public static final byte CMD_RET_CLIENT_INQ_EXIT_ERR	= 73;
	
	public static final byte CMD_CLINET_INQ_PARAMETERS_SETTING				= 74;	
	public static final byte CMD_RET_CLINET_INQ_PARAMETERS_SETTING_OK		= 75;
	public static final byte CMD_RET_CLIENT_INQ_PARAMETERS_SETTING_ERR		= 76;
	public static final byte CMD_RET_CLIENT_INQ_PARAMETERS_SETTING_OUT_MEM	= 77;
	public static final byte CMD_RET_CLIENT_INQ_PARAMETERS_SETTING_BUSY		= 78;
	
	//=== Added from ver 2.x.x.x for two layer password.	
	public static final byte CMD_RET_CLIENT_INQ_REG_CTRLONLY_PW_ERR		= 79;
	//=== End of added.	
	
	public static final byte CMD_GET_DEVICE_IPS				= 101;
	public static final byte CMD_RET_GET_DEVICE_IPS_OK      = 102;                                              
	public static final byte CMD_RET_GET_DEVICE_IPS_ERR		= 103;
	
	public static final byte CMD_GET_DEVICE_NAME 			= 105;
	public static final byte CMD_RET_GET_DEVICE_NAME_OK		= 106;
	public static final byte CMD_RET_GET_DEVICE_NAME_ERR	= 107;
	
	public static final byte CMD_DEVICE_REBOOT = 111;

	//  Command set for search Device By DeviceID
	public static final byte CMD_INFOSERVER_INQ_BY_CID_CODE			= (byte)200;
	public static final byte CMD_RET_INFOSERVER_INQ_BY_CID_CODE_OK	= (byte)201;
	public static final byte CMD_RET_INFOSERVER_INQ_BY_CID_CODE_ERR	= (byte)202;
	public static final byte CMD_INFOSERVER_INQ_BY_CID				= (byte)203;
	public static final byte CMD_RET_INFOSERVER_INQ_BY_CID_OK		= (byte)204;
	public static final byte CMD_RET_INFOSERVER_INQ_BY_CID_ERR		= (byte)205;

 	public static final byte CMD_INFOSERVER_INQ_OL_INFOLIST			= (byte)206;
	public static final byte CMD_INFOSERVER_INQ_OL_INFOLIST_OK		= (byte)207;
	public static final byte CMD_INFOSERVER_INQ_OL_INFOLIST_ERR		= (byte)208;

	public static final byte CMD_INFOSERVER_INQ_BY_CID_CODE_NEW			= (byte)209;
	public static final byte CMD_RET_INFOSERVER_INQ_BY_CID_CODE_NEW_OK	= (byte)210;
	public static final byte CMD_RET_INFOSERVER_INQ_BY_CID_CODE_NEW_ERR	= (byte)211;
	public static final byte CMD_INFOSERVER_INQ_BY_CID_NEW				= (byte)212;
	public static final byte CMD_RET_INFOSERVER_INQ_BY_CID_NEW_OK		= (byte)213;
	public static final byte CMD_RET_INFOSERVER_INQ_BY_CID_NEW_ERR		= (byte)214;

 	public static final byte CMD_INQ_LATEST_AP_VERSION 		= (byte)250;
	public static final byte CMD_INQ_LATEST_AP_VERSION_OK	= (byte)251;
	public static final byte CMD_INQ_LATEST_AP_VERSION_ERR	= (byte)252;
	public static final byte CMD_SET_LATEST_AP_VERSION 		= (byte)253;
	public static final byte CMD_SET_LATEST_AP_VERSION_OK	= (byte)254;
	public static final byte CMD_SET_LATEST_AP_VERSION_ERR	= (byte)255;
	
	// ---------------------------------------------------------------------------
	// Parameter Mode
	public static final byte PARAMETER_WRITE = 0; 
	public static final byte PARAMETER_READ = 1;
	
	// ---------------------------------------------------------------------------
	// Parameter Type
	public static final byte PARAMETER_IPS_A = 1;
	public static final byte PARAMETER_IPS_B = 2;
	public static final byte PARAMETER_IPS_C = 3;
	public static final byte PARAMETER_IPS_D = 4;
	public static final byte PARAMETER_PC08_CTRL = 5;
	public static final byte PARAMETER_SECURITY_A = 6;
	public static final byte PARAMETER_SECURITY_B = 7;
	public static final byte PARAMETER_SECURITY_C = 8;
	public static final byte PARAMETER_SECURITY_D = 9;
	public static final byte PARAMETER_A = 10;
	public static final byte PARAMETER_B = 11; //manually control the trigger.
	public static final byte PARAMETER_D = 12;
	public static final byte PARAMETER_EMAIL = 13;	
	public static final byte PARAMETER_SMS = 14;
	public static final byte PARAMETER_PREORDER_MACRO_CTRL = 15;
	public static final byte PARAMETER_PERIOD_MACRO_CTRL = 16;
	public static final byte PARAMETER_RFT_MACRO_CTRL = 17;
	public static final byte PARAMETER_OTHERS = 18;
	//=== Added from Ver 2.3.0.0	
	public static final byte PARAMETER_NAMES = 19;
	//=== End of added.	
	public static final byte PARAMETER_SPECIAL_CMD_AP = 100;
	
	// Zone 1 Flag ---------------------------------------------------------------------------
	public static final short KEY_A__ON		= 0x5281;
	public static final short KEY_B__ON		= 0x5282;
	public static final short KEY_C__ON		= 0x5283;
	public static final short KEY_D__ON		= 0x5284;
	public static final short KEY_E__ON		= 0x5285;
	public static final short KEY_F__ON		= 0x5286;
	public static final short KEY_G__ON		= 0x5287;
	public static final short KEY_H__ON		= 0x5288;
	public static final short KEY_I__ON		= 0x5289;
    public static final short KEY_J__ON     = 0x528A;
    public static final short KEY_K__ON     = 0x528B;
    public static final short KEY_L__ON     = 0x528C;
    
	public static final short KEY_A__OFF	= 0x5241;
	public static final short KEY_B__OFF	= 0x5242;
	public static final short KEY_C__OFF	= 0x5243;
	public static final short KEY_D__OFF	= 0x5244;
	public static final short KEY_E__OFF	= 0x5245;
	public static final short KEY_F__OFF	= 0x5246;
	public static final short KEY_G__OFF	= 0x5247;
	public static final short KEY_H__OFF	= 0x5248;
	public static final short KEY_I__OFF	= 0x5249;
    public static final short KEY_J__OFF     = 0x524A;
    public static final short KEY_K__OFF     = 0x524B;
    public static final short KEY_L__OFF     = 0x524C;
    
	// Zone 2 Flag ----------------------------------------------------------------------------------------------------
	public static final short KEY_LAMP2_1__ON	= 0x5291;
	public static final short KEY_LAMP2_2__ON	= 0x5292;
	public static final short KEY_LAMP2_3__ON	= 0x5293;
	public static final short KEY_LAMP2_4__ON	= 0x5294;
	public static final short KEY_LAMP2_5__ON	= 0x5295;
	public static final short KEY_LAMP2_6__ON	= 0x5296;
	public static final short KEY_LAMP2_7__ON	= 0x5297;
	public static final short KEY_LAMP2_8__ON	= 0x5298;
	public static final short KEY_LAMP2_9__ON	= 0x5299;
	public static final short KEY_LAMP2_10__ON	= 0x529A;
	public static final short KEY_LAMP2_11__ON	= 0x529B;
	public static final short KEY_LAMP2_12__ON	= 0x529C;
	public static final short KEY_LAMP2_1__OFF	= 0x5251;
	public static final short KEY_LAMP2_2__OFF	= 0x5252;
	public static final short KEY_LAMP2_3__OFF	= 0x5253;
	public static final short KEY_LAMP2_4__OFF	= 0x5254;
	public static final short KEY_LAMP2_5__OFF	= 0x5255;
	public static final short KEY_LAMP2_6__OFF	= 0x5256;
	public static final short KEY_LAMP2_7__OFF	= 0x5257;
	public static final short KEY_LAMP2_8__OFF	= 0x5258;
	public static final short KEY_LAMP2_9__OFF	= 0x5259;
	public static final short KEY_LAMP2_10__OFF	= 0x525A;
	public static final short KEY_LAMP2_11__OFF	= 0x525B;
	public static final short KEY_LAMP2_12__OFF	= 0x525C;
	
	
	// Appliance Selection Flag ---------------------------------------------------------------------------
	public static final short DEV_AMP		= (short)0xf10e;
	public static final short DEV_TV		= (short)0xf20d;
	public static final short DEV_STB		= (short)0xf30c;
	public static final short DEV_DVD		= (short)0xf40b;
	public static final short DEV_CD		= (short)0xf50a;
	public static final short DEV_CATV		= (short)0xf609;
	public static final short DEV_AUX		= (short)0xf708;
	
	// Appliance Function Flag ---------------------------------------------------------------------------
	public static final short POWER			= 0x4901;
	public static final short MENU			= 0x4902;
	public static final short PLAY			= 0x4903;
	public static final short FORWARD		= 0x4904;
	public static final short BACKWARD		= 0x4905;
	public static final short UP			= 0x4906;
	public static final short DOWN			= 0x4907;
	public static final short LEFT			= 0x4908;
	public static final short RIGHT			= 0x4909;
	public static final short PAUSE			= 0x490a;
	public static final short STOP			= 0x490b;
	public static final short VOL_UP		= 0x490c;
	public static final short VOL_DOWN		= 0x490d;
	public static final short CH_UP			= 0x490e;
	public static final short CH_DOWN		= 0x490f;
	public static final short TV_VIDEO		= 0x5901;
	public static final short MUTE			= 0x5902;
	public static final short NEXT			= 0x5903;
	public static final short PREVIOUS		= 0x5904;
	public static final short _1			= (short)0x9601;
	public static final short _2			= (short)0x9602;
	public static final short _3			= (short)0x9603;
	public static final short _4			= (short)0x9604;
	public static final short _5			= (short)0x9605;
	public static final short _6			= (short)0x9606;
	public static final short _7			= (short)0x9607;
	public static final short _8			= (short)0x9608;
	public static final short _9			= (short)0x9609;
	public static final short _10			= (short)0x960a;
	public static final short _11			= (short)0x960b;
	public static final short _12			= (short)0x960c;
}

