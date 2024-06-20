package net.project.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "net.project.springboot")
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public void run(String... args) throws Exception {

		Employee employee1 = Employee.builder()
				.firstName("sai")
				.lastname("potluri")
				.email("raji@gmail.com")
				.build();
		Employee employee2 = Employee.builder()
				.firstName("sanvi")
				.lastname("pap")
				.email("saan@gmail.com")
				.build();

		Employee employee3 = Employee.builder()
				.firstName("anu")
				.lastname("anuu")
				.email("anana@gmail.com")
				.build();

		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
	}
}
