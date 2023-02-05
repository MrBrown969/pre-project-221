package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User getUserByModelAndSeries (String carModel, int carSeries) {
      String HQL = "From User u LEFT OUTER JOIN FETCH u.car where u.car.model = :carModel and u.car.series = :carSeries";
      User user = sessionFactory.getCurrentSession().createQuery(HQL, User.class)
              .setParameter("carModel", carModel)
              .setParameter("carSeries", carSeries).getSingleResult();
      return user;
   }
}
