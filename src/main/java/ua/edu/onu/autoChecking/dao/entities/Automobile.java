package ua.edu.onu.autoChecking.dao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @JsonFormat(pattern = "dd-MM-yyyy")
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

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH
    )
    @JoinColumn(name = "color",
            referencedColumnName = "color_id",
            insertable = false, updatable = false)
    private Color colorId;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "model_id",
            referencedColumnName = "model_id",
            insertable = false, updatable = false)
    private Model model;

    @OneToMany(
            mappedBy = "automobile"
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
