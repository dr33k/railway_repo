package com.seven.ije.models.entities;

import com.seven.ije.models.enums.VoyageStatus;
import com.seven.ije.models.records.VoyageRecord;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name="voyage")
@Data
@ToString
public class Voyage {
    @Id
    @GeneratedValue
    private UUID voyageNo;

    //Join columns on composite primary key
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "departureLocationStateCode", referencedColumnName = "stateCode") ,
            @JoinColumn(name = "departureLocationStationNo", referencedColumnName = "stationNo")
    })
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Location departureLocation;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "arrivalLocationStateCode", referencedColumnName = "stateCode") ,
            @JoinColumn(name = "arrivalLocationStationNo", referencedColumnName = "stationNo")
    })
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Location arrivalLocation;

    @Column(nullable = false)
    private ZonedDateTime departureDateTime;

    @Column(nullable = true)
    private ZonedDateTime arrivalDateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VoyageStatus status;

    @Column(nullable = false)
    private Boolean published;
    public static Voyage of(VoyageRecord record) {
        Voyage voyage = new Voyage();
        BeanUtils.copyProperties(record , voyage);
        return voyage;
    }
}