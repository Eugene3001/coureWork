package ua.edu.onu.autoChecking.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
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

    @Column(name = "model_id", nullable = false)
    private Long modelId;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "color", nullable = false)
    private Long color;

    @Column(name = "engine_number", nullable = false, unique = true)
    private String engineNumber;

    @OneToMany(
            mappedBy = "automobile"
    )
    private List<Color> colors;

    @OneToMany(
            mappedBy = "automobile"
    )
    private List<Model> models;

    @OneToMany(
            mappedBy = "automobile"
    )
    private List<Protocol> protocols;

    @OneToMany(
            mappedBy = "automobile"
    )
    private List<Story> stories;
}
