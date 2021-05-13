package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
@RequestMapping("/user")
public class userController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	// Adding method for common data
	@ModelAttribute
	public void addCommonData(Model m, Principal p) {

		String uname = p.getName();
		System.out.println("username=" + uname);

		// get the user using username
		User u1 = userRepository.getUserByUserName(uname);
		System.out.println("User=" + u1);
		m.addAttribute("user", u1);

	}

	//Dashboard home
	@RequestMapping("/index")
	public String index(Model m, Principal p) {
		m.addAttribute("title", "User Dashboard");
		return "normal/user_dashboard";
	}

	
	// open add-form handler:
    @GetMapping("/add-contact")
	public String openAddContactForm(Model m) {
		m.addAttribute("title", "Add Contact");
		m.addAttribute("contact", new Contact());

		return "normal/add_contact";
	}

	
    // processing add-contact form
    @PostMapping("/process")
	public String processContact(@Valid @ModelAttribute Contact contact,BindingResult result, Principal principle,
			@RequestParam("pimage") MultipartFile file, HttpSession session,Model m) {
		try {

			 if(result.hasErrors())
			 {
				 System.out.println("result="+result.toString());
				 m.addAttribute("contact",contact);
				 return "normal/add_contact";
			 }
			
			
			String name = principle.getName();
			User u1 = this.userRepository.getUserByUserName(name);

			// processing and uploading file
			if (file.isEmpty()) {
				// if file is empty then try our message
				System.out.println("Image is not uploaded");
				contact.setImage("contact.png");
			} else {
				// file the file to folder and update the file name and contact

				contact.setImage(file.getOriginalFilename());

				File f1 = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(f1.getAbsolutePath() + File.separator + file.getOriginalFilename());

				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Image Uploaded");

			}

			contact.setUser(u1);
			u1.getContacts().add(contact);
			this.userRepository.save(u1);
			System.out.println("data=" + contact);
			System.out.println("Added to Daatabse...");

			session.setAttribute("message", new Message("Contact Add Successfully!! Add More..", "success"));
			return "normal/add_contact";

		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new Message("Something Went wrong!! Try again..", "danger"));

		}

		return "normal/add_contact";
	}

	// View Contacts
	// per page=5[n]
	// current page=0 [page]

	@GetMapping("/show-contacts/{page}")
	public String showContacts(@PathVariable("page") Integer page, Model m1, Principal principle) {
		m1.addAttribute("title", "Show User Contacts");

		// send Contact list

		String uname = principle.getName();
		User user = this.userRepository.getUserByUserName(uname);

		Pageable p1 = PageRequest.of(page, 5);

		Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(), p1);
		m1.addAttribute("contacts", contacts);
		m1.addAttribute("currentPage", page);
		m1.addAttribute("totalPages", contacts.getTotalPages());

		return "normal/show_contacts";
	}

	// Showing Particuler Contact Details:

	@RequestMapping("/{cId}/contact")
	public String showContacts1(@PathVariable("cId") Integer cId, Model m3, Principal principal) {
		System.out.println("CID=" + cId);
		Optional<Contact> c2 = this.contactRepository.findById(cId);
		Contact contact = c2.get();

		String uname = principal.getName();
		User user1 = this.userRepository.getUserByUserName(uname);

		if (user1.getId() == contact.getUser().getId())
			m3.addAttribute("contact", contact);
		m3.addAttribute("title", contact.getName());

		return "normal/contact_details";
	}

	// Delete Contact Handler:

	@GetMapping("/delete/{cid}")
	public String delete(@PathVariable("cid") Integer cId, Model m, Principal principal, HttpSession session) {

		Contact contact = this.contactRepository.findById(cId).get();

		//contact.setUser(null);
		// check
		
		User user1 = this.userRepository.getUserByUserName(principal.getName());
        user1.getContacts().remove(contact);
        
        this.userRepository.save(user1);
		
		//this.contactRepository.delete(contact);

		System.out.println("Contact Deleted");

		session.setAttribute("message", new Message("Contact Deleted Successfully", "success"));

		return "redirect:/user/show-contacts/0";
	}
	
	//Update Form Handler
	
	@PostMapping("/update-contact/{cid}")
	public String updateForm(@PathVariable("cid") Integer cId,Model m)
	{
		m.addAttribute("title","Update Contacts");
	     Contact contact = this.contactRepository.findById(cId).get();
	     m.addAttribute("contact",contact);
		
		return "normal/updateForm";
	}
	
	//update handler
	@RequestMapping(value="/process-update",method=RequestMethod.POST)
	public String updateHandler(@ModelAttribute Contact contact,
			@RequestParam("pimage") MultipartFile file,Model m,HttpSession session,Principal principal)
	{
		try
		{
			//old contact details
			Contact contact2 = this.contactRepository.findById(contact.getcId()).get();
			
			
			//image
			if(!file.isEmpty())
			{
				//file work
				//Delete old photo 
				File f2 = new ClassPathResource("static/img").getFile();
				File f3=new File(f2,contact2.getImage());
				f3.delete();
				
				//update new photo
				

				File f1 = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(f1.getAbsolutePath() + File.separator + file.getOriginalFilename());

				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			
				contact.setImage(file.getOriginalFilename());
			}
			else
			{
				contact.setImage(contact2.getImage());
				
			}
			
			
			User user=this.userRepository.getUserByUserName(principal.getName());
			contact.setUser(user);
			
			this.contactRepository.save(contact);
			session.setAttribute("message", new Message("Your contact is updated...","success"));
			
		
			
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		System.out.println("Contact Name="+contact.getName());
		System.out.println("Contaact Id="+contact.getcId());
		return "redirect:/user/"+contact.getcId()+"/contact";
	}
	
	
	//Profile Handler:
	
	@GetMapping("/profile")
	 public String profile(Model m)
	 {
		 m.addAttribute("title","Profile Page");
		 return "normal/profile";
	 }

}