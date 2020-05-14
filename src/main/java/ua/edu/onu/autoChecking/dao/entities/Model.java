package ua.edu.onu.autoChecking.dao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "model_name", unique = true, nullable = false)
    private String modelName;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "body_type", nullable = false)
    private String bodyType;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "manuf_year", nullable = false)
    private Date manufYear;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "model_id",
            referencedColumnName = "model_id",
            insertable = false, updatable = false)
    private Automobile automobile;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "brand_id",
            referencedColumnName = "brand_id",
            insertable = false, updatable = false)
    private Brand brand;
}