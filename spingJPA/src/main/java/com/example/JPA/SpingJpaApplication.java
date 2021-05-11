package com.example.JPA;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.JPA.dao.UserRepository;
import com.example.JPA.model.User;

@SpringBootApplication
public class SpingJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpingJpaApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		/*
		 * User user = new User(); user.setId(2); user.setName("Sneha Amrutkar");
		 * user.setCity("Dhule"); user.setStatus("I am a Designer");
		 * 
		 * User u1 = userRepository.save(user); System.out.println(u1);
		 */

		/*
		 * //Create a Object of user: User u1=new User(); u1.setName("Nikita Morankar");
		 * u1.setCity("Pachora"); u1.setStatus("She is a Bathroom Singer");
		 * 
		 * 
		 * User u2=new User(); u2.setName("Vrusshali Surywannshi");
		 * u2.setCity("Mungshe"); u2.setStatus("She is a Topper");
		 * 
		 * 
		 * //Save Single User User r=userRepository.save(u1);
		 * System.out.println("Saved User="+r);
		 * 
		 * User u3=new User(); u3.setName("Pradhunya Shewale"); u3.setCity("Tehre");
		 * u3.setStatus("He is a Ghyani Baba");
		 * 
		 * 
		 * List<User> users=List.of(u1,u2,u3); //Save Multiple objects Iterable<User>
		 * result=userRepository.saveAll(users);
		 * 
		 * result.forEach(user ->{ System.out.println(user); });
		 * 
		 */

		// Update user:

		/*
		 * Optional<User> optional=userRepository.findById(3); User user=optional.get();
		 * user.setName("Niki");
		 * 
		 * User result=userRepository.save(user); System.out.println(result);
		 * System.out.println("Done...");
		 */

		// How to get data

		// Iterable<User> itr=userRepository.findAll();
		/*
		 * Iterator<User> i1=itr.iterator(); while(i1.hasNext()) { User u1=i1.next();
		 * System.out.println(u1); }
		 */

		/*
		 * itr.forEach(user ->{ System.out.println(user); });
		 */
		/*
		 * // Delete user userRepository.deleteById(1); System.out.println("Deleted..");
		 */

		/*
		 * List<User> f1 = userRepository.findByName("Vrusshali Surywannshi");
		 * System.out.println(f1);
		 */

		/*
		 * List<User> f2=userRepository.findByNameAndCity("Niki", "Pachora");
		 * System.out.println(f2);
		 */

		List<User> alluser = userRepository.getAllUser();
		alluser.forEach(e -> {
			System.out.println(e);
		});

		System.out.println("----------------------------------------");
		
		List<User> u1 = userRepository.getUserByName("Niki","Pachora");
		u1.forEach(e -> {
			System.out.println(e);
		});
		
		System.out.println("___________________________________");
		
	     userRepository.getUsers().forEach(e->{
			System.out.println(e);
		});

	}
}