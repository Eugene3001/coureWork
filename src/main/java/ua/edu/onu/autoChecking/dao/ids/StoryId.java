package ua.edu.onu.autoChecking.dao.ids;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.util.Date;

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

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "start_date")
    private Date startDate;
}
