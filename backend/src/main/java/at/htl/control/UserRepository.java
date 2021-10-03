package at.htl.control;

import at.htl.entities.Room;
import at.htl.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UserRepository implements PanacheRepository<User> {
    public User save(User userToSave) {
        return getEntityManager().merge(userToSave);
    }
}
