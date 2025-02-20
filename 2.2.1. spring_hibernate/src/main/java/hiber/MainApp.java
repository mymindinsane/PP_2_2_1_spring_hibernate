package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.addUser(new User("Egor", "Egorov", "user1@mail.ru"));
      userService.addUser(new User("Artur", "Arturov", "user2@mail.ru"));
      userService.addUser(new User("Dima", "Dimov", "user3@mail.ru"));
      userService.addUser(new User("Vitaliy", "Vitaliyev", "user4@mail.ru"));

      Car car1 = new Car(5,"BMW");
      carService.add(car1);
      Car car2 = new Car(4,"Mercedes");
      carService.add(car2);
      Car car3 = new Car(3,"Volkswagen");
      carService.add(car3);

      User user5 = new User("Svetlana", "Svetlanova", "user5@mail.ru");
      user5.setCar(car1);
      car1.setUser(user5);
      userService.addUser(user5);



      User user6 = new User("Diana", "Dianovna", "user6@mail.ru");
      user6.setCar(car2);
      car2.setUser(user6);
      userService.addUser(user6);

      User user7 = new User("Jora", "Jorov", "user7@mail.ru");
      user7.setCar(car3);
      car3.setUser(user7);
      userService.addUser(user7);



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println(userService.getUserByCarSeriesAndModel("BMW",5));
      System.out.println(userService.getUserByCarSeriesAndModel("Mercedes",4));
      System.out.println(userService.getUserByCarSeriesAndModel("Volkswagen",3));

      context.close();
   }
}
