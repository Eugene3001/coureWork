package ua.edu.onu.autoChecking.dao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.onu.autoChecking.dao.ids.StoryId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import java.util.Date;

@Entity
@Builder
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

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "finish_date")
    private Date finishDate;

    @OneToMany(
            mappedBy = "story",
            cascade = {CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<Driver> drivers;

    @OneToMany(
            mappedBy = "story",
            cascade = {CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<Automobile> automobiles;
}
