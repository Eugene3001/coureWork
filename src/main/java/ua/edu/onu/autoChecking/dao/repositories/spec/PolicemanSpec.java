package ua.edu.onu.autoChecking.dao.repositories.spec;

import org.springframework.data.jpa.domain.Specification;
import ua.edu.onu.autoChecking.dao.entities.Policeman;

public class PolicemanSpec {
    public static Specification<Policeman> withRankEqual(String rank) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (rank != null) {
                return criteriaBuilder.equal(root.get("rank"), rank);
            }

            return null;
        };
    }

    public static Specification<Policeman> withNameEqual(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (name != null) {
                return criteriaBuilder.equal(root.get("name"), name);
            }

            return null;
        };
    }

    public static Specification<Policeman> withSurnameEqual(String surname) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (surname != null) {
                return criteriaBuilder.equal(root.get("surname"), surname);
            }

            return null;
        };
    }

    public static Specification<Policeman> withPatronymicEqual(String patronymic) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (patronymic != null) {
                return criteriaBuilder.equal(root.get("patronymic"), patronymic);
            }

            return null;
        };
    }

    public static Specification<Policeman> buildSearchSpec(String rank, String name, String surname, String patronymic) {
        return Specification
                .where(withRankEqual(rank))
                .and(withNameEqual(name))
                .and(withSurnameEqual(surname))
                .and(withPatronymicEqual(patronymic));
    }
}
