package com.smart.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
public class HomeController {

	// Password-Encoder
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// Create User repossitory(dao)
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String home(Model m) {

		m.addAttribute("title", "Home-Smart Contact Manager");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model m) {

		m.addAttribute("title", "About-Smart Contact Manager");
		return "about";
	}

	@RequestMapping("/signup")
	public String register(Model m) {

		m.addAttribute("title", "Registration-Smart Contact Manager");
		m.addAttribute("user", new User());
		return "signup";
	}

	// Handling for Registering user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m1,
			HttpSession session) {

		try {

			if (!agreement) {
				System.out.println("You have not agreed terms and conditions!!");
				throw new Exception("You have not agreed terms and conditions!!");
			}

			if (result1.hasErrors()) {
				System.out.println("Error" + result1.toString());
				m1.addAttribute("user", user);
				return "signup";
			}

			/*
			 * System.out.println("Agreement=" + agreement); System.out.println("User" +
			 * user);
			 */
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			User result = this.userRepository.save(user);

			// it returns data after submit
			m1.addAttribute("user", result);
			m1.addAttribute("user", new User());
			session.setAttribute("message", new Message("SuccessFully Registered!!", "alert-success"));
			return "signup";

		} catch (Exception e) {
			e.printStackTrace();
			m1.addAttribute("user", user);
			session.setAttribute("message", new Message("Somethinng Went Wrong!!" + e.getMessage(), "alert-danger"));

		}
		return "signup";
	}

	// Handler for Custom login
	@RequestMapping("/signIn")
	public String customLogin(Model m2) {
		m2.addAttribute("title", "Login Page");
		return "Login";
	}

}
