package s2h.platform.instrument.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.SerialPort;

public class SerialPortGatewayImpl implements SerialPortGateway
{
    private CommPortIdentifier comId;

    private SerialPort serialPort;

    private InputStream is;

    private OutputStream os;

    public SerialPortGatewayImpl(String comportId)
    {
        boolean tryagain = true;

        while (tryagain)
        {
            try
            {
                Thread.sleep(1000);
                comId = CommPortIdentifier.getPortIdentifier(comportId);
                tryagain = false;
            }
            catch (NoSuchPortException nspe)
            {
                System.out.println("serial port (usb) device not attached.");
                // nspe.printStackTrace();
            }
            catch (InterruptedException e)
            {
            }
        }

        try
        {
            serialPort = (SerialPort) comId.open("NTU iSpace SerialUtils", 10);
            serialPort.setSerialPortParams(115200, 8, 1, 0);
            Thread.sleep(1000);
            os = serialPort.getOutputStream();
            is = serialPort.getInputStream();
        }
        catch (InterruptedException e)
        {
            // do nothing
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public InputStream getInputStream()
    {
        return is;
    }

    public OutputStream getOutputStream()
    {
        return os;
    }

    public void dispose()
    {
        try
        {
            is.close();
            os.close();
            serialPort.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
