package BC_Hospital.Project;

import java.io.Console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.env.SystemEnvironmentPropertySourceEnvironmentPostProcessor;

import BC_Hospital.Project.AuServices.KeyGeneration;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		KeyGeneration.Generate();
		//System.out.println(KeyGeneration.Generate().getPrivateKey().toString());
	}

}
