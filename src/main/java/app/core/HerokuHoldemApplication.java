package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class HerokuHoldemApplication {
	/*
	 * start date 02/07/2021
	 */
	public static void main(String[] args) {
		SpringApplication.run(HerokuHoldemApplication.class, args);
	}

}
