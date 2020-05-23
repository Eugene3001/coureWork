package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.edu.onu.autoChecking.dao.entities.Brand;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Optional<Brand> findByBrandName(String brandName);
}
