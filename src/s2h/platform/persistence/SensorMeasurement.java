/**
 * Copyright 2007 National Taiwan University CSIE Dept. Intelligent Robot & Automation Lab
 */
package s2h.platform.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SensorData
 * 
 * @author Chun-Feng Liao (2008/5/6)
 */

@Entity
@Table(name = "sensor_context")
public class SensorMeasurement
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int serial; // dummy

    @Column(name = "sensor_id")
    private String sensorId;

    @Column(name = "sensor_type")
    private String sensorType;

    private double value;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;

    public SensorMeasurement()
    {
    }

    public String getSensorId()
    {
        return sensorId;
    }

    public void setSensorId(String sensorId)
    {
        this.sensorId = sensorId;
    }

    public String getSensorType()
    {
        return sensorType;
    }

    public void setSensorType(String sensorType)
    {
        this.sensorType = sensorType;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    public static void main(String[] args)
    {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SensorMeasurement"); // (1)
//        EntityManager em = emf.createEntityManager(); // (2)
//        em.getTransaction().begin(); // (3)
//        for (int i = 0; i < 10; i++)
//        {
//            SensorMeasurement data = new SensorMeasurement("1", "", 20.11, new Date());
//            em.persist(data);
//        }
//        em.getTransaction().commit(); // (6)
//        em.close(); // (7)
//        emf.close(); // (8)
        
        
    }

    public void setSerial(int serial)
    {
        this.serial = serial;
    }

    public int getSerial()
    {
        return serial;
    }

}
