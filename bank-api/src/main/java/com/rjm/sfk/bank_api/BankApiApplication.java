package com.rjm.sfk.bank_api;

import com.rjm.sfk.bank_api.core.configuration.JpaTransactionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"com.rjm.sfk.bank_api"})
@Import({JpaTransactionConfig.class})
public class BankApiApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);
		System.out.println("********RUN***********");
	}

}
