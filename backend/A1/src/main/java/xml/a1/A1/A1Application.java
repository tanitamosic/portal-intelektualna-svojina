package xml.a1.A1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class A1Application {

	public static void main(String[] args) {
		SpringApplication.run(A1Application.class, args);
	}

}
