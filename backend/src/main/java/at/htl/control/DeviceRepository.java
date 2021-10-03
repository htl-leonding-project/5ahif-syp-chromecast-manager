package at.htl.control;

import at.htl.entities.Device;
import at.htl.entities.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class DeviceRepository implements PanacheRepository<Device> {
    public Device save(Device deviceToSave) {
        return getEntityManager().merge(deviceToSave);
    }
}
