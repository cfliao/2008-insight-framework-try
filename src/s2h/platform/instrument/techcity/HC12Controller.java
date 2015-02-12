package s2h.platform.instrument.techcity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 科技城產品的低階控制實作，for HC-12, UDP-based protocol
 * 
 * @author starsirius 紀忠毅
 */
public class HC12Controller
{
    private String deviceAddr;

    private int devicePort;

    private String deviceID;

    // private byte[] clientVisa;
    private byte[] deviceVisa;

    public HC12Controller(String deviceAddr, int devicePort, String deviceID)
    {
        this.deviceAddr = deviceAddr;
        this.devicePort = devicePort;
        this.deviceID = deviceID;
        // this.clientVisa = new byte[4];
        this.deviceVisa = new byte[4];
    }

    /**
     * Appliance Control function
     */
    public void binaryControl(short cmd) throws Exception
    {
        connect();
        command(cmd);
        disconnect();
    }

    public void infraredControl(short dev, short cmd) throws Exception
    {
        connect();
        command(dev);
        command(cmd);
        disconnect();
    }

    private void command(short cmd) throws Exception
    {
        byte cHeader1 = HC12ControlConstants.VER_2 | HC12ControlConstants.PADDING | 0x02;
        byte cHeader2 = HC12ControlConstants.HEADER2_CODE;
        byte cDeviceType = HC12ControlConstants.TYPE_HC12;
        byte cCmd = HC12ControlConstants.CMD_CLINET_INQ_PARAMETERS_SETTING;
        int lPackNO = 0;
        int lClientVisa = 0;
        int lDeviceVisa = byteArrayToInt(this.deviceVisa);
        byte cRedundant = 0;
        byte cActionNO = 0;
        int lCheckSum = lDeviceVisa + lClientVisa + lPackNO + (cActionNO & 0xFF) + (cDeviceType & 0xFF) - (cCmd & 0xFF) + 100;
        byte uParameterMode = HC12ControlConstants.PARAMETER_WRITE;
        byte uParameterType = HC12ControlConstants.PARAMETER_PC08_CTRL;
        int lDeviceID = Integer.parseInt(this.deviceID, 16);

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(this.deviceAddr);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        bos = new ByteArrayOutputStream();
        dos = new DataOutputStream(bos);

        // the stDeviceHeader part of stParametersSetting struct (22 bytes)
        dos.writeByte(cHeader1); // cHeader1
        dos.writeByte(cHeader2); // cHeader2
        dos.writeByte(cDeviceType); // cDeviceType
        dos.writeByte(cCmd); // cCmd
        dos.writeInt(lPackNO); // lPackNo
        dos.writeInt(lClientVisa); // lClientVisa
        dos.writeInt(lDeviceVisa); // lDeviceVisa
        dos.writeInt(lCheckSum); // lCheckSum
        dos.writeByte(cRedundant); // cRedundant
        dos.writeByte(cActionNO); // cActionNO
        // the rest of stParametersSetting struct (64 - 22 = 42 bytes)
        dos.writeByte(uParameterMode); // ParameterMode
        dos.writeByte(uParameterType); // ParameterType
        dos.writeInt(lDeviceID); // lDeviceID
        dos.writeInt(0); // Common
        // (stParameterPC08Ctrl_PC *)pData
        dos.writeInt(0); // pRandom[3] & uCheckSum
        dos.writeShort(cmd); // iKeyCode
        dos.write(new byte[26], 0, 26); // the rest, byte default value: 0
        dos.flush();

        byte[] sendData = new byte[64];
        sendData = bos.toByteArray();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 2100);
        clientSocket.send(sendPacket);

