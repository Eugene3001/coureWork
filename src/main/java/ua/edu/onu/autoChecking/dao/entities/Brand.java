package ua.edu.onu.autoChecking.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "brand_name", unique = true, nullable = false)
    private String brandName;

    @OneToMany(
            mappedBy = "brand"
    )
    List<Model> models;
}
