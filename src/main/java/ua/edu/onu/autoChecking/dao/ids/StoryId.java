package ua.edu.onu.autoChecking.dao.ids;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class StoryId implements Serializable {
    @Column(name = "auto_id")
    private Long autoId;

    @Column(name = "driver_id")
    private Long driverId;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "start_date")
    private Date startDate;
}
