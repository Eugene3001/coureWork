package ua.edu.onu.autoChecking.dao.repositories.spec;

import org.springframework.data.jpa.domain.Specification;
import ua.edu.onu.autoChecking.dao.entities.Driver;

import java.util.Date;

public class DriverSpec {
    public static Specification<Driver> withDateBetween(Date begin, Date end) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (begin != null && end != null) {
                return criteriaBuilder.between(root.get("birthDate"), begin, end);
            }

            return null;
        };
    }

    public static Specification<Driver> withCityEqual(String city) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (city != null) {
                return criteriaBuilder.equal(root.get("city"), city);
            }

            return null;
        };
    }

    public static Specification<Driver> withStreetEqual(String street) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (street != null) {
                criteriaBuilder.equal(root.get("street"), street);
            }

            return null;
        };
    }

    public static Specification<Driver> withHouseEqual(String house) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (house != null) {
                return criteriaBuilder.equal(root.get("house"), house);
            }

            return null;
        };
    }

    public static Specification<Driver> withFlatEqual(Long flat) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (flat != null) {
                return criteriaBuilder.equal(root.get("flat"), flat);
            }

            return null;
        };
    }

    public static Specification<Driver> withNameEqual(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (name != null) {
                return criteriaBuilder.equal(root.get("name"), name);
            }

            return null;
        };
    }

    public static Specification<Driver> withSurnameEqual(String surname) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (surname != null) {
                return criteriaBuilder.equal(root.get("surname"), surname);
            }

            return null;
        };
    }

    public static Specification<Driver> withPatronymicEqual(String patronymic) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (patronymic != null) {
                return criteriaBuilder.equal(root.get("patronymic"), patronymic);
            }

            return null;
        };
    }

    public static Specification<Driver> buildSearchSpec(Date begin, Date end, String city,
                                                        String street, String house, Long flat,
                                                        String name, String surname, String patronymic) {
        return Specification
                .where(withDateBetween(begin, end))
                .and(withCityEqual(city))
                .and(withStreetEqual(street))
                .and(withHouseEqual(house))
                .and(withFlatEqual(flat))
                .and(withNameEqual(name))
                .and(withSurnameEqual(surname))
                .and(withPatronymicEqual(patronymic));
    }
}
