package com.example.esercizio12;

import com.example.esercizio12.entities.User;
import com.example.esercizio12.repositories.UserRepo;
import com.example.esercizio12.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles(value = "test")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void createUser() {
		User user = new User();
		user.setName("Anto");
		user.setSurname("Troiano");
		user.setAge(30);
		user.setEmail("anto@gmail.com");

		User userDB = userRepo.saveAndFlush(user);

		assertThat(userService.createUser(user)).isNotNull();
		assertThat(userService.createUser(user)).isEqualTo(new User(1L,"Anto","Troiano",30,"anto@gmail.com"));

	}

	@Test
	void readUsers(){
		User user = new User(1L,"Anto","Anto",30,"Anto@gmail.com");
		User user2 = new User(2L,"Mario","Mario",29,"mario@gmail.com");

		userService.createUser(user);
		userService.createUser(user2);

		assertThat(userService.getAll().size()).isNotZero();
		assertThat(userService.getAll().size()).isEqualTo(2);
	}

	@Test
	void updateUser(){
		User user = new User(1L,"Anto","Anto",30,"Anto@gmail.com");

		userService.createUser(user);
		userService.updateUser(1,new User(1L,"Anto","Anto",30,"mario@gmail"));

		assertThat(userService.getAll()).contains(new User(1L,"Anto","Anto",30,"mario@gmail"));
	}

	@Test
	void deleteUser(){
		User user = new User(1L,"Anto","Anto",30,"Anto@gmail.com");

		userService.createUser(user);

		assertThat(userService.deleteUser(1L)).isEqualTo(user);
		assertThat(userService.getAll()).isEmpty();
		assertThat(userService.getAll().size()).isZero();
	}

}
