package org.rent.circle.resident.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
import org.rent.circle.resident.api.persistence.model.PrimaryResident;
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

        PrimaryResident primaryResident = residentMapper.toModel(saveResidentInfo);
        primaryResident.setManagerId(managerId);

        residentRepository.persist(primaryResident);
        return primaryResident.getId();
    }

    public ResidentDto getResident(long id, String managerId) {
        PrimaryResident primaryResident = residentRepository.findByIdAndManagerId(id, managerId);
        return residentMapper.toDto(primaryResident);
    }

    public ResidentDto getResidentByEmail(String email) {
        PrimaryResident primaryResident = residentRepository.findByEmail(email);
        return residentMapper.toDto(primaryResident);
    }

    @Transactional
    public void updateResidentInfo(String userId, UpdateResidentDto updateResidentInfo) {
        PrimaryResident primaryResident = residentRepository.findByUserId(userId);
        if (primaryResident == null) {
            log.info("Could Not Find Resident For Update");
            return;
        }

        residentMapper.update(updateResidentInfo, primaryResident);
        residentRepository.persist(primaryResident);
    }
}
