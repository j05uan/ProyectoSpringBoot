package com.survey.survey;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.survey.survey.responseOptions.domain.entity.ResponseOptions;
import com.survey.survey.roles.domain.entity.Roles;
import com.survey.survey.security.domain.entity.PermissionEntity;
import com.survey.survey.user.domain.entity.Users;
import com.survey.survey.user.infraestructure.repository.Userrepository;

@SpringBootApplication
public class SurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
	}

	@Bean
	CommandLineRunner init(Userrepository userrepository){
		return args ->{
			//Crear Permisos
			PermissionEntity createPermission = PermissionEntity.builder()
				.name("CREATE")
				.build();


			PermissionEntity readPermission = PermissionEntity.builder()
			.name("READ")
			.build();


			PermissionEntity updatePermission = PermissionEntity.builder()
			.name("UPDATE")
			.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
			.name("DELETE")
			.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
			.name("REFACTOR")
			.build();

			//Crear Roles
			Roles roleAdmin = Roles.builder()
				.name("Admin")
				.permissions(Set.of(createPermission,readPermission,updatePermission,deletePermission))
				.build();

			Roles roleUser = Roles.builder()
				.name("User")
				.permissions(Set.of(readPermission))
				.build();

			//Crear usuarios
			Users userJP = Users.builder()
				.username("JP")
				.password("$2a$10$.hxgRIbNsaft7HM8JBcCH.cvK9dkrL/xoXwu50cW9JB.46JmTM0JK")
				.isEnable(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.credentialNoExpired(true)
				.roles(Set.of(roleAdmin))
				.build();

			Users userYolber = Users.builder()
				.username("Yolber")
				.password("$2a$10$.hxgRIbNsaft7HM8JBcCH.cvK9dkrL/xoXwu50cW9JB.46JmTM0JK")
				.isEnable(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.credentialNoExpired(true)
				.roles(Set.of(roleUser))
				.build();

			userrepository.saveAll(List.of(userJP,userYolber));
		};
	}

}
