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
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
import org.rent.circle.resident.api.dto.VehicleDto;
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

        Resident resident = new Resident();
        resident.setId(100L);
        when(residentMapper.toModel(saveResidentInfo)).thenReturn(resident);

        // Act
        Long result = residentService.saveResidentInfo(saveResidentInfo);

        // Assert
        assertNotNull(result);
        assertEquals(resident.getId(), result);
    }

    @Test
    public void getResidentById_WhenResidentWithGivenIdCantBeFound_ShouldReturnNull() {
        // Arrange
        long residentId = 1;
        when(residentRepository.findById(residentId)).thenReturn(null);
        when(residentMapper.toDto(null)).thenReturn(null);

        // Act
        ResidentDto result = residentService.getResidentById(residentId);

        // Assert
        assertNull(result);
    }

    @Test
    public void getResidentById_WhenCalled_ShouldReturnResident() {
        // Arrange
        long residentId = 100;

        Resident resident = new Resident();
        resident.setId(residentId);

        ResidentDto residentDto = ResidentDto.builder()
            .propertyId(1L)
            .fullName("My Resident")
            .email("resident@email.com")
            .phone("1234567890")
            .build();

        when(residentRepository.findById(residentId)).thenReturn(resident);
        when(residentMapper.toDto(resident)).thenReturn(residentDto);

        // Act
        ResidentDto result = residentService.getResidentById(residentId);

        // Assert
        assertEquals(residentDto, result);
    }

    @Test
    public void getResidentByEmail_WhenResidentWithGivenIdCantBeFound_ShouldReturnNull() {
        // Arrange
        String residentEmail = "resident@email.com";
        when(residentRepository.findByEmail(residentEmail)).thenReturn(null);
        when(residentMapper.toDto(null)).thenReturn(null);

        // Act
        ResidentDto result = residentService.getResidentByEmail(residentEmail);

        // Assert
        assertNull(result);
    }

    @Test
    public void getResidentByEmail_WhenCalled_ShouldReturnResident() {
        // Arrange
        String residentEmail = "resident@email.com";

        Resident resident = new Resident();
        resident.setId(100L);
        resident.setEmail(residentEmail);

        ResidentDto residentDto = ResidentDto.builder()
            .propertyId(1L)
            .fullName("My Resident")
            .email("resident@email.com")
            .phone("1234567890")
            .build();

        when(residentRepository.findByEmail(residentEmail)).thenReturn(resident);
        when(residentMapper.toDto(resident)).thenReturn(residentDto);

        // Act
        ResidentDto result = residentService.getResidentByEmail(residentEmail);

        // Assert
        assertEquals(residentDto, result);
    }

    @Test
    public void updateResident_WhenResidentIsNotFound_ShouldReturnNotUpdate() {
        // Arrange
        String userId = "123";
        UpdateResidentDto updateResidentDto = UpdateResidentDto.builder().build();
        when(residentRepository.findByUserId(userId)).thenReturn(null);

        // Act
        residentService.updateResidentInfo(userId, updateResidentDto);

        // Assert
        verify(residentMapper, never()).update(updateResidentDto, null);
        verify(residentRepository, never()).persist(Mockito.any(Resident.class));
    }

    @Test
    public void updateResidentInfo_WhenCalled_ShouldUpdate() {
        // Arrange
        String userId = "123";

        Resident resident = new Resident();
        resident.setId(1L);
        resident.setUserId(userId);

        UpdateResidentDto updateResidentInfo = UpdateResidentDto.builder()
            .preferredName("Updated Name")
            .phone("9876543210")
            .build();
        when(residentRepository.findByUserId(userId)).thenReturn(resident);

        // Act
        residentService.updateResidentInfo(userId, updateResidentInfo);

        // Assert
        verify(residentMapper, times(1)).update(updateResidentInfo, resident);
        verify(residentRepository, times(1)).persist(resident);
    }
}
