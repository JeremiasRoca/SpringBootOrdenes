package proyecto.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.proyecto.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    public Client getByDni(String dni);
}
