package at.htl.control;

import at.htl.entities.Room;
import at.htl.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class UserRepository extends MyEntityManagerFactory<User> {
    /**
     * Use the method 'merge' of the EntityManager to persist the entity
     * - when the object doesn't exist in the database or the id is null, a new database entry is created (persist)
     * - when the object already exists in the database, it will be updated
     *
     * @param userToSave - object to persist
     * @return the managed instance of the object
     */
    public User save(User userToSave) {
        return getEntityManager().merge(userToSave);
    }

    public List<User> findAllUsers(){
        return (List<User>)getEntityManager().createNamedQuery("User.findAll")
                .getResultList();
    }
}
