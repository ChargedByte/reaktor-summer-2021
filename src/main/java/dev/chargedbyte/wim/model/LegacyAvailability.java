package dev.chargedbyte.wim.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegacyAvailability {
    private String id;
    @JsonProperty("DATAPAYLOAD")
    private String payload;
}
