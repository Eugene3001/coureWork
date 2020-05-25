package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.edu.onu.autoChecking.dao.entities.Driver;
import ua.edu.onu.autoChecking.dto.dtoSpec.DriverDtoSpec;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends CrudRepository<Driver, Long>, JpaSpecificationExecutor<Driver> {
    //Sorting
    @Query("select d from Driver d order by d.birthDate asc")
    List<Driver> getBirthDateSortedList();

    //Selection
    @Query("select d.driverId from Driver d where d.passport = :passport")
    Long getDriverIdByPassport(@Param("passport")String passport);

    @Query("select d.passport from Driver d where d.driverId = :driverId")
    String getPassportByDriverId(@Param("driverId")Long driverId);

    Optional<Driver> findByPassport(String passport);

    @Query(
            value = "select D from Driver D\n" +
                "join Protocol using(driver_id)\n" +
                "where Protocol.status = true and\n" +
                "extract (year from age(Protocol.prep_date)) * 12 + extract (month from age(Protocol.prep_date)) < Protocol.due_date",
            nativeQuery = true
    )
    List<Driver> selectByPastDuePaymentDate();

    @Query(
            value = "select * from vio_count",
            nativeQuery = true
    )
    List<String> firstView();
}
