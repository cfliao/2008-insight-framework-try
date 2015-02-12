package s2h.platform.instrument.serial;


import java.io.OutputStream;
import java.util.Enumeration;

import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;

public class SerialPortUtils
{

    @SuppressWarnings("unchecked")
	public static void main(String[] args)
    {
        Enumeration e = CommPortIdentifier.getPortIdentifiers();
        while (e.hasMoreElements())
        {
            CommPortIdentifier port = (CommPortIdentifier) e.nextElement();
            System.out.println(port.getName());
            //send("COM1",ELiveSerialPortCommand.B_ON.getContent());
            
        }
    }

    public static int send(String portID, byte[] serialCommand)
    {
        try
        {
            CommPortIdentifier id = CommPortIdentifier.getPortIdentifier(portID);
            CommPort techcity = id.open("NTU iSpace SerialUtils", 10);

            OutputStream os = techcity.getOutputStream();
            os.write(serialCommand);

            os.flush();
            os.close();
            techcity.close();

            return 0;
        }
        catch (Exception ex)
        {
            throw new RuntimeException("serial port connection error!", ex);
        }
    }

}
