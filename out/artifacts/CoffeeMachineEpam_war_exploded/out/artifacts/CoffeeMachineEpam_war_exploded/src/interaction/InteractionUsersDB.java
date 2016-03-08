package interaction;

import model.UsersDB;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 01/03/16.
 */
public class InteractionUsersDB {

    EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();
    public List<UsersDB> listOfUsers = new ArrayList<>();
    private boolean userStatus;
    protected static UsersDB userDB;


    public void addUser(String userName, String userEmail, String userPassword) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        entityManager.getTransaction().begin();

        UsersDB user = new UsersDB();
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setUserPassword(userPassword);

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

    public boolean checkUser(String userEmail, String userPassword) throws ArrayIndexOutOfBoundsException{
       listOfUsers = entityManager.createQuery("select u from usersEntity u where u.userEmail like ?1 and u.userPassword like ?2", UsersDB.class)
               .setParameter(1, userEmail)
               .setParameter(2, userPassword)
               .getResultList();

       if (listOfUsers.size() == 0){
           userStatus = false;
       }
        else{
           userStatus = true;
           userDB = listOfUsers.get(0);
       }
        return userStatus;
    }

}
