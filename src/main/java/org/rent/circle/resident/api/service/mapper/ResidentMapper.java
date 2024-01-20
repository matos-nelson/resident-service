package org.rent.circle.resident.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveCoResidentInfoDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
import org.rent.circle.resident.api.persistence.model.CoResident;
import org.rent.circle.resident.api.persistence.model.Resident;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface ResidentMapper {

    Resident toModel(SaveResidentInfoDto saveResidentInfo);

    CoResident toCoResidentModel(SaveCoResidentInfoDto saveCoResidentInfo);

    void update(UpdateResidentDto updateResidentInfo, @MappingTarget Resident resident);

    ResidentDto toDto(Resident resident);
}
