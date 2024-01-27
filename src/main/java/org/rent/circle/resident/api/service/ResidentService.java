package org.rent.circle.resident.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.resident.api.dto.PrimaryResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
import org.rent.circle.resident.api.persistence.model.PrimaryResident;
import org.rent.circle.resident.api.persistence.repository.PrimaryResidentRepository;
import org.rent.circle.resident.api.service.mapper.ResidentMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class ResidentService {

    @Inject
    PrimaryResidentRepository primaryResidentRepository;

    @Inject
    ResidentMapper residentMapper;

    @Transactional
    public Long saveResidentInfo(SaveResidentInfoDto saveResidentInfo, String managerId) {

        PrimaryResident primaryResident = residentMapper.toModel(saveResidentInfo);
        primaryResident.setManagerId(managerId);

        primaryResidentRepository.persist(primaryResident);
        return primaryResident.getId();
    }

    public PrimaryResidentDto getResident(long id, String managerId) {
        PrimaryResident primaryResident = primaryResidentRepository.findByIdAndManagerId(id, managerId);
        return residentMapper.toDto(primaryResident);
    }

    public PrimaryResidentDto getResidentByEmail(String email) {
        PrimaryResident primaryResident = primaryResidentRepository.findByEmail(email);
        return residentMapper.toDto(primaryResident);
    }

    @Transactional
    public void updateResidentInfo(String userId, UpdateResidentDto updateResidentInfo) {
        PrimaryResident primaryResident = primaryResidentRepository.findByUserId(userId);
        if (primaryResident == null) {
            log.info("Could Not Find Resident For Update");
            return;
        }

        residentMapper.update(updateResidentInfo, primaryResident);
        primaryResidentRepository.persist(primaryResident);
    }
}
