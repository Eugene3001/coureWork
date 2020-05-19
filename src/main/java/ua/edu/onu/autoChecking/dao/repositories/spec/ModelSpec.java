package ua.edu.onu.autoChecking.dao.repositories.spec;

import org.springframework.data.jpa.domain.Specification;
import ua.edu.onu.autoChecking.dao.entities.Model;

import java.util.Date;

public class ModelSpec {
    public static Specification<Model> withBodyTypeEqual(String bodyType) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (bodyType != null) {
                return criteriaBuilder.equal(root.get("bodyType"), bodyType);
            }

            return null;
        };
    }

    public static Specification<Model> withManufactureYearBetween(Date begin, Date end) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (begin != null && end != null) {
                return criteriaBuilder.between(root.get("manufYear"), begin, end);
            }

            return null;
        };
    }

    public static Specification<Model> buildSearchSpec(String bodyType, Date begin, Date end) {
        return Specification
                .where(withBodyTypeEqual(bodyType))
                .and(withManufactureYearBetween(begin, end));
    }
}
