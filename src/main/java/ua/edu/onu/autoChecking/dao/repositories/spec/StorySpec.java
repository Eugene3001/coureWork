package ua.edu.onu.autoChecking.dao.repositories.spec;

import org.springframework.data.jpa.domain.Specification;
import ua.edu.onu.autoChecking.dao.entities.Story;

public class StorySpec {
    public static Specification<Story> withUserPassportEqual(String userPassport) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (userPassport != null) {
                return criteriaBuilder.equal(root.get("userPassport"), userPassport);
            }

            return null;
        };
    }

    public static Specification<Story> withIsOwnerFlag(String isOwner) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (isOwner != null) {
                return criteriaBuilder.isNull(root.get("finishDate"));
            }

            return null;
        };
    }

    public static Specification<Story> withIsNotOwnerFlag(String isNotOwner) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (isNotOwner != null) {
                return criteriaBuilder.isNotNull(root.get("finishDate"));
            }

            return null;
        };
    }

    public static Specification<Story> buildSearchSpec(String userPassport, String isOwner, String isNotOwner) {
        return Specification
                .where(withUserPassportEqual(userPassport))
                .and(withIsOwnerFlag(isOwner))
                .and(withIsNotOwnerFlag(isNotOwner));
    }
}
