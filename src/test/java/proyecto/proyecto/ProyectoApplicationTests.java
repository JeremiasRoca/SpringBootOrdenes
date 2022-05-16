package proyecto.proyecto;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import proyecto.proyecto.models.MyUser;
import proyecto.proyecto.models.Role;
import proyecto.proyecto.services.UserService;

@SpringBootTest
class ProyectoApplicationTests {

	@Autowired
	private PasswordEncoder pE;
	@Autowired
	private UserService uA;
	@Autowired
	private WebApplicationContext webC;
	private MockMvc mvc;

	@Test
	void contextLoads() {
		MyUser user = new MyUser();

		user.setUsername("admin");
		user.setPassword(pE.encode("admin"));
		Role role = new Role();
		role.setId(1);
		role.setName("ADMIN");
		user.setRole(role);

		uA.save(user);
		MyUser r = uA.findByUsername(user.getUsername());
		Assert.assertTrue(r.getPassword().equalsIgnoreCase(user.getPassword()));

	}

}
