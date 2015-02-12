package s2h.platform.persistence;

import org.springframework.orm.jpa.support.JpaDaoSupport;

public class SensorMeasurementDaoJpaImpl extends JpaDaoSupport implements SensorMeasurementDao
{

    @Override
    public void save(SensorMeasurement s)
    {
        this.getJpaTemplate().persist(s);
    }

}
