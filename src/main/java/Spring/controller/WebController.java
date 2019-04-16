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

import Spring.Repository.CartRepository;
import Spring.Repository.UserRepository;
import Spring.Repository.MenuDepartmentsRepository;
import Spring.Repository.MenuItemsRepository;
import Spring.Repository.OrderItemsRepository;
import Spring.Repository.OrdersRepository;
import Spring.beans.Cart;
import Spring.beans.MenuDepartments;

import Spring.beans.MenuItems;
import Spring.beans.OrderItems;
import Spring.beans.Orders;
import Spring.beans.User;

@Controller
public class WebController {
	@Autowired
	MenuDepartmentsRepository deptRepo;
	
	@Autowired
	MenuItemsRepository menuRepo;
	
	@Autowired
	UserRepository uRepo;
	
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
	public String showThisDept(@PathVariable("id") MenuDepartments id, Model model) {
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

/***************************Cart Related Edits*********************************************/
	@GetMapping("/viewCart/{id}")
	public String viewCart(@PathVariable("id") User id, Model model) {
		//Cart ca = cartRepo.findByUserId(id);
		model.addAttribute("cart", cartRepo.findByUserId(id));
		return "viewCart";
	}

	
	
	
	
/****************************Login Related Edits*********************************************/
	@RequestMapping(value="loginUser", method=RequestMethod.POST)
	public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
		
		try {
		User u = uRepo.findByUserName(username);		
		model.addAttribute("user", u);
		
		if(u.getPassWord().equals(password) && u.getUserAuth().equals("ADMIN") ) {
			
			return "viewAdmin";
			
			}else if(u.getPassWord().equals(password) && u.getUserAuth().equals("CUSTOMER")){
				
				return "viewCustomer";		
		}
		else {
		
		return "login";
		}
		}
		catch(Exception e){
			return "loginError";
		}
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
		model.addAttribute("deptList", deptRepo.findAll());
		return "insertMenuItem";
	}
	
	@PostMapping("/inputMenuItem")
	public String addNewMenuItem(@ModelAttribute MenuItems mi, Model model) {
		menuRepo.save(mi);
		return "adminPortal";

	}
	
	@GetMapping("/inputDepartment")
	public String addNewDepartment(Model model) {
		MenuDepartments md = new MenuDepartments();
		model.addAttribute("newDepartmentItem", md);
		return "inputDepartment";
	}
	
	@PostMapping("/inputDepartment")
	public String addnewDepartmentItem(@ModelAttribute MenuDepartments md, Model model) {
		deptRepo.save(md);
		return "adminportal";
	}
	
	@GetMapping("viewDepartmentItems")
	public String getDepartmentItems(Model model) {
		model.addAttribute("departments", deptRepo.findAll());
		return "adminViewDepartments";
	}
	
}
