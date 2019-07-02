package groupID.FindDev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FindDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindDevApplication.class, args);
	}

}