        clientSocket.close();
    }

    private void connect() throws Exception
    {
        byte cHeader1 = HC12ControlConstants.VER_2 | HC12ControlConstants.PADDING | 0x02;
        byte cHeader2 = HC12ControlConstants.HEADER2_CODE;
        byte cDeviceType = HC12ControlConstants.TYPE_HC12;
        byte cCmd = HC12ControlConstants.CMD_CLIENT_INQ_REG;
        int lPackNO = 0;
        int lClientVisa = 0;
        int lDeviceVisa = 0;
        byte cRedundant = 0;
        byte cActionNO = 0;
        int lCheckSum = lDeviceVisa + lClientVisa + lPackNO + (cActionNO & 0xFF) + (cDeviceType & 0xFF) - (cCmd & 0xFF) + 100;
        int lDeviceID = Integer.parseInt(this.deviceID, 16);

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(this.deviceAddr);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        // the stDeviceHeader part of stEncryption struct (22 bytes)
        dos.writeByte(cHeader1); // cHeader1
        dos.writeByte(cHeader2); // cHeader2
        dos.writeByte(cDeviceType); // cDeviceType
        dos.writeByte(cCmd); // cCmd
        dos.writeInt(lPackNO); // lPackNO
        dos.writeInt(lClientVisa); // lClientVisa
        dos.writeInt(lDeviceVisa); // lDeviceVisa
        dos.writeInt(lCheckSum); // lCheckSum
        dos.writeByte(cRedundant); // cRedundant
        dos.writeByte(cActionNO); // cActionNO
        // the rest of stEncryption struct (64 - 22 = 42 bytes)
        dos.writeInt(lDeviceID); // lDeviceID
        dos.write(new byte[38], 0, 38); // the rest, byte default value: 0
        dos.flush();

        byte[] sendData = new byte[64];
        byte[] recvData = new byte[64];
        sendData = bos.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, this.devicePort);
        clientSocket.send(sendPacket);

        DatagramPacket recvPacket = new DatagramPacket(recvData, recvData.length);
        clientSocket.receive(recvPacket);

        //System.out.println(recvData[3]);
        System.arraycopy(recvData, 12, this.deviceVisa, 0, 4); // store the deviceVisa
        clientSocket.close();
    }

    private void disconnect() throws Exception
    {
        byte cHeader1 = HC12ControlConstants.VER_2 | HC12ControlConstants.PADDING | 0x02;
        byte cHeader2 = HC12ControlConstants.HEADER2_CODE;
        byte cDeviceType = HC12ControlConstants.TYPE_HC12;
        byte cCmd = HC12ControlConstants.CMD_CLIENT_INQ_EXIT;
        int lPackNO = 0;
        int lClientVisa = 0;
        int lDeviceVisa = byteArrayToInt(this.deviceVisa);
        byte cRedundant = 0;
        byte cActionNO = 0;
        int lCheckSum = lDeviceVisa + lClientVisa + lPackNO + (cActionNO & 0xFF) + (cDeviceType & 0xFF) - (cCmd & 0xFF) + 100;
        int lDeviceID = Integer.parseInt(this.deviceID, 16);

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(this.deviceAddr);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        // the stDeviceHeader part of stEncryption struct (22 bytes)
        dos.writeByte(cHeader1); // cHeader1
        dos.writeByte(cHeader2); // cHeader2
        dos.writeByte(cDeviceType); // cDeviceType
        dos.writeByte(cCmd); // cCmd
        dos.writeInt(lPackNO); // lPackNO
        dos.writeInt(lClientVisa); // lClientVisa
        dos.writeInt(lDeviceVisa); // lDeviceVisa
        dos.writeInt(lCheckSum); // lCheckSum
        dos.writeByte(cRedundant); // cRedundant
        dos.writeByte(cActionNO); // cActionNO
        // the rest of stEncryption struct (64 - 22 = 42 bytes)
        dos.writeInt(lDeviceID); // lDeviceID
        dos.write(new byte[38], 0, 38); // the rest, byte default value: 0
        dos.flush();

        byte[] sendData = new byte[64];
        sendData = bos.toByteArray();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, this.devicePort);
        clientSocket.send(sendPacket);

        clientSocket.close();
    }

    // private static byte[] intToByteArray(final int integer) throws IOException
    // {
    // ByteArrayOutputStream bos = new ByteArrayOutputStream();
    // DataOutputStream dos = new DataOutputStream(bos);
    // dos.writeInt(integer);
    // dos.flush();
    // return bos.toByteArray();
    // }

    // converting back with data output stream
    public static int byteArrayToInt(final byte[] bytes) throws IOException
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(bis);
        return in.readInt();
    }
}
