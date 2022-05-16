package proyecto.proyecto.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import proyecto.proyecto.models.Ticket;
import proyecto.proyecto.repositories.TicketRepository;

@Service
public class TicketService {

    @Autowired
    TicketRepository tR;

    public List<Ticket> getAll()
    {
        return  tR.findAll();
    }

    public Ticket save(Ticket c)
    {
        if(tR.getById(c.getTicketId()) == null)
        {
            return  tR.save(c);
        }
        else
            return null;

    }

    public Optional<Ticket> getById(long id)
    {
        return tR.findById(id);
    }

    @Transactional
    public Ticket update(Ticket c, long id)
    {
        if(tR.getById(id)!=null)
        {
            c.setTicketId(id);
            return  tR.save(c);
        }
        else {
            return  null;
        }

    }

    public boolean delete(long id)
    {
        try
        {
            tR.deleteById(id);
            return  true;
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }

    }

    public List<Ticket> getByClient(String dni) {
        return tR.getByDni(dni);
    }
}
