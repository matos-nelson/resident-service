package org.rent.circle.resident.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
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
            .licenceNumber("123-ABC")
            .build();
        SaveResidentInfoDto saveResidentInfo = SaveResidentInfoDto.builder()
            .addressId(1L)
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
}
