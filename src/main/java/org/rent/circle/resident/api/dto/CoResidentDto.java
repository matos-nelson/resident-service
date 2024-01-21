package org.rent.circle.resident.api.dto;

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
public class CoResidentDto {

    private String preferredName;
    private String fullName;
    private String email;
    private String phone;
}
