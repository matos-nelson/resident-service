package org.rent.circle.resident.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.rent.circle.resident.api.dto.ResidentDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
import org.rent.circle.resident.api.dto.VehicleDto;
import org.rent.circle.resident.api.persistence.model.Resident;
import org.rent.circle.resident.api.persistence.model.Vehicle;

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
            .addressId(1L)
            .preferredName("Preferred Name")
            .fullName("Simple Test")
            .email("simpletest@email.com")
            .phone("1234567890")
            .build();

        // Act
        Resident result = residentMapper.toModel(saveResidentInfo);

        // Assert
        assertNotNull(result);
        assertEquals(saveResidentInfo.getAddressId(), result.getAddressId());
        assertEquals(saveResidentInfo.getPreferredName(), result.getPreferredName());
        assertEquals(saveResidentInfo.getFullName(), result.getFullName());
        assertEquals(saveResidentInfo.getEmail(), result.getEmail());
        assertEquals(saveResidentInfo.getPhone(), result.getPhone());
    }

    @Test
    public void toModel_WhenGivenASaveResidentInfoDtoWithVehicles_ShouldMap() {
        // Arrange
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenceNumber("123-ABC")
            .build();
        SaveResidentInfoDto saveResidentInfo = SaveResidentInfoDto.builder()
            .vehicles(Collections.singletonList(vehicle))
            .build();

        // Act
        Resident result = residentMapper.toModel(saveResidentInfo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getVehicles().size());
        assertEquals(vehicle.getMake(), result.getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenceNumber(), result.getVehicles().get(0).getLicenceNumber());
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
    public void toModel_WhenGivenAResident_ShouldMap() {
        // Arrange
        Resident resident = new Resident();
        resident.setAddressId(1L);
        resident.setPreferredName("Preferred Name");
        resident.setFullName("Simple Test");
        resident.setEmail("simpletest@email.com");
        resident.setPhone("1234567890");

        // Act
        ResidentDto result = residentMapper.toDto(resident);

        // Assert
        assertNotNull(result);
        assertEquals(resident.getAddressId(), result.getAddressId());
        assertEquals(resident.getPreferredName(), result.getPreferredName());
        assertEquals(resident.getFullName(), result.getFullName());
        assertEquals(resident.getEmail(), result.getEmail());
        assertEquals(resident.getPhone(), result.getPhone());
    }

    @Test
    public void toDto_WhenGivenAResidentWithVehicles_ShouldMap() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Make");
        vehicle.setModel("Model");
        vehicle.setYear(1000);
        vehicle.setColor("Color");
        vehicle.setLicenceNumber("123-ABC");

        Resident resident = new Resident();
        resident.setVehicles(Collections.singletonList(vehicle));

        // Act
        ResidentDto result = residentMapper.toDto(resident);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getVehicles().size());
        assertEquals(vehicle.getMake(), result.getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenceNumber(), result.getVehicles().get(0).getLicenceNumber());
    }

    @Test
    public void update_WhenGivenNullUpdateResidentDto_ShouldReturnNull() {
        // Arrange
        Resident resident = new Resident();
        resident.setAddressId(1L);
        resident.setPreferredName("Preferred Name");
        resident.setFullName("Simple Test");
        resident.setEmail("simpletest@email.com");
        resident.setPhone("1234567890");

        // Act
        residentMapper.update(null, resident);

        // Assert
        assertNotNull(resident);
    }

    @Test
    public void update_WhenGivenAnUpdateResidentDto_ShouldMap() {
        // Arrange
        Resident resident = new Resident();
        resident.setAddressId(1L);
        resident.setPreferredName("Preferred Name");
        resident.setFullName("Simple Test");
        resident.setEmail("simpletest@email.com");
        resident.setPhone("1234567890");

        UpdateResidentDto updateResident = UpdateResidentDto.builder()
            .addressId(3L)
            .preferredName("Updated Name")
            .phone("9876543210")
            .build();

        // Act
        residentMapper.update(updateResident, resident);

        // Assert
        assertNotNull(resident);
        assertEquals(updateResident.getAddressId(), resident.getAddressId());
        assertEquals(updateResident.getPreferredName(), resident.getPreferredName());
        assertEquals("Simple Test", resident.getFullName());
        assertEquals("simpletest@email.com", resident.getEmail());
        assertEquals(updateResident.getPhone(), resident.getPhone());
    }

    @Test
    public void update_WhenGivenAnUpdateResidentDtoWithVehicles_ShouldMap() {
        // Arrange
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenceNumber("123-ABC")
            .build();

        UpdateResidentDto updateResidentDto = UpdateResidentDto.builder()
            .vehicles(Collections.singletonList(vehicle))
            .build();

        Resident resident = new Resident();

        // Act
        residentMapper.update(updateResidentDto, resident);

        // Assert
        assertNotNull(resident);
        assertEquals(1, resident.getVehicles().size());
        assertEquals(vehicle.getMake(), resident.getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), resident.getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), resident.getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), resident.getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenceNumber(), resident.getVehicles().get(0).getLicenceNumber());
    }
}
