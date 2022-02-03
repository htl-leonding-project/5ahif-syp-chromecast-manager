package at.htl.control;

import at.htl.entities.Device;
import at.htl.entities.Room;
import at.htl.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Transactional
public class DeviceRepository implements PanacheRepository<Device> {
    public Device save(Device deviceToSave) {
        if (deviceToSave == null)
        {
            throw new NullPointerException("Device to save is null");
        }

        return getEntityManager().merge(deviceToSave);
    }

    public List<Device> findAllDevices(){
        return Collections.unmodifiableList(listAll(Sort.by("D_ID")));
    }

    public Device delete(Long id)
    {
        Device device = findById(id);
        getEntityManager().remove(device);
        return device;
    }

    public Device update(Long userId, String name, String brand)
    {
        Device deviceToChange = findById(userId);
        deviceToChange.setName(name);
        deviceToChange.setBrand(brand);
        getEntityManager().merge(deviceToChange);

        return deviceToChange;
    }

    public Device getDeviceDummy() {
        Device device = new Device();

        device.setName("Chromecast");
        device.setBrand("Brand");

        return device;
    }

    public List<Device> getAllDevices() {
        return Collections.unmodifiableList(listAll());
    }
}
