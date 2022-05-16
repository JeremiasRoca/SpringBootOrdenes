package proyecto.proyecto.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import proyecto.proyecto.models.Client;
import proyecto.proyecto.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository cR;

    public List<Client> getAll()
    {
        return  cR.findAll();
    }

    public Client save(Client c)
    {
        if(cR.getById(c.getDni()) == null)
        {

            return  cR.save(c);
        }
        else {
            return  null;
        }
    }
    @Transactional
    public Client update(Client c, String dni)
    {
        if(cR.getById(dni) != null)
        {
            c.setDni(dni);
            return  cR.save(c);
        }
        else {
            return  null;
        }

    }
    public Client getClientByDni(String dni)
    {
        return cR.getByDni(dni);
    }
    public boolean delete(String dni)
    {
        try
        {
            cR.deleteById(dni);
            return  true;
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }

    }
}
