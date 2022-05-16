package proyecto.proyecto;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import proyecto.proyecto.models.Client;
import proyecto.proyecto.models.Product;
import proyecto.proyecto.repositories.ClientRepository;
import proyecto.proyecto.repositories.ProductRepository;
import proyecto.proyecto.services.ClientService;
import proyecto.proyecto.services.ProductService;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsTests {

    @InjectMocks
    private ProductService pS;

    @Mock
    private ProductRepository pR;

    @Test
    public void createProductIfNotExists()
    {
        Product product = new Product();
        product.setCode(1);
        when(pR.getById(1L)).thenReturn(null);
        when(pR.save(product)).thenReturn(product);
        assertEquals(pS.save(product), product);
    }
    @Test
    public void createProductIfExists()
    {
        Product product = new Product();
        product.setCode(1);
        when(pR.getById(1L)).thenReturn(product);
        assertNull(pS.save(product));
    }

    @Test
    public void updateProductIfExists()
    {
        Product product = new Product();
        when(pR.getById(1L)).thenReturn(product);
        when(pR.save(product)).thenReturn(product);
        assertEquals(pS.update(product, 1), product);
    }
    @Test
    public void updateProductIfNotExists()
    {
        Product product = new Product();
        when(pR.getById(1L)).thenReturn(null);
        assertNull(pS.update(product, 1));
    }

    @Test
    public void deleteProductIfExists()
    {
        assertTrue(pS.delete(1L));
    }
    @Test
    public void deleteProductIfNotExists()
    {
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(pR).deleteById(1L);
        assertFalse(pS.delete(1));

    }

}
