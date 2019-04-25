package Spring.controller;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.support.SessionStatus;

import Spring.Repository.CartRepository;
import Spring.Repository.UserRepository;
import Spring.Repository.MenuDepartmentsRepository;
import Spring.Repository.MenuItemsRepository;
import Spring.Repository.OrderItemsRepository;
import Spring.Repository.OrdersRepository;
import Spring.Repository.PromotionRepository;

import Spring.beans.MenuDepartments;

import Spring.beans.MenuItems;
import Spring.beans.OrderItems;
import Spring.beans.Orders;
import Spring.beans.Promotion;
import Spring.beans.User;

@Controller
@SessionAttributes({"user", "order"})
public class WebController {

	  @ModelAttribute("user")
	   public User setUpUserForm() {
	      return new User();
	   }
	  
	
	
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
	
	@Autowired
	PromotionRepository promoRepo;
	
//****************************************************************************************************************//
	
	@GetMapping("/customerportal")
	public String goToPortal(User user, Orders order, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("order", order);
		if(user.getUserAuth().equals("CUSTOMER") || user.getUserAuth().equals("ADMIN")) {
			return "customerportal";
		}else {
			model.addAttribute("message", "User not authorized");
			return "login";
		}
	}
	
	@GetMapping("../")
	public String goToHome() {
		return "login";
	}
	
		
	@GetMapping("/adminportal") 
		public String goToAdminPortal(User user, Model model) {
		model.addAttribute("user", user);
		if(user.getUserAuth().equals("ADMIN")) {
			return "adminPortal";
		}else {
			model.addAttribute("message", "User not authorized");
			return "/login";
		}
	}
	
	@GetMapping("/createUser")
	public String createUser(Model model) {
		User u = new User();
		model.addAttribute("user", u);
		return "/createUser";
	}
	@PostMapping("/createUser")
		public String createUser(User user, Model model) {
			user.setUserAuth("CUSTOMER");
			Date d = new Date(Calendar.getInstance().getTime().getTime());
			user.setVisitDate(d);
			uRepo.save(user);
			
			Orders o = new Orders(user.getUserId());
			oRepo.save(o);
			model.addAttribute(user);
			return "/login";
		}
/********************************Menu Related Edits**********************/
	
	@GetMapping("/viewMenu")
	public String menuInit(User user, Orders order, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("order", order);
		model.addAttribute("departments", deptRepo.findAll());
		return "viewMenu";
	}
	
	@GetMapping("/viewDept/{id}")
	public String showThisDept(@PathVariable("id") MenuDepartments id, User user, Orders order, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("order", order);
		model.addAttribute("deptId", id);
		model.addAttribute("menuItems", menuRepo.findByItemDepartment(id));
		return "viewDept";
	}

	
/************************Order Related Edits******************************/
	
