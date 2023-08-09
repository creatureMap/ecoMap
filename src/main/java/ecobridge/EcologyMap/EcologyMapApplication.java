package ecobridge.EcologyMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"ecobridge.EcologyMap.domain"})
public class EcologyMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcologyMapApplication.class, args);
	}

}
