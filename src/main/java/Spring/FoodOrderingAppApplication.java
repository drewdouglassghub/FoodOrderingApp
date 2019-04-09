package Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class FoodOrderingAppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(FoodOrderingAppApplication.class, args);
	}

}
