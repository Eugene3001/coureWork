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

    @OneToMany(
        mappedBy = "model"
    )
    private List<Automobile> automobiles;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "brand_id",
            referencedColumnName = "brand_id",
            insertable = false, updatable = false)
    private Brand brand;
}