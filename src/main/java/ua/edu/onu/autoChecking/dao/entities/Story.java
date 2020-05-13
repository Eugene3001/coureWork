package ua.edu.onu.autoChecking.dao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.onu.autoChecking.dao.ids.StoryId;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Story")
public class Story {
    @EmbeddedId
    private StoryId id;

    @Column(name = "user_passport")
    private String userPassport;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "finish_date")
    private Date finishDate;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "driver_id",
            referencedColumnName = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "auto_id",
            referencedColumnName = "auto_id")
    private Automobile automobile;
}
