package hu.ponte.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

// maven build 1st, to trigger javascipt build and get javascript resources

@EnableAsync		// can use @Async in components methods, to run asycnhronously
@SpringBootApplication(scanBasePackages = "hu.ponte.hr", exclude = {
	MultipartAutoConfiguration.class
})
public class SeniorTestApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SeniorTestApplication.class, args);
	}

}

