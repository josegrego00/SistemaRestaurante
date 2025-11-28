package pd.proyectomrburger.proyectomrburger;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pd.proyectomrburger.proyectomrburger.models.UserEntity;


@SpringBootApplication
public class ProyectomrburgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectomrburgerApplication.class, args);

	}

}