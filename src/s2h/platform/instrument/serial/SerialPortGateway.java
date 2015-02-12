package s2h.platform.instrument.serial;

import java.io.InputStream;
import java.io.OutputStream;

public interface SerialPortGateway
{
    public InputStream getInputStream();

    public OutputStream getOutputStream();
    
    public void dispose();
}
