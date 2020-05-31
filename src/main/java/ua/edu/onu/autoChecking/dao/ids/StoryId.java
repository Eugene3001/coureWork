package ua.edu.onu.autoChecking.dao.ids;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class StoryId implements Serializable {
    @Column(name = "auto_id")
    private Long autoId;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "start_date")
    private String startDate;
}
