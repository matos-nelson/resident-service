package org.rent.circle.resident.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.persistence.model.Resident;

@QuarkusTest
public class ResidentMapperTest {

    @Inject
    ResidentMapper residentMapper;

    @Test
    public void toModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        Resident result = residentMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenASaveResidentInfoDto_ShouldMap() {
        // Arrange
        SaveResidentInfoDto saveResidentInfo = SaveResidentInfoDto.builder()
            .propertyId(1L)
            .userId("123")
            .tenantId(2L)
            .build();

        // Act
        Resident result = residentMapper.toModel(saveResidentInfo);

        // Assert
        assertNotNull(result);
        assertEquals(saveResidentInfo.getPropertyId(), result.getPropertyId());
        assertEquals(saveResidentInfo.getUserId(), result.getUserId());
        assertEquals(saveResidentInfo.getTenantId(), result.getTenantId());
    }

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        ResidentDto result = residentMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenAResident_ShouldMap() {
        // Arrange
        Resident resident = new Resident();
        resident.setPropertyId(1L);
        resident.setManagerId("123");
        resident.setUserId("abc");
        resident.setTenantId(2L);

        // Act
        ResidentDto result = residentMapper.toDto(resident);

        // Assert
        assertNotNull(result);
        assertEquals(resident.getId(), result.getId());
        assertEquals(resident.getPropertyId(), result.getPropertyId());
        assertEquals(resident.getTenantId(), result.getTenantId());
    }
}
