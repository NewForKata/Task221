package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      User vanya = new User("Vanya", "Vanyov", "VanyaVanyov@mail.ru");
      User serega = new User("Serega", "Seregov", "SeregaSeregov@mail.ru");
      User natasha = new User("Natasha", "Natasheva", "NatashaNatasheva@mail.ru");
      User vera = new User("Vera", "Verova", "VeraVerova@mail.ru");


      Car audi = new Car("Audi", 111);
      Car lexus = new Car("Lexus", 222);
      Car bmw = new Car("Bmw", 333);
      Car rollsroyce = new Car("Rollsroyce", 444);


      userService.add(vanya.setCar(audi).setUser(vanya));
      userService.add(serega.setCar(lexus).setUser(serega));
      userService.add(natasha.setCar(bmw).setUser(natasha));
      userService.add(vera.setCar(rollsroyce).setUser(vera));

      //Юзеры с машинами
      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("\n строка");
      }

      // Определить юзера по машине
      System.out.println(userService.getUserByCar("Lexus", 222) + "– юзер найден");
      System.out.println("\n строка");

      // Пользователь с такой машиной не найден
      try {
         User notFoundUser = userService.getUserByCar("Lada", 777);
      } catch (NoResultException e) {
         System.out.println("Юзер с такой машиной не найден");
         System.out.println("\n строка");
      }


      context.close();
   }
}