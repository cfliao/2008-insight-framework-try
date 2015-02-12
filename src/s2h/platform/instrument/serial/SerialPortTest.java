package s2h.platform.instrument.serial;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Formatter;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;

public class SerialPortTest implements Runnable
{
    private String portName;

    public SerialPortTest(String portName)
    {
        this.portName = portName;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws InterruptedException
    {
        Enumeration e = CommPortIdentifier.getPortIdentifiers();
        while (e.hasMoreElements())
        {
            CommPortIdentifier port = (CommPortIdentifier) e.nextElement();
            System.out.println(port.getName());
        }
        // new Thread(new SerialPortTest("COM3")).start();
        //new Thread(new SerialPortTest("COM6")).start();
        // send("COM4", new byte[] { (byte) 0x5a });
    }

    public int send(String portID, byte[] serialCommand)
    {
        try
        {
            CommPortIdentifier id = CommPortIdentifier.getPortIdentifier(portID);
            SerialPort techcity = (SerialPort) id.open("NTU iSpace SerialUtils", 10);
            techcity.setSerialPortParams(115200, 8, 1, 0);
            Thread.sleep(1000);

            // OutputStream os = techcity.getOutputStream();
            InputStream is = techcity.getInputStream();

            byte[] b = new byte[15];
            byte[] header = new byte[1];
            int k = 0;
            Formatter f = null;
            StringBuffer sb = null;

            // ´M§ä¼ÐÀY

            boolean out = false;

            while (!out)
            {
                header[0] = 0x0;
                is.read(header);

                if (header[0] == 0x5a)
                {
                    System.out.println(header[0]);
                    header[0] = 0x0;
                    is.read(header);
                    System.out.println(header[0]);
                    if (header[0] == (byte) -91)
                    {
                        out = true;
                    }
                }
            }

            for (int i = 0; i < 13; i++)
            {
                is.read(header);
            }

            while (k < 100)
            {
                for (int i = 0; i < 14; i++)
                    b[i] = 0x0;
                sb = new StringBuffer();
                f = new Formatter();
                Thread.sleep(1000);
                // os.write(serialCommand);
                // os.flush();
                is.read(b);

                sb.append("(" + portName + ")");

                f.format("%#x,%#x,%#x,%#x,%#x,%#x,%#x,%#x,%#x,%#x,%#x,%#x,%#x,%#x,%#x.", b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7], b[8], b[9], b[10], b[11], b[12], b[13], b[14]);

                // f.format("Sensor ID=(%#x,%#x), Timestamp=(%#x,%#x), type=(%#x,%#x), value=(%#x,%#x,%#x,%#x).", b[2],
                // b[3], b[4], b[5], b[6], b[7], b[8], b[9], b[10], b[11]);
                sb.append(f.toString());
                System.out.println(sb.toString());
                // System.out.println(((b[10] & 255) << 8) + (b[11] & 255));
               // System.out.println(PL2303Utils.bytesToValue(b[10], b[11]));
                f.close();
                k++;
                sb = null;
            }

            techcity.close();

            return 0;
        }
        catch (Exception ex)
        {
            throw new RuntimeException("test", ex);
        }
    }

    public void run()
    {
        send(portName, new byte[] { (byte) 0x5a });
    }

}
