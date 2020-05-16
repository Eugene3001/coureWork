package ua.edu.onu.autoChecking.dao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "passport", nullable = false, unique = true)
    private String passport;

    @Column(name = "license_number", nullable = false)
    private String licenseNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "flat")
    private Long flat;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @OneToMany(
            mappedBy = "driver"
    )
    private List<Protocol> protocols;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumns({
            @JoinColumn(name = "driver_id", referencedColumnName = "driver_id", insertable = false, updatable = false),
            @JoinColumn(name = "auto_id", referencedColumnName = "auto_id", insertable = false, updatable = false),
            @JoinColumn(name = "start_date", referencedColumnName = "start_date", insertable = false, updatable = false)
    })
    private Story story;
}
