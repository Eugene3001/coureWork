package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.edu.onu.autoChecking.dao.entities.Automobile;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface AutomobileRepository extends CrudRepository<Automobile, Long>, JpaSpecificationExecutor<Automobile> {
    //Sorting
    @Query("select a from Automobile a order by a.registrationDate asc")
    List<Automobile> getDateSortedListAsc();

    //Selection
    @Query("select a.autoId from Automobile a where a.vehicleIdNumber = :vehicleIdNumber")
    Long getAutoIdByVehicleIdNumber(@Param("vehicleIdNumber") String vehicleIdNumber);

    Optional<Automobile> findAutomobileByAutoId(Long autoId);

    Optional<Automobile> findByVehicleIdNumber(String vehicleIdNumber);

    @Query(
            value =
                    "select count(*) from Automobile\n" +
                    "join Model using(model_id)\n" +
                    "join Brand on Brand.brand_id = Model.brand_id\n" +
                    "where extract (year from Model.manuf_year) >= ?3 and\n" +
                    "\t  extract (year from Model.manuf_year) <= ?4 and \n" +
                    "\t  Model.body_type = ?2 and Brand.brand_name = ?1",
            nativeQuery = true
    )
    Long countByBrandAndBodyTypeAndPeriod(String brandName, String bodyType, Long yearBegin, Long yearEnd);

    @Query(
            value = "select auto_id, vehicle_id_number, registration_date,\n" +
                    "Model.model_name, Color.color_name, engine_number, registration_number\n" +
                    "from Automobile\n" +
                    "join Color on Color.color_id = color\n" +
                    "join Model using(model_id)\n" +
                    "join Brand on Brand.brand_id = Model.brand_id\n" +
                    "where auto_id != all(select auto_id from Protocol)",
            nativeQuery = true
    )
    List<String> selectByProtocolFlagEqualNull();
}

