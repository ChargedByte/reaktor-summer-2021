package dev.chargedbyte.wim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityResponse {
    private Integer code;
    private List<LegacyAvailability> response;

    public AvailabilityResponse(int code, LegacyAvailability[] response) {
        this.code = code;
        this.response = Arrays.asList(response);
    }
}
