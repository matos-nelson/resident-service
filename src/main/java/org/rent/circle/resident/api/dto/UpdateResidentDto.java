package org.rent.circle.resident.api.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResidentDto {

    @NotNull
    private Long addressId;

    private String preferredName;

    @NotNull
    private String phone;

    private List<VehicleDto> vehicles;
}
