package az.adauniversity.demo;

import az.adauniversity.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Product,Integer> {
}
