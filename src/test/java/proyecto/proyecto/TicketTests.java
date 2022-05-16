package proyecto.proyecto;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import proyecto.proyecto.models.Client;
import proyecto.proyecto.models.Product;
import proyecto.proyecto.models.Requests.TicketDetailsRequest;
import proyecto.proyecto.models.Requests.TicketRequest;
import proyecto.proyecto.models.Ticket;
import proyecto.proyecto.models.TicketDetails;
import proyecto.proyecto.repositories.TicketRepository;
import proyecto.proyecto.repositories.UserRepository;
import proyecto.proyecto.services.TicketService;
import proyecto.proyecto.services.UserService;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketTests {

    @InjectMocks
    private TicketService tS;

    @Mock
    private TicketRepository tR;

    @Test
    public void createIfNotExists()
    {
        TicketRequest ticketRequest = new TicketRequest("1", new ArrayList<TicketDetailsRequest>());
        ticketRequest.getTicketDetails().add(new TicketDetailsRequest(1, 10));
        Ticket ticket = new Ticket(ticketRequest);

        when(tR.getById(1L)).thenReturn(null);
        when(tR.save(ticket)).thenReturn(ticket);
        assertEquals(tS.save(ticket), ticket);
    }

    @Test
    public void createIfExists()
    {
        TicketRequest ticketRequest = new TicketRequest("1", new ArrayList<TicketDetailsRequest>());
        ticketRequest.getTicketDetails().add(new TicketDetailsRequest(1, 10));
        Ticket ticket = new Ticket(ticketRequest);
        when(tR.getById(1L)).thenReturn(ticket);
        assertNull(tS.save(ticket));
    }

    @Test
    public void getDataIfExists()
    {
        TicketRequest ticketRequest = new TicketRequest("1", new ArrayList<TicketDetailsRequest>());
        ticketRequest.getTicketDetails().add(new TicketDetailsRequest(1, 10));
        Ticket ticket = new Ticket(ticketRequest);
        when(tR.findById(1L)).thenReturn(Optional.of(ticket));
        assertFalse(tS.getById(1L).isEmpty());
    }
    @Test
    public void getDataIfNotExists()
    {
        when(tR.findById(1L)).thenReturn(Optional.empty());
        assertTrue(tS.getById(1L).isEmpty());
    }
}
