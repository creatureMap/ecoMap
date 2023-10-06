package ecobridge.EcologyMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EntityScan({"ecobridge.EcologyMap.domain"})
@CrossOrigin
public class EcologyMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcologyMapApplication.class, args);
	}

}
