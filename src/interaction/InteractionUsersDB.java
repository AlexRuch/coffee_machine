package interaction;

import model.UsersDB;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.*;

/**
 * Created by alexey on 01/03/16.
 */
public class InteractionUsersDB {

    EntityManager entityManager;

    public void addUser(String userName, String userEmail, String userPassword) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();

        entityManager.getTransaction().begin();

        UsersDB user = new UsersDB();
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setUserPassword(userPassword);

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

}
