package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.edu.onu.autoChecking.dao.entities.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    @Query("select b from Brand b where b.brandName like :ch")
    List<Brand> findBrandWhereFirstCharacterEqualA(@Param("ch") String ch);
}
