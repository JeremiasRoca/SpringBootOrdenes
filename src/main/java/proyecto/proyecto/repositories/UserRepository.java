package proyecto.proyecto.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyecto.proyecto.models.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser,String>  {


    @Query(value = "SELECT name FROM my_user INNER JOIN role ON my_user.role_id=role.id WHERE username=:username", nativeQuery = true)
    public String getRole(@Param("username") String username);

    public Optional<MyUser> findByUsername(String username);
}
