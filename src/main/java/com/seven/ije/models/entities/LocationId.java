package com.seven.ije.models.entities;

import com.seven.ije.models.enums.StateCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
//Composite primary Key class
@Data
@Validated
@Embeddable
@NoArgsConstructor
@ToString
public class LocationId implements Serializable {

    @NotBlank(message = "Required field")
    @Pattern(regexp = "^[A-Z]{4}$", message = "Incorrect format. Must be four(4) letters from A-Z (not a-z)")
    @Enumerated(EnumType.STRING)
    private StateCode stateCode;
    @NotBlank(message = "Required field")
    @Pattern(regexp = "^[0-9]{2}$",message = "Incorrect format. Must be a two(2) digit number: 01,02 ...")
    private String stationNo;

    public LocationId(StateCode stateCode , String stationNo) {
        this.stateCode = stateCode;
        this.stationNo = stationNo;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
