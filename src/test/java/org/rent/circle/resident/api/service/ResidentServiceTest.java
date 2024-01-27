package org.rent.circle.resident.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rent.circle.resident.api.dto.PrimaryResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
import org.rent.circle.resident.api.dto.VehicleDto;
import org.rent.circle.resident.api.persistence.model.PrimaryResident;
import org.rent.circle.resident.api.persistence.repository.PrimaryResidentRepository;
import org.rent.circle.resident.api.service.mapper.ResidentMapper;

@QuarkusTest
public class ResidentServiceTest {

    @InjectMock
    PrimaryResidentRepository primaryResidentRepository;

    @InjectMock
    ResidentMapper residentMapper;

    @Inject
    ResidentService residentService;

    @Test
    public void saveResidentInfo_WhenCalled_ShouldReturnSavedResidentId() {
        // Arrange
        String managerId = "abc123";
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenseNumber("123-ABC")
            .build();
        SaveResidentInfoDto saveResidentInfo = SaveResidentInfoDto.builder()
            .propertyId(1L)
            .userId("123")
            .preferredName("Preferred Name")
            .fullName("Simple Test")
            .email("simpletest@email.com")
            .phone("1234567890")
            .vehicles(Collections.singletonList(vehicle))
            .build();

        PrimaryResident primaryResident = new PrimaryResident();
        primaryResident.setId(100L);
        when(residentMapper.toModel(saveResidentInfo)).thenReturn(primaryResident);

        // Act
        Long result = residentService.saveResidentInfo(saveResidentInfo, managerId);

        // Assert
        assertNotNull(result);
        assertEquals(primaryResident.getId(), result);
    }

    @Test
    public void getResident_WhenResidentWithGivenIdCantBeFound_ShouldReturnNull() {
        // Arrange
        String managerId = "abc123";
        long residentId = 1;
        when(primaryResidentRepository.findByIdAndManagerId(residentId, managerId)).thenReturn(null);
        when(residentMapper.toDto(null)).thenReturn(null);

        // Act
        PrimaryResidentDto result = residentService.getResident(residentId, managerId);

        // Assert
        assertNull(result);
    }

    @Test
    public void getResident_WhenCalled_ShouldReturnResident() {
        // Arrange
        String managerId = "abc123";
        long residentId = 100;

        PrimaryResident primaryResident = new PrimaryResident();
        primaryResident.setId(residentId);

        PrimaryResidentDto primaryResidentDto = PrimaryResidentDto.builder()
            .propertyId(1L)
            .fullName("My Resident")
            .email("resident@email.com")
            .phone("1234567890")
            .build();

        when(primaryResidentRepository.findByIdAndManagerId(residentId, managerId)).thenReturn(primaryResident);
        when(residentMapper.toDto(primaryResident)).thenReturn(primaryResidentDto);

        // Act
        PrimaryResidentDto result = residentService.getResident(residentId, managerId);

        // Assert
        assertEquals(primaryResidentDto, result);
    }

    @Test
    public void getResidentByEmail_WhenResidentWithGivenIdCantBeFound_ShouldReturnNull() {
        // Arrange
        String residentEmail = "resident@email.com";
        when(primaryResidentRepository.findByEmail(residentEmail)).thenReturn(null);
        when(residentMapper.toDto(null)).thenReturn(null);

        // Act
        PrimaryResidentDto result = residentService.getResidentByEmail(residentEmail);

        // Assert
        assertNull(result);
    }

    @Test
    public void getResidentByEmail_WhenCalled_ShouldReturnResident() {
        // Arrange
        String residentEmail = "resident@email.com";

        PrimaryResident primaryResident = new PrimaryResident();
        primaryResident.setId(100L);
        primaryResident.setEmail(residentEmail);

        PrimaryResidentDto primaryResidentDto = PrimaryResidentDto.builder()
            .propertyId(1L)
            .fullName("My Resident")
            .email("resident@email.com")
            .phone("1234567890")
            .build();

        when(primaryResidentRepository.findByEmail(residentEmail)).thenReturn(primaryResident);
        when(residentMapper.toDto(primaryResident)).thenReturn(primaryResidentDto);

        // Act
        PrimaryResidentDto result = residentService.getResidentByEmail(residentEmail);

        // Assert
        assertEquals(primaryResidentDto, result);
    }

    @Test
    public void updateResident_WhenResidentIsNotFound_ShouldReturnNotUpdate() {
        // Arrange
        String userId = "123";
        UpdateResidentDto updateResidentDto = UpdateResidentDto.builder().build();
        when(primaryResidentRepository.findByUserId(userId)).thenReturn(null);

        // Act
        residentService.updateResidentInfo(userId, updateResidentDto);

        // Assert
        verify(residentMapper, never()).update(updateResidentDto, null);
        verify(primaryResidentRepository, never()).persist(Mockito.any(PrimaryResident.class));
    }

    @Test
    public void updateResidentInfo_WhenCalled_ShouldUpdate() {
        // Arrange
        String userId = "123";

        PrimaryResident primaryResident = new PrimaryResident();
        primaryResident.setId(1L);
        primaryResident.setUserId(userId);

        UpdateResidentDto updateResidentInfo = UpdateResidentDto.builder()
            .preferredName("Updated Name")
            .phone("9876543210")
            .build();
        when(primaryResidentRepository.findByUserId(userId)).thenReturn(primaryResident);

        // Act
        residentService.updateResidentInfo(userId, updateResidentInfo);

        // Assert
        verify(residentMapper, times(1)).update(updateResidentInfo, primaryResident);
        verify(primaryResidentRepository, times(1)).persist(primaryResident);
    }
}
