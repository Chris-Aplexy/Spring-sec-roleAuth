package com.roleAuth.roleAuthorisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoleAuthorisationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleAuthorisationApplication.class, args);
		/*User user = new User(1L,"Chris", "APLEXY",
				List.of(new Role(1L, "ADMIN")));
		UserService userService;
		userService.registerUser(user);*/
	}

}
