package reserve.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

import reserve.control.domain.User;

@SpringBootApplication
public class TodoListApplication {
	private static final Logger log = LoggerFactory.getLogger(TodoListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}
}
