package org.rent.circle.resident.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.persistence.model.Resident;
import org.rent.circle.resident.api.persistence.repository.ResidentRepository;
import org.rent.circle.resident.api.service.mapper.ResidentMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class ResidentService {

    @Inject
    ResidentRepository residentRepository;

    @Inject
    ResidentMapper residentMapper;

    @Transactional
    public Long saveResidentInfo(SaveResidentInfoDto saveResidentInfo, String managerId) {

        Resident resident = residentMapper.toModel(saveResidentInfo);
        resident.setManagerId(managerId);

        residentRepository.persist(resident);
        return resident.getId();
    }

    public ResidentDto getResident(long id, String managerId) {
        Resident resident = residentRepository.findByIdAndManagerId(id, managerId);
        return residentMapper.toDto(resident);
    }
}
