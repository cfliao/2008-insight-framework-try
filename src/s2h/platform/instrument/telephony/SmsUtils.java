package s2h.platform.instrument.telephony;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import s2h.platform.support.PlatformConfig;

/*
 * Send Short Messages
 * 
 * Send Message: http://api.message.com.tw/send.php
 * Ex:  http://api.message.com.tw/send.php?gid=1189&id=freshstar&password=maybe&tel=0935932757&msg=測試訊息內容&mtype=P
 * 
 * Query: http://api.message.com.tw/query.php
 * 
 * @author try 
 */
public class SmsUtils
{
    private static Log logger = LogFactory.getLog(SmsUtils.class.getPackage().getName());

    private static final String SMS_GID = "1189";

    private static final String SMS_ID = "freshstar";

    private static final String SMS_PASSWORD = "maybe";

    private static final String SEND_URL = "http://api.message.com.tw/send.php";

    // private static final String QUERY_URL = "";

    public static String sendShortMessage(String tel, String messageContent)
    {
        String result = null;
        URL u;
        URLConnection uc;

        StringBuilder sb = new StringBuilder();
        sb.append(SEND_URL).append("?gid=").append(SMS_GID).append("&id=").append(SMS_ID).append("&password=").append(SMS_PASSWORD).append("&tel=").append(tel).append("&msg=").append(messageContent)
                .append("&mtype=P");
        logger.info(sb.toString());

        if (PlatformConfig.SMS_ENABLED)
        {
            try
            {
                u = new URL(sb.toString());
                uc = u.openConnection();
                BufferedReader theHTML = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                result = theHTML.readLine();
            }
            catch (Exception e)
            {
                logger.error("和遠端SMS Server連線出現問題.");
                e.printStackTrace();
            }
        }
        else
        {
            logger.info("目前簡訊功能未開啟!");
        }
        
        return result;
    }

    public static void main(String[] args)
    {
        
        String result = SmsUtils.sendShortMessage("0935932757", "封裝測試");
        System.out.println(result);
        
        // String thisLine;
        // URL u;
        // URLConnection uc;
        // // 即時發送
        // String strOnlineSend =
        // "http://api.message.com.tw/send.php?gid=1189&id=freshstar&password=maybe&mname=廖峻鋒&tel=0935932757;0972293108;0931563210&msg=這是測試簡訊&mtype=P";
        //        
        // String query = "http://api.message.com.tw/query.php?gid=1189&id=freshstar&password=maybe&columns=all";

        // try
        // {
        // u = new URL(query);
        // uc = u.openConnection();
        // BufferedReader theHTML = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        // thisLine = theHTML.readLine();
        //            
        // System.out.println(thisLine);
        // }
        // catch (Exception e)
        // {
        // e.printStackTrace();
        // }

    }
}
