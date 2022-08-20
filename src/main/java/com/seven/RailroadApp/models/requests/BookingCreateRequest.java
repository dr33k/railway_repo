package com.seven.RailroadApp.models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seven.RailroadApp.models.enums.BookingStatus;
import com.seven.RailroadApp.models.enums.SeatType;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookingCreateRequest {
    private UUID voyageNo;
    @NotBlank(message = "Required field",groups = SeatType.class)
    private SeatType seatType;
}
