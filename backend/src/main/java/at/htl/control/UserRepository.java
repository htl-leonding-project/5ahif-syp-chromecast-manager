package at.htl.control;

import at.htl.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Transactional
public class UserRepository implements PanacheRepository<User> {
    public User save(User userToSave) {
        if (userToSave == null)
        {
            throw new NullPointerException("User to save is null");
        }
        if(Collections.unmodifiableList(listAll()).size() != 0){
            for (User user: Collections.unmodifiableList(listAll()))
            {
                if (user.getName() == userToSave.getName() || user.getId() == userToSave.getId())
                {
                    return user;
                }
            }
        }
        return getEntityManager().merge(userToSave);
    }

    public List<User> findAllUsers(){
        return Collections.unmodifiableList(listAll(Sort.by("U_ID")));
    }

    public void delete(Long id)
    {
        User user = findById(id);
        getEntityManager().remove(user);
    }

    public User getUserDummy() {
        User user = new User();

        user.setName("Felix");
        user.setPasswordHash("Benchpress123");

        return user;
    }
}
