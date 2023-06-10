package xml.z1.Z1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Z1Application {

	public static void main(String[] args) {
		SpringApplication.run(Z1Application.class, args);
	}

}
