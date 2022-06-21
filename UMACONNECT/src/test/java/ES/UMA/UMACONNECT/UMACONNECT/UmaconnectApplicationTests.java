package ES.UMA.UMACONNECT.UMACONNECT;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest
class UmaconnectApplicationTests {

	@Test
	void contextLoads() {
		UmaconnectApplication.main(new String[]{"test"});
	}

}
