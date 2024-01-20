package org.rent.circle.resident.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class SaveCoResidentInfoDto {

    private String preferredName;

    @NotNull
    @NotBlank
    private String fullName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String phone;
}
