package Spring.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import Spring.beans.MenuDepartments;
@Configuration
public class BeanConfiguration {
	@Bean
	public MenuDepartments MenuDepartments() {
		MenuDepartments bean = new MenuDepartments();
		return bean;
	}
}
