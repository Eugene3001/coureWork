package ua.edu.onu.autoChecking.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Automobile")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id")
    private Long autoId;

    @Column(name = "vehicle_id_number", nullable = false, unique = true)
    private String vehicleIdNumber;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "engine_number", nullable = false, unique = true)
    private String engineNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "color", referencedColumnName = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private Model model;

    @OneToMany(mappedBy = "automobile")
    private List<Protocol> protocols = new ArrayList<>();

    @OneToMany(mappedBy = "automobile")
    private List<Story> stories = new ArrayList<>();
}
