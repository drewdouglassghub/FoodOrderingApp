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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import Spring.Repository.CartRepository;
import Spring.Repository.CustomerRepository;
import Spring.Repository.MenuDepartmentsRepository;
import Spring.Repository.MenuItemsRepository;
import Spring.Repository.OrderItemsRepository;
import Spring.Repository.OrdersRepository;
import Spring.beans.MenuItems;
import Spring.beans.OrderItems;
import Spring.beans.Orders;
import Spring.beans.User;

@Controller
@SessionAttributes("user")
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
	
	@Autowired
	CartRepository cartRepo;
	
//****************************************************************************************************************//
	
	@GetMapping("/customerportal")
	public String goToPortal() {
		return "customerportal";
	}
	
	@GetMapping("../")
	public String goToHome() {
		return "login";
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
	
/***************************Cart Related Edits*********************************************/


	
	
	
	
/****************************Login Related Edits*********************************************/
	@RequestMapping(value = "loginUser", method = RequestMethod.POST)
	public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {

		try {
			User u = cRepo.findByUserName(username);
			model.addAttribute("user", u);

			if (u.getPassWord().equals(password) && u.getUserAuth().equals("ADMIN")) {

				return "viewAdmin";

			} else if (u.getPassWord().equals(password) && u.getUserAuth().equals("CUSTOMER")) {

				return "viewCustomer";
			} else {

				return "login";
			}
		} catch (Exception e) {
			return "loginError";
		}
	}

	  @ModelAttribute("user")
	   public User setUpUserForm() {
	      return new User();
	   }
	
	/*@PostMapping("/loginUser/{username}")
	public String showUser(@PathVariable("username") String username, Model model) {
	User u = cRepo.findByUserName(username);
		model.addAttribute("user", u);
			
		return "viewUser";
	}*/
	
	
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
		return "insertMenuItem";
	}
	
	@PostMapping("/inputMenuItem")
	public String addNewMenuItem(@ModelAttribute MenuItems mi, Model model) {
		menuRepo.save(mi);
		return "adminPortal";

	}
	
}
