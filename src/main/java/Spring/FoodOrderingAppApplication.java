package Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@ComponentScan({"Spring", "controller"})
@Import({SecurityConfig.class})
public class FoodOrderingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderingAppApplication.class, args);
	}

}
