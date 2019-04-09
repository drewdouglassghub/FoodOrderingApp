package Spring.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import Spring.repository.FoodAppRepository;


@Controller
@RequestMapping("user/{newUser}")
@SessionAttributes("user")
public class LoginController {

	@Autowired
	FoodAppRepository repo;
	
	    @RequestMapping("/login")
	    public String viewHome() {
	        return "login";
	    }
	    
	 @RequestMapping("/user/{newUser}")
	 	public String handleLogin (@RequestParam("username")String username, Model model) {
	 		model.addAttribute("username", username);
	 		return "/admin/manage";
}
}
