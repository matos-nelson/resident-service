package org.rent.circle.resident.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
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

    public ResidentDto getResidentByEmail(String email) {
        Resident resident = residentRepository.findByEmail(email);
        return residentMapper.toDto(resident);
    }

    @Transactional
    public void updateResidentInfo(String userId, UpdateResidentDto updateResidentInfo) {
        Resident resident = residentRepository.findByUserId(userId);
        if (resident == null) {
            log.info("Could Not Find Resident For Update");
            return;
        }

        residentMapper.update(updateResidentInfo, resident);
        residentRepository.persist(resident);
    }
}
