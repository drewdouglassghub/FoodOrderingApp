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
import Spring.beans.Customer;
import Spring.beans.MenuItems;
import Spring.beans.OrderItems;
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
	
/********************************Menu Related Edits**********************/
	
	@GetMapping("/viewMenu")
	public String menuInit(Model model) {
		model.addAttribute("departments", deptRepo.findAll());
		return "viewMenu";
	}
	
	@GetMapping("/viewDept/{id}")
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
	
	
	@GetMapping("/addOrderItem/{id}")
	public String addOrderItem(@PathVariable("id") int id, Model model) {
		OrderItems oi = new OrderItems(id);
		model.addAttribute("addOrderItem", oi);
		oi.setQuantity(1);
		oi.setOrderId(1); //Placeholder until we have login functionality
						  //Must have an order in database to work
		oiRepo.save(oi);
		return "customerportal";
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
	
	@GetMapping("/viewMenuItems")
	public String viewAllItems(Model model) {
		model.addAttribute("menuItems", menuRepo.findAll());
		return "adminViewMenu";
	}
	
	@GetMapping("/adminEdit/{id}")
	public String showMenuItemUpdateForm(@PathVariable("id") long id, Model model) {
		MenuItems mi = menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Menu Item Id: " + id));
		
		model.addAttribute("menuItem", mi);
		model.addAttribute("deptList" , deptRepo.findAll());
		return "adminEdit";
	}
	
	@PostMapping("adminEdit/{id}")
	public String updateMenuItem(@PathVariable("id") long id, @Valid MenuItems mi, BindingResult result, Model model) {
		if(result.hasErrors()) {
			mi.setId(id);
			return "adminEdit";
		}
		
		menuRepo.save(mi);
		model.addAttribute("menuItems", menuRepo.findAll());
		return "adminViewMenu";
	}
	
	@GetMapping("adminDelete/{id}")
	public String deleteMenuItem(@PathVariable("id") long id, Model model) {
		MenuItems mi = menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Menu Item Id: " + id));
		
		menuRepo.delete(mi);
		model.addAttribute("menuItems", menuRepo.findAll());
		return "adminViewMenu";
	}

	
	
/****************************Login Related Edits*********************************************/
	@GetMapping("/loginUser/{username}")
	public String validateUser(@ModelAttribute Customer c, Model model) {
		if(c.getAuth() == "ADMIN") {
		return "adminPortal";
		}else if(c.getAuth() == "CUSTOMER") {
		return "customerportal";
		}else {
			return "index";
		}
	}
	
	@GetMapping("/login")
	public String goToLogin() {
		return "login";
	}
	/*@GetMapping("/editMenu/{id}")
	public String showMenuUpdateForm(@PathVariable("id") long id, Model model) {
		MenuItems i = menuRepo.find
	}*/
	
	@GetMapping("/inputMenuItem")
	public String addNewMenuItem(Model model) {
		MenuItems mi = new MenuItems();
		model.addAttribute("newMenuItem", mi);
		model.addAttribute("deptList", deptRepo.findAll());
		return "insertMenuItem";
	}
	
	@PostMapping("/inputMenuItem")
	public String addNewMenuItem(@ModelAttribute MenuItems mi, Model model) {
		menuRepo.save(mi);
		return "adminPortal";

	}
	
}
