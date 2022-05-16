package proyecto.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import proyecto.proyecto.models.TicketDetails;

public interface TicketDetailRepository extends JpaRepository<TicketDetails, Long> {
    @Query(value = "Select * from ticket_details td inner join ticket t on t.ticket_id = td.ticket_id where td.client_dni = :dni ", nativeQuery = true)
    public List<TicketDetails> getByDni(@Param("dni") String dni);
}
