package app.core;

import app.core.filters.AddressFilter;
import app.core.security.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
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

	@Bean
	public FilterRegistrationBean<AddressFilter> tokenFilterRegistration(JwtUtil jwtUtil){
		FilterRegistrationBean<AddressFilter> filterRegistrationBean = new FilterRegistrationBean<AddressFilter>();
		AddressFilter addressFilter = new AddressFilter(jwtUtil);
		filterRegistrationBean.setFilter(addressFilter);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
}
