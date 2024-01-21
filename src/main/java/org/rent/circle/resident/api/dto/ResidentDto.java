package org.rent.circle.resident.api.dto;

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
public class ResidentDto {

    private Long id;
    private Long propertyId;
    private String preferredName;
    private String fullName;
    private String email;
    private String phone;
    private List<CoResidentDto> coResidents;
    private List<VehicleDto> vehicles;
}
