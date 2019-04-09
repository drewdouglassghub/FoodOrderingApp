package Spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Spring.Repository.CustomerRepository;
import Spring.Repository.MenuDepartmentsRepository;
import Spring.Repository.MenuItemsRepository;
import Spring.Repository.OrderItemsRepository;
import Spring.Repository.OrdersRepository;
import Spring.beans.MenuItems;
import Spring.beans.Orders;

@Controller
public class WebController {
	@Autowired
	MenuDepartmentsRepository deptRepo;
	
	@Autowired
	MenuItemsRepository menuRepo;
	
	@Autowired
	CustomerRepository cRepo;
	
	@Autowired
	OrderItemsRepository oiRepo;
	
	@Autowired
	OrdersRepository oRepo;
	
	@GetMapping("/customerportal")
	public String goToPortal() {
		return "customerportal";
	}
	
	@GetMapping("../")
	public String goToHome() {
		return "index";
	}
	
	@GetMapping("/adminportal") 
		public String goToAdminPortal() {
			return "adminPortal";
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

	
/************************Order Related Edits******************************/
	
	@GetMapping("/viewOrders")
	public String viewOrderList(Model model) {
		model.addAttribute("orders", oRepo.findAll());
		return "viewOrders";
	}
	
	@GetMapping("/editOrder/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Orders o = oRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid order id:" + id));
		model.addAttribute("orders", o);
		return "updateOrder";
	}
	
	@PostMapping("/updateOrder/{id}")
	public String updateOrders(@PathVariable("id") long id, @Valid Orders o, BindingResult result, Model model) {
		if(result.hasErrors()) {
			o.setId(id);;
			return "updateOrder";
		}
		
		oRepo.save(o);
		model.addAttribute("orders", oRepo.findAll());
		return "customerportal";
	}
/*************************************************************************/	

/************************Admin Related Edits******************************/	

	@GetMapping("/addItem")
	public String addNewItem(@ModelAttribute MenuItems mi, Model model) {
		menuRepo.save(mi);
		return "adminPortal";
	}
	/*@GetMapping("/editMenu/{id}")
	public String showMenuUpdateForm(@PathVariable("id") long id, Model model) {
		MenuItems i = menuRepo.find
	}*/
	
	
}
