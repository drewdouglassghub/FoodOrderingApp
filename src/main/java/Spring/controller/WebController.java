package Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Spring.Repository.MenuDepartmentsRepository;
import Spring.Repository.MenuItemsRepository;

@Controller
public class WebController {
	@Autowired
	MenuDepartmentsRepository deptRepo;
	
	@Autowired
	MenuItemsRepository menuRepo;
	
	@GetMapping("/customerportal")
	public String goToPortal() {
		return "customerportal";
	}
	
	@GetMapping("/viewMenu")
	public String menuInit(Model model) {
		model.addAttribute("departments", deptRepo.findAll());
		return "viewMenu";
	}
	
	@GetMapping("/view/{id}")
	public String showThisDept(@PathVariable("id") long id, Model model) {
		model.addAttribute("menuItems", menuRepo.findByItemDepartment(id));
		return "viewDept";
	}
	
}
