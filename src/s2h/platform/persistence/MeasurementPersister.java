package s2h.platform.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import s2h.platform.annotation.ListenTopic;
import s2h.platform.annotation.UPnPParameter;
import s2h.platform.node.ActuatorNode;
import s2h.platform.node.NodeRunner;
import s2h.platform.node.PlatformMessage;
import s2h.platform.node.PlatformTopic;

/**
 * Context Persister
 * 
 * @author Chun-Feng Liao (2008/5/6)
 */
@ListenTopic(topic = PlatformTopic.RAW_DATA)
@UPnPParameter(friendlyName = "Sensor Measurement Persister")
public class MeasurementPersister extends ActuatorNode
{
    private Log logger = LogFactory.getLog(this.getPackageIdentity());

    private SensorMeasurementDao sensorMeasurementDao;

    public MeasurementPersister()
    {
        super(MeasurementPersister.class.getName());
    }

    public static void main(String[] args)
    {
        new NodeRunner(MeasurementPersister.class).execute();
    }

    @Override
    protected void processMessage(PlatformMessage message)
    {

        List<SensorMeasurement> results = extractData(message);
        for (SensorMeasurement m : results)
            sensorMeasurementDao.save(m);
    }

    @SuppressWarnings("unchecked")
    private List<SensorMeasurement> extractData(PlatformMessage message)
    {
        // {"type":"taroko", "id":"0", "humidity":"50", "temperature":"30.2" }
        // 除了type及id，其它都算measurement
        List<SensorMeasurement> results = new ArrayList<SensorMeasurement>();
        //logger.debug("message=-->" + message.getContent() + "<--");
        JSONObject json = JSONObject.fromObject(message.getContent());

        if (json == null)
            return null;

        String sensorId = json.getString("id");

        for (String key : (Set<String>) json.keySet())
        {
            if (!"id".equalsIgnoreCase(key) && !"type".equalsIgnoreCase(key))
            {
                SensorMeasurement m = new SensorMeasurement();
                m.setSensorId(sensorId);
                m.setSensorType(key);
                m.setValue(json.getDouble(key));
                m.setTimestamp(new Date());
                results.add(m);
                logger.debug(m.getSensorId() + " " + m.getSensorType() + " " + m.getValue() + " " + m.getTimestamp());
            }
        }

        return results;
    }

    @Override
    protected void onActivate()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onRest()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onShutdown()
    {
    }

    public SensorMeasurementDao getSensorMeasurementDao()
    {
        return sensorMeasurementDao;
    }

    public void setSensorMeasurementDao(SensorMeasurementDao sensorMeasurementDao)
    {
        this.sensorMeasurementDao = sensorMeasurementDao;
    }

}
