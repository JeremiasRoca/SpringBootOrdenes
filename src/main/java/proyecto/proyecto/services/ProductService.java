package proyecto.proyecto.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import proyecto.proyecto.models.Product;
import proyecto.proyecto.repositories.ProductRepository;

@Service
public class ProductService {


    @Autowired
    ProductRepository cR;

    public List<Product> getAll()
    {
        return  cR.findAll();
    }

    public Product save(Product c)
    {
        if(cR.getById(c.getCode()) == null)
            return  cR.save(c);
        else
            return  null;

    }
    @Transactional
    public Product update(Product c, long code)
    {
        if(cR.getById(code) != null)
        {
            c.setCode(code);
            return  cR.save(c);
        }
        else {
            return  null;
        }

    }

    public boolean delete(long code)
    {
        try
        {
            cR.deleteById(code);
            return  true;
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }

    }
}