	@GetMapping("/viewOrders")
	public String viewOrderList(User user, Model model) {
		model.addAttribute("orders", oRepo.findAll());
		model.addAttribute("user", user);
		if(user.getUserAuth().equals("ADMIN")) {
			return "viewOrders";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/viewOrderDetails/{id}")
	public String viewOrderDetails(@PathVariable("id") long id, User user, Model model) {
		OrderItems oi = oiRepo.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("orderitems", oi);
		return "viewOrderDetails";
	}
	
	
	@GetMapping("/addOrderItem/{id}")
	public String viewItem(@PathVariable("id") long id, User user, Orders order, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("order", order);
		MenuItems mi = menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Menu Item Id: " + id));
		model.addAttribute("item", mi);
		return "viewItem";
	}
	
	@PostMapping("/addItemToCart")
	public String addItemToCart(@ModelAttribute OrderItems ca, User user, Model model) {
		Orders order = oRepo.findByCustomerId(user.getUserId());
		ca.setOrderId(order);
		oiRepo.save(ca);
		return "/viewOrders";
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
			o.setOrderId(id);;
			return "updateOrder";
		}
		
		oRepo.save(o);
		model.addAttribute("orders", oRepo.findAll());
		return "customerportal";
	}
	
	
/*************************************************************************/	

/************************Admin Related Edits******************************/	

	@GetMapping("/addItem")
	public String addNewItem(@ModelAttribute MenuItems mi, User user, Model model) {
		model.addAttribute("user", user);
		if(user.getUserAuth().equals("ADMIN")) {
			menuRepo.save(mi);
			return "adminPortal";
		}else {
			return "login";
		}		
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

/***************************Cart/Order Items Related Edits*********************************************/
	@GetMapping("/viewCart/{id}")
	public String viewCart(@PathVariable("id") User user, Model model) {
		model.addAttribute("user", user);
		/* Error handling for users who haven't made a cart yet.
		if (oRepo.findByCustomerId(user.getUserId())) {
			return "/customerportal";
		}
		*/
		Orders o = oRepo.findByCustomerId(user.getUserId());
		
		List<OrderItems> orderitems = oiRepo.findByOrderId(o);
		
		model.addAttribute("orderitems", orderitems);
		
		List<String> itemValues = new ArrayList<>();
		double totalValue = 0;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		for (int i = 0; i < orderitems.size(); i++) {
			OrderItems toRead = orderitems.get(i);
			MenuItems productId = toRead.getItemId();
			double money = productId.getItemPrice() * toRead.getQuantity();
			String sMoney = formatter.format(money);
			String product = productId.getItemName();
			String toSend = "Total Cost of " + product + ": " + sMoney + "";
			itemValues.add(toSend);
			totalValue += money;
		}
		String sTotal = formatter.format(totalValue);
		String totalCost = "Total Cost of Items: " + sTotal + "";
		
		model.addAttribute("moneyItems", itemValues);
		model.addAttribute("totalCost", totalCost);
		

		if(user.getUserAuth().equals("CUSTOMER")||user.getUserAuth().equals("ADMIN")) {
			return "viewCart";
		}else {
			model.addAttribute("message", "Please sign in to view your cart.");
			return "/login";
		}		
	}
	
	@GetMapping("/updateOrderItem/{id}")
	public String updateOrderItem(@PathVariable("id") long id, User user, Model model) {
		
		OrderItems oi = oiRepo.findById(id);
		oi.setOrderId(oRepo.findByOrderId(oi.getOrderIdNum()));
		Orders o = oi.getOrderId();
		model.addAttribute("orderitems", oi);
		model.addAttribute("user", user);
		model.addAttribute("orders", o);
		return "updateOrderItem";
	}
	
	@PostMapping("/updateOrderItem/{id}")
	public String updateOrderItem(@PathVariable("id") long id, @Valid OrderItems oi, Model model) {

		oiRepo.save(oi);
		
		User user = uRepo.findByUserId(oi.getUserId());
		model.addAttribute("user", user);
		Orders o = oRepo.findByCustomerId(user.getUserId()); //was having difficulty getting this from oi so it's getting it from user.
		List<OrderItems> orderitems = oiRepo.findByOrderId(o);
		
		model.addAttribute("orderitems", orderitems);
		
		List<String> itemValues = new ArrayList<>();
		double totalValue = 0;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		for (int i = 0; i < orderitems.size(); i++) {
			OrderItems toRead = orderitems.get(i);
			MenuItems productId = toRead.getItemId();
			double money = productId.getItemPrice() * toRead.getQuantity();
			String sMoney = formatter.format(money);
			String product = productId.getItemName();
			String toSend = "Total Cost of " + product + ": " + sMoney + "";
			itemValues.add(toSend);
			totalValue += money;
		}
		String sTotal = formatter.format(totalValue);
		String totalCost = "Total Cost of Items: " + sTotal + ""; 
		
		model.addAttribute("moneyItems", itemValues);
		model.addAttribute("totalCost", totalCost);
		return "viewCart";
	}
	
	@GetMapping("deleteOrderItem/{id}")
	public String deleteOrderItem(@PathVariable("id") long id, User user, Model model) {
		OrderItems oi = oiRepo.findById(id);
		
		oiRepo.delete(oi);
		model.addAttribute("user", user);
		Orders o = oRepo.findByCustomerId(user.getUserId());
		List<OrderItems> orderitems = oiRepo.findByOrderId(o);
		
		model.addAttribute("orderitems", orderitems);
		
		List<String> itemValues = new ArrayList<>();
		double totalValue = 0;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		for (int i = 0; i < orderitems.size(); i++) {
			OrderItems toRead = orderitems.get(i);
			MenuItems productId = toRead.getItemId();
			double money = productId.getItemPrice() * toRead.getQuantity();
			String sMoney = formatter.format(money);
			String product = productId.getItemName();
			String toSend = "Total Cost of " + product + ": " + sMoney + "";
			itemValues.add(toSend);
			totalValue += money;
		}
		String sTotal = formatter.format(totalValue);
		String totalCost = "Total Cost of Items: " + sTotal + "";
		
		model.addAttribute("moneyItems", itemValues);
		model.addAttribute("totalCost", totalCost);
		return "viewCart";
	}
	
	@GetMapping("/usePromoCode/{promo}")
	public String usePromo(@PathVariable("promo") String promo, User user, Model model) {
		model.addAttribute("user", user);
		Orders o = oRepo.findByCustomerId(user.getUserId());
		List<OrderItems> orderitems = oiRepo.findByOrderId(o);
		model.addAttribute("orderitems", orderitems);
		
		List<String> itemValues = new ArrayList<>();
		double totalValue = 0;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		for (int i = 0; i < orderitems.size(); i++) {
			OrderItems toRead = orderitems.get(i);
			MenuItems productId = toRead.getItemId();
			double money = productId.getItemPrice() * toRead.getQuantity();
			String sMoney = formatter.format(money);
			String product = productId.getItemName();
			String toSend = "Total Cost of " + product + ": " + sMoney + "";
			itemValues.add(toSend);
			totalValue += money;
		}
		String sTotal = formatter.format(totalValue);
		String totalCost = "Total Cost of Items: " + sTotal + "";
		
		
		Promotion thisPromo = promoRepo.findByName(promo);
		
		if (thisPromo == null) {
			model.addAttribute("moneyItems", itemValues);
			model.addAttribute("totalCost", totalCost);
			return "viewCart";
		}
		
		String active = thisPromo.getActive();
		if (active.compareTo("N") == 0) {
			model.addAttribute("moneyItems", itemValues);
			model.addAttribute("totalCost", totalCost);
			return "viewCart";
		}
		
		String type = thisPromo.getType();
		if (type.compareTo("P") == 0) {
			double percentOff = thisPromo.getAmount();
			double offPercent = 1 - percentOff;
			for (int i = 0; i < orderitems.size(); i++) {
				OrderItems toRead = orderitems.get(i);
				MenuItems productId = toRead.getItemId();
				double money = productId.getItemPrice() * toRead.getQuantity();
				money *= offPercent;
				String sMoney = formatter.format(money);
				String product = productId.getItemName();
				String toSend = "Total Cost of " + product + ": " + sMoney + "";
				itemValues.add(toSend);
				totalValue += money;
			}
			sTotal = formatter.format(totalValue);
			totalCost = "Total Cost of Items: " + sTotal + "";
			model.addAttribute("moneyItems", itemValues);
			model.addAttribute("totalCost", totalCost);
		}
		else {
			double amountOff = thisPromo.getAmount();
			double newTotal = totalValue - amountOff;
			sTotal = formatter.format(newTotal);
			totalCost = "Total Cost of Items: " + sTotal + "";
			model.addAttribute("moneyItems", itemValues);
			model.addAttribute("totalCost", totalCost);
		}
		return "viewCart";
	}
	

	
	
	
	
/****************************Login Related Edits*********************************************/
	@RequestMapping(value = "loginUser", method = RequestMethod.POST)
	public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password,
			@ModelAttribute("user") User user, Model model) {

		try {

			User u = uRepo.findByUserName(username);
			//model.addAttribute("tempUser", u);

			if (u.getPassWord().equals(password) && u.getUserAuth().equals("ADMIN")) {
				
				user.setFirstName(u.getFirstName());
				user.setLastName(u.getLastName());
				user.setVisitDate(u.getVisitDate());
				user.setEmail(u.getEmail());
				user.setPhoneNumber(u.getPhoneNumber());
				user.setUserName(u.getUserName());
				user.setUserAuth(u.getUserAuth());
				model.addAttribute("user", u);
				return "viewAdmin";

			} else if (u.getPassWord().equals(password) && u.getUserAuth().equals("CUSTOMER")) {
				
				user.setFirstName(u.getFirstName());
				user.setLastName(u.getLastName());
				user.setVisitDate(u.getVisitDate());
				user.setEmail(u.getEmail());
				user.setPhoneNumber(u.getPhoneNumber().toString());
				user.setUserName(u.getUserName());
				user.setUserAuth(u.getUserAuth());
				model.addAttribute("user", u);
				return "viewCustomer";
			} else {
			return "loginError";
			}
		}catch (Exception e) {
			return "/loginError";
		}
	}
	
	 @RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public String page4(@ModelAttribute User user, HttpSession session, SessionStatus status, Model model) {
	        //uRepo.save(user);
	        status.setComplete();
	        session.removeAttribute("user");
	        model.addAttribute("message", "Logged out. Thank you for visiting.");
	        return "/login";
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
	
	@GetMapping("/adminAddPromotion")
	public String addNewPromotion(Model model) {
		Promotion promo = new Promotion();
		model.addAttribute("newPromotionItem", promo);
		return "adminAddPromotion";
	}
	
	@PostMapping("/adminAddPromotion")
	public String addNewPromotionItem(@ModelAttribute Promotion promo, Model model) {
		promoRepo.save(promo);
		return "adminportal";
	}
	
	@GetMapping("viewPromotions")
	public String viewDepartmentItems(Model model) {
		model.addAttribute("promotions", promoRepo.findAll());
		return "adminViewPromotions";
	}
	
	@GetMapping("adminPromoEdit/{id}")
	public String showPromoUpdateForm(@PathVariable("id") long id, Model model) {
		Promotion promo = promoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Promotion Id: " + id));
		
		model.addAttribute("promotion", promo);
		return "adminPromoEdit";
	}
	
	@PostMapping("adminPromoEdit/{id}")
	public String updatePromoItem(@PathVariable("id") long id, @Valid Promotion promo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			promo.setId(id);
			return "adminPromoEdit";
		}
		
		promoRepo.save(promo);
		model.addAttribute("promotions", promoRepo.findAll());
		return "adminViewPromotions";
	}
	
	@GetMapping("adminPromoDelete/{id}")
	public String deletePromoItem(@PathVariable("id") long id, Model model) {
		Promotion promo = promoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Promotion Id: " + id));
		
		promoRepo.delete(promo);
		model.addAttribute("promotions", promoRepo.findAll());
		return "adminViewPromotions";
	}
	
}
