package org.rent.circle.resident.api.resource;

import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
import org.rent.circle.resident.api.service.ResidentService;

@AllArgsConstructor
@Authenticated
@Path("/resident")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ResidentResource {

    private final ResidentService residentService;

    @POST
    public Long saveResident(@NotNull @Valid SaveResidentInfoDto saveResidentInfo) {
        return residentService.saveResidentInfo(saveResidentInfo);
    }

    @GET
    @Path("/{id}")
    public ResidentDto getResident(@NotNull @PathParam("id") long residentId) {
        return residentService.getResidentById(residentId);
    }

    @GET
    @Path("/email/{email}")
    public ResidentDto getResident(@NotNull @NotBlank @Email @PathParam("email") String residentEmail) {
        return residentService.getResidentByEmail(residentEmail);
    }

    @PATCH
    @Path("/{id}")
    public void updateResident(@NotNull @PathParam("id") long residentId,
        @NotNull @Valid UpdateResidentDto updateResidentInfo) {
        residentService.updateResidentInfo(residentId, updateResidentInfo);
    }
}
