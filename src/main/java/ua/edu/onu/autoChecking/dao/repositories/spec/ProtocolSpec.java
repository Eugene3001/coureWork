package ua.edu.onu.autoChecking.dao.repositories.spec;

import org.springframework.data.jpa.domain.Specification;
import ua.edu.onu.autoChecking.dao.entities.Policeman;
import ua.edu.onu.autoChecking.dao.entities.Protocol;
import ua.edu.onu.autoChecking.dao.entities.Violation;

public class ProtocolSpec {
    public static Specification<Protocol> withViolationEqual(Violation violation) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (violation != null) {
                return criteriaBuilder.equal(root.get("violation"), violation);
            }

            return null;
        };
    }

    public static Specification<Protocol> withStatusEqual(Boolean status) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (status != null) {
                return criteriaBuilder.equal(root.get("status"), status);
            }

            return null;
        };
    }

    public static Specification<Protocol> withDueDateEqual(Long dueDate) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (dueDate != null) {
                return criteriaBuilder.equal(root.get("dueDate"), dueDate);
            }

            return null;
        };
    }

    public static Specification<Protocol> withPolicemanEqual(Policeman policeman) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (policeman != null) {
                return criteriaBuilder.equal(root.get("policeman"), policeman);
            }

            return null;
        };
    }

    public static Specification<Protocol> buildSearchSpec(Violation violation, Boolean status, Long dueDate, Policeman policeman) {
        return Specification
                .where(withViolationEqual(violation))
                .and(withStatusEqual(status))
                .and(withDueDateEqual(dueDate))
                .and(withPolicemanEqual(policeman));
    }
}
