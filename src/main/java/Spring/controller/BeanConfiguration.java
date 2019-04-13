package Spring.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import Spring.beans.MenuDepartments;
import Spring.beans.MenuItems;
@Configuration
public class BeanConfiguration {
	@Bean
	public MenuDepartments MenuDepartments() {
		MenuDepartments bean = new MenuDepartments();
		return bean;
	}
	
	@Bean
	public MenuItems MenuItems() {
		MenuItems bean = new MenuItems();
		return bean;
	}
}
