package org.rent.circle.resident.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.resident.api.dto.CoResidentDto;
import org.rent.circle.resident.api.dto.PrimaryResidentDto;
import org.rent.circle.resident.api.dto.SaveCoResidentInfoDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
import org.rent.circle.resident.api.persistence.model.CoResident;
import org.rent.circle.resident.api.persistence.model.PrimaryResident;
import org.rent.circle.resident.api.persistence.model.Resident;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface ResidentMapper {

    PrimaryResident toModel(SaveResidentInfoDto saveResidentInfo);

    CoResident toCoResidentModel(SaveCoResidentInfoDto saveCoResidentInfo);

    void update(UpdateResidentDto updateResidentInfo, @MappingTarget Resident resident);

    PrimaryResidentDto toDto(PrimaryResident resident);

    CoResidentDto toCoResidentDto(CoResident coResident);
}
