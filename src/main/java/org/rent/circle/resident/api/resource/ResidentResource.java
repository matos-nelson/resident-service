package org.rent.circle.resident.api.resource;

import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.service.ResidentService;

@AllArgsConstructor
@Authenticated
@Path("/resident")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ResidentResource {

    private final ResidentService residentService;
    private final JsonWebToken jwt;

    @POST
    public Long saveResident(@Valid SaveResidentInfoDto saveResidentInfo) {
        return residentService.saveResidentInfo(saveResidentInfo, jwt.getName());
    }

    @GET
    @Path("/{id}")
    public ResidentDto getResident(@NotNull @PathParam("id") long residentId) {
        return residentService.getResident(residentId, jwt.getName());
    }
}
