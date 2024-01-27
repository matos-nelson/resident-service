package org.rent.circle.resident.api.dto;

import jakarta.validation.constraints.NotNull;
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
public class SaveResidentInfoDto {

    @NotNull
    private Long propertyId;

    @NotNull
    private String userId;

    @NotNull
    private Long tenantId;
}
