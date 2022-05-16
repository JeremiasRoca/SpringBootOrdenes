package proyecto.proyecto;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import proyecto.proyecto.models.Client;
import proyecto.proyecto.models.MyUser;
import proyecto.proyecto.models.Product;
import proyecto.proyecto.models.Role;
import proyecto.proyecto.repositories.ClientRepository;
import proyecto.proyecto.repositories.UserRepository;
import proyecto.proyecto.services.ClientService;
import proyecto.proyecto.services.UserService;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @InjectMocks
    private UserService uS;

    @Mock
    private UserRepository uR;

    @Test
    public void saveIfNotExists()
    {
        MyUser user = new MyUser();
        user.setUsername("admin");

        when(uR.findByUsername("admin")).thenReturn(Optional.empty());
        when(uR.save(user)).thenReturn(user);
        boolean res = uS.save(user);
        assertTrue(res);

    }
    @Test
    public void saveIfExists()
    {
        MyUser user = new MyUser();
        user.setUsername("admin");

        when(uR.getById("admin")).thenReturn(user);
        when(uR.save(user)).thenReturn(user);
        boolean res = uS.save(user);
        assertFalse(res);

    }

    @Test
    public void updateIfExists() {
        MyUser user = new MyUser();
        user.setUsername("admin");
        when(uR.getById("admin")).thenReturn(user);
        when(uR.save(user)).thenReturn(user);
        assertTrue(uS.update(user, "admin"));
    }
    @Test
    public void updateIfNotExists()
    {
        MyUser user = new MyUser();
        when(uR.getById("admin")).thenReturn(null);
        assertFalse(uS.update(user, "admin"));
    }
    @Test
    public void deleteIfExists()
    {
        assertTrue(uS.delete("admin"));
    }

    @Test
    public void deleteIfNotExists()
    {
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(uR).deleteById("admin");
        assertFalse(uS.delete("admin"));

    }
}
