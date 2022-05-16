package proyecto.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.proyecto.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
