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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Protocol")
public class Protocol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "protocol_number")
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "prep_date")
    private Date prepDate;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "due_date")
    private Long dueDate;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "token_number",
            referencedColumnName = "token_number",
            insertable = false, updatable = false)
    private Policeman policeman;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "violation_number",
            referencedColumnName = "violation_number",
            insertable = false, updatable = false)
    private Violation violation;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "auto_id",
            referencedColumnName = "auto_id",
            insertable = false, updatable = false)
    private Automobile automobile;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "driver_id",
            referencedColumnName = "driver_id",
            insertable = false, updatable = false)
    private Driver driver;
}
