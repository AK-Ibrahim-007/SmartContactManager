package com.contactmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactmanager.dao.UserRepository;
import com.contactmanager.entities.User;
import com.contactmanager.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	

//	home page
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home page");
		return "home";
	}

//	about page
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About page");
		return "about";
	}

// signup page
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "SignUp page");
		model.addAttribute("user",new User());
		return "signup";
	}	
	
// Handler for registration
	@RequestMapping(value="/do_register", method = RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("user") User user,BindingResult result1,
			@RequestParam(value = "agreement",defaultValue = "false") boolean agreement,
			Model model,HttpSession session) {
		
		try {
			if(!agreement) {
				System.out.println("You have not agreed term and condition");
				throw new Exception("You have not agreed term and condition");
			}
			
			if(result1.hasErrors()) {
				System.out.println("Error "+result1.toString());
				model.addAttribute("user",user);
				return "signup";
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.jpg");
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			
			System.out.println("Agreement "+agreement);
			System.out.println("User "+user);
			
			User result=this.userRepository.save(user);
			System.out.println("Saved data in database "+result);
			model.addAttribute("user",new User());
			
			session.setAttribute("message" ,new Message("Successfully Registered !!", "alert-success") );
			return "signup";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong !!"+ e.getMessage(), "alert-danger"));
			return "signup";
		}
		
	}

	
	//handler for signin
	@GetMapping("/signin")
	public String loginUser(Model model) {
		model.addAttribute("title","Login Page");
		return "login";
	}
}
