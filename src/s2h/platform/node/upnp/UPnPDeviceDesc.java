package s2h.platform.node.upnp;

public class UPnPDeviceDesc
{
    private String udn;

    private String deviceType;

    private String path;

    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUdn(String udn)
    {
        this.udn = udn;
    }

    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getUdn()
    {
        return udn;
    }

    public String getDeviceType()
    {
        return deviceType;
    }

    public String getPath()
    {
        return path;
    }

}
