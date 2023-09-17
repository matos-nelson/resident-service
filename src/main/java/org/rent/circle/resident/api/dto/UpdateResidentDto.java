package org.rent.circle.resident.api.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class UpdateResidentDto {

    @NotNull
    private Long addressId;

    private String preferredName;

    @NotNull
    private String phone;

    private List<VehicleDto> vehicles;
}
