package com.contactmanager.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contactmanager.dao.UserRepository;
import com.contactmanager.entities.Contact;
import com.contactmanager.entities.User;
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// method to adding common data to respons
	@ModelAttribute
	public void addCommonData(Model model, Principal principle) {
		String userName = principle.getName();
		System.out.println("Email : " + userName);

		// getting the user by username
		User user = userRepository.getUserByUserName(userName);

		System.out.println("User Details : " + user);

		model.addAttribute("user", user);

	}

	// home dash board
	@RequestMapping("/index")
	public String dashBoard(Model model, Principal princple) {
		model.addAttribute("title", "User Dasboard");
		return "normal/dash_board";
	}

//open add handler	
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_contact_form";
	}

	// processing add contact form

	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact, Principal principal) {

		String name = principal.getName();
		
		  User user = this.userRepository.getUserByUserName(name);
		  contact.setUser(user); user.getContacts().add(contact);
		  this.userRepository.save(user); System.out.println("Data : " + contact);
		 
		
		System.out.println("Added");
		return "normal/add_contact_form";
	}
}
