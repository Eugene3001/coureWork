package ua.edu.onu.autoChecking.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.edu.onu.autoChecking.dao.entities.Policeman;

import java.util.List;
import java.util.Optional;

public interface PolicemanRepository extends CrudRepository<Policeman, Long>, JpaSpecificationExecutor<Policeman> {
    Optional<Policeman> findByName(String name);

    @Query(
            value = "with \"sec_rank\" as \n" +
                    "(select \"P\".token_number, count(\"Pr\".token_number) cnt_2 from Policeman \"P\"\n" +
                    "join Protocol \"Pr\" using(token_number)\n" +
                    "join Driver \"D\" on \"D\".driver_id = \"Pr\".driver_id\n" +
                    "where extract(year from age(\"D\".birth_date)) < ?1 or\n" +
                    "extract(year from age(\"D\".birth_date)) > ?2\n" +
                    "group by \"P\".token_number),\n" +
                    "\"third_rank\" as \n" +
                    "(select \"P\".token_number, count(\"Pr\".token_number) cnt_3 from Policeman \"P\"\n" +
                    "join Protocol \"Pr\" using(token_number)\n" +
                    "join Automobile \"A\" on \"A\".auto_id = \"Pr\".auto_id\n" +
                    "join Model \"M\" on \"M\".model_id = \"A\".model_id\n" +
                    "where extract(year from age(\"M\".manuf_year)) > ?3\n" +
                    "group by \"P\".token_number)\n" +
                    "select cnt.token_number \"Номер жетона\", \n" +
                    "name, surname, patronymic, \n" +
                    "dense_rank() over(order by cnt_1) \"Ранг_1\", \n" +
                    "dense_rank() over(order by cnt_2) \"Ранг_2\",\n" +
                    "dense_rank() over(order by cnt_3) \"Ранг_3\"\n" +
                    "from \n" +
                    "(select \"P\".token_number, \"P\".name, \"P\".surname, \"P\".patronymic,   count(\"Pr\".token_number) cnt_1\n" +
                    "from Policeman \"P\"\n" +
                    "join Protocol \"Pr\" using(token_number)\n" +
                    "group by \"P\".name, \"P\".surname, \"P\".patronymic, \"P\".token_number) cnt\n" +
                    "left join \"sec_rank\" using(token_number)\n" +
                    "left join \"third_rank\" using(token_number)",
            nativeQuery = true
    )
    List<String> getRanksByProtocolCountAndDriversAgeBetweenAndAutosAgeMore(Long beginDriverYear, Long endDriverYear, Long autoYear);
}
