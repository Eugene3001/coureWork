package ua.edu.onu.autoChecking.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Violations")
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "violation_number")
    private Long id;

    @Column(name = "violation")
    private String violation;

    @Column(name = "money_penalty")
    private Double moneyPenalty;

    @Column(name = "court")
    private Boolean court;

    @OneToMany(
            mappedBy = "violations"
    )
    private List<Protocol> protocols;
}
