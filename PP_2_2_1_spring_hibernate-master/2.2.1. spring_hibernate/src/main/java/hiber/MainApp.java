package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("KIA 1000", 551);
      Car car2 = new Car("KIA 1400", 111);
      userService.add(new User("Ivana", "Taranova", "ivashkaiam@gmail.ru", car1));
      userService.add(new User("Ivan", "Taranov", "ivashka@gmail.ru", car2));
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println(user.getCar().toString());
         System.out.println();
      }

      User user1 = userService.getUserByModelAndSeries("KIA 1000", 551 );

      System.out.println("Id = " + user1.getId());
      System.out.println("First Name = " + user1.getFirstName());
      System.out.println("Last Name = " + user1.getLastName());
      System.out.println("Email = " + user1.getEmail());
      System.out.println(user1.getCar().toString());
      System.out.println();
         context.close();
      }
   }
