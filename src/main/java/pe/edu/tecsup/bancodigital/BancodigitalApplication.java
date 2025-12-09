package pe.edu.tecsup.bancodigital;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j // Usamos Log4j2 a través de SLF4J
public class BancodigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancodigitalApplication.class, args);
		log.info("🚀 BANCO DIGITAL INICIADO - PATRON HEXAGONAL 🚀");
		log.info("👉 Swagger UI: http://localhost:8080/swagger-ui.html");
		log.info("👉 H2 Console: http://localhost:8080/h2-console");
	}

}
