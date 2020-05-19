package ua.edu.onu.autoChecking.dao.repositories.spec;

import org.springframework.data.jpa.domain.Specification;
import ua.edu.onu.autoChecking.dao.entities.Automobile;
import ua.edu.onu.autoChecking.dao.entities.Color;

import java.util.Date;

public class AutomobileSpec {
    public static Specification<Automobile> withColorEqual(Color color) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (color != null && color.getColorId() != null) {
                return criteriaBuilder.equal(root.get("color"), color.getColorId());
            }

            return null;
        };
    }

    public static Specification<Automobile> withDateBetween(Date begin, Date end) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (begin != null && end != null) {
                return criteriaBuilder.between(root.get("registrationDate"), begin, end);
            }

            return null;
        };
    }

    public static Specification<Automobile> buildSearchSpec(Color color, Date begin, Date end) {
        return Specification
                .where(withColorEqual(color))
                .and(withDateBetween(begin, end));
    }
}
