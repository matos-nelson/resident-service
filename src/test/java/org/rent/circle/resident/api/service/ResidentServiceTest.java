package org.rent.circle.resident.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.persistence.model.Resident;
import org.rent.circle.resident.api.persistence.repository.ResidentRepository;
import org.rent.circle.resident.api.service.mapper.ResidentMapper;

@QuarkusTest
public class ResidentServiceTest {

    @InjectMock
    ResidentRepository residentRepository;

    @InjectMock
    ResidentMapper residentMapper;

    @Inject
    ResidentService residentService;

    @Test
    public void saveResidentInfo_WhenCalled_ShouldReturnSavedResidentId() {
        // Arrange
        String managerId = "abc123";
        SaveResidentInfoDto saveResidentInfo = SaveResidentInfoDto.builder()
            .propertyId(1L)
            .tenantId(2L)
            .userId("123")
            .build();

        Resident resident = new Resident();
        resident.setId(100L);
        when(residentMapper.toModel(saveResidentInfo)).thenReturn(resident);

        // Act
        Long result = residentService.saveResidentInfo(saveResidentInfo, managerId);

        // Assert
        assertNotNull(result);
        assertEquals(resident.getId(), result);
    }

    @Test
    public void getResident_WhenResidentWithGivenIdCantBeFound_ShouldReturnNull() {
        // Arrange
        String managerId = "abc123";
        long residentId = 1;
        when(residentRepository.findByIdAndManagerId(residentId, managerId)).thenReturn(null);
        when(residentMapper.toDto(null)).thenReturn(null);

        // Act
        ResidentDto result = residentService.getResident(residentId, managerId);

        // Assert
        assertNull(result);
    }

    @Test
    public void getResident_WhenCalled_ShouldReturnResident() {
        // Arrange
        String managerId = "abc123";
        long residentId = 100;

        Resident resident = new Resident();
        resident.setId(residentId);

        ResidentDto residentDto = ResidentDto.builder()
            .propertyId(1L)
            .tenantId(2L)
            .build();

        when(residentRepository.findByIdAndManagerId(residentId, managerId)).thenReturn(resident);
        when(residentMapper.toDto(resident)).thenReturn(residentDto);

        // Act
        ResidentDto result = residentService.getResident(residentId, managerId);

        // Assert
        assertEquals(residentDto, result);
    }
}
