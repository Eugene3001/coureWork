package ua.edu.onu.autoChecking.dao.repositories.spec;

import org.springframework.data.jpa.domain.Specification;
import ua.edu.onu.autoChecking.dao.entities.Violation;

public class ViolationSpec {
    public static Specification<Violation> withMoneyPenaltyBetween(Float first, Float second) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (first != null && second != null) {
                return criteriaBuilder.between(root.get("moneyPenalty"), first, second);
            }

            return null;
        };
    }

    public static Specification<Violation> withCourtFlag(String isCourt) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (isCourt != null) {
                return criteriaBuilder.isTrue(root.get("court"));
            }

            return null;
        };
    }

    public static Specification<Violation> withNotCourtFlag(String isNotCourt) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (isNotCourt != null) {
                return criteriaBuilder.isFalse(root.get("court"));
            }

            return null;
        };
    }

    public static Specification<Violation> buildSearchSpec(Float first, Float second, String isCourt, String isNotCourt) {
        return Specification
                .where(withMoneyPenaltyBetween(first, second))
                .and(withCourtFlag(isCourt))
                .and(withNotCourtFlag(isNotCourt));
    }
}
