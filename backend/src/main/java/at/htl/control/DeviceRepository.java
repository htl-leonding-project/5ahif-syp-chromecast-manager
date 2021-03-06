package at.htl.control;

import at.htl.entities.Category;
import at.htl.entities.Device;
import at.htl.entities.Room;
import at.htl.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional
public class DeviceRepository implements PanacheRepository<Device> {

    @Inject
    InstallAtRepository installAtRepository;


    public Device save(Device deviceToSave) {
        if (deviceToSave == null)
        {
            throw new NullPointerException("Device to save is null");
        }

        return getEntityManager().merge(deviceToSave);
    }

    public Device delete(Long id)
    {
        Device device = findById(id);
        getEntityManager().remove(device);
        return device;
    }

    public Device update(Long userId, String name, String brand, String ean, Category category)
    {
        Device deviceToChange = findById(userId);
        deviceToChange.setName(name);
        deviceToChange.setBrand(brand);
        deviceToChange.setEan(ean);
        deviceToChange.setCategory(category);
        getEntityManager().merge(deviceToChange);

        return deviceToChange;
    }

    public Device getDeviceDummy() {
        Device device = new Device();

        device.setName("Chromecast");
        device.setBrand("Brand");

        return device;
    }

    public List<Device> findAllDevices() {
        return Collections.unmodifiableList(listAll());
    }

    public List<Device> findFreedevices(){
        var notFreeDevices = installAtRepository.findAllInstallAts().stream()
                .map(i -> i.getDevice())
                .collect(Collectors.toList());

        var freeDevices = new LinkedList<Device>();

        for (Device d: findAllDevices()){
            if (!notFreeDevices.contains(d)){
                freeDevices.add(d);
            }
        }
        return freeDevices;
    }

    public List<Device> searchByCategory(String category){

        return findAllDevices().stream().filter(d -> d.getCategory().equals(Category.valueOf(category)))
                .collect(Collectors.toList());

    }

    public List<Device> readCSV(String file_name) {
        try{
            Path findCSV = find("devices.csv", ".").stream().findFirst().get();
            /*Stream<String> stream = Files.lines(findCSV, StandardCharsets.UTF_8);
            return stream.skip(1).map(line ->
            {
                String [] splitted = line.split(";"); return new Device(splitted[0],splitted[1]);
            })
                    .collect(Collectors.toList());*/
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    protected static Collection<Path> find(String fileName, String searchDirectory) throws IOException {
        try (Stream<Path> files = Files.walk(Paths.get(searchDirectory))) {
            return files
                    .filter(f -> f.getFileName().toString().equals(fileName))
                    .collect(Collectors.toList());

        }
    }
}
