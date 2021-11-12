package javeriana.ms.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EurekaserverApplication.class, args);
	}

}
