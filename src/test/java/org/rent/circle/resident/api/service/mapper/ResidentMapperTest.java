package org.rent.circle.resident.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.resident.api.dto.CoResidentDto;
import org.rent.circle.resident.api.dto.PrimaryResidentDto;
import org.rent.circle.resident.api.dto.SaveCoResidentInfoDto;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.UpdateResidentDto;
import org.rent.circle.resident.api.dto.VehicleDto;
import org.rent.circle.resident.api.persistence.model.CoResident;
import org.rent.circle.resident.api.persistence.model.PrimaryResident;
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
        PrimaryResident result = residentMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenASaveResidentInfoDto_ShouldMap() {
        // Arrange
        SaveResidentInfoDto saveResidentInfo = SaveResidentInfoDto.builder()
            .propertyId(1L)
            .userId("123")
            .preferredName("Preferred Name")
            .fullName("Simple Test")
            .email("simpletest@email.com")
            .phone("1234567890")
            .build();

        // Act
        PrimaryResident result = residentMapper.toModel(saveResidentInfo);

        // Assert
        assertNotNull(result);
        assertEquals(saveResidentInfo.getPropertyId(), result.getPropertyId());
        assertEquals(saveResidentInfo.getUserId(), result.getUserId());
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
            .licenseNumber("123-ABC")
            .build();
        SaveResidentInfoDto saveResidentInfo = SaveResidentInfoDto.builder()
            .vehicles(Collections.singletonList(vehicle))
            .build();

        // Act
        PrimaryResident result = residentMapper.toModel(saveResidentInfo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getVehicles().size());
        assertEquals(vehicle.getMake(), result.getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenseNumber(), result.getVehicles().get(0).getLicenseNumber());
    }

    @Test
    public void toModel_WhenGivenASaveResidentInfoDtoWithCoResidents_ShouldMap() {
        // Arrange
        SaveCoResidentInfoDto saveCoResidentInfoDto = SaveCoResidentInfoDto.builder()
            .preferredName("SaveCo")
            .fullName("Co Resident")
            .email("coresident@email.com")
            .phone("1238909900")
            .build();
        SaveResidentInfoDto saveResidentInfo = SaveResidentInfoDto.builder()
            .coResidents(Collections.singletonList(saveCoResidentInfoDto))
            .build();

        // Act
        PrimaryResident result = residentMapper.toModel(saveResidentInfo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getCoResidents().size());
        assertEquals(saveCoResidentInfoDto.getPreferredName(), result.getCoResidents().get(0).getPreferredName());
        assertEquals(saveCoResidentInfoDto.getFullName(), result.getCoResidents().get(0).getFullName());
        assertEquals(saveCoResidentInfoDto.getEmail(), result.getCoResidents().get(0).getEmail());
        assertEquals(saveCoResidentInfoDto.getPhone(), result.getCoResidents().get(0).getPhone());
    }

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        PrimaryResidentDto result = residentMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenAResident_ShouldMap() {
        // Arrange
        PrimaryResident resident = new PrimaryResident();
        resident.setPropertyId(1L);
        resident.setPreferredName("Preferred Name");
        resident.setFullName("Simple Test");
        resident.setEmail("simpletest@email.com");
        resident.setPhone("1234567890");

        // Act
        PrimaryResidentDto result = residentMapper.toDto(resident);

        // Assert
        assertNotNull(result);
        assertEquals(resident.getId(), result.getId());
        assertEquals(resident.getPropertyId(), result.getPropertyId());
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
        vehicle.setLicenseNumber("123-ABC");

        PrimaryResident primaryResident = new PrimaryResident();
        primaryResident.setVehicles(Collections.singletonList(vehicle));

        // Act
        PrimaryResidentDto result = residentMapper.toDto(primaryResident);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getVehicles().size());
        assertEquals(vehicle.getMake(), result.getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenseNumber(), result.getVehicles().get(0).getLicenseNumber());
    }

    @Test
    public void toDto_WhenGivenAResidentWithCoResidents_ShouldMap() {
        // Arrange
        CoResident coResident = new CoResident();
        coResident.setEmail("coresident@email.com");
        coResident.setFullName("My resident");
        coResident.setPreferredName("Co Resident");
        coResident.setPhone("1231231234");

        PrimaryResident primaryResident = new PrimaryResident();
        primaryResident.setCoResidents(Collections.singletonList(coResident));

        // Act
        PrimaryResidentDto result = residentMapper.toDto(primaryResident);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getCoResidents().size());
        assertEquals(coResident.getEmail(), result.getCoResidents().get(0).getEmail());
        assertEquals(coResident.getFullName(), result.getCoResidents().get(0).getFullName());
        assertEquals(coResident.getPreferredName(), result.getCoResidents().get(0).getPreferredName());
        assertEquals(coResident.getPhone(), result.getCoResidents().get(0).getPhone());
    }

    @Test
    public void toCoResidentDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange
        CoResident coResident = new CoResident();
        coResident.setEmail("coresident@email.com");
        coResident.setFullName("My resident");
        coResident.setPreferredName("Co Resident");
        coResident.setPhone("1231231234");

        // Act
        CoResidentDto result = residentMapper.toCoResidentDto(coResident);

        // Assert
        assertNotNull(result);
        assertEquals(coResident.getEmail(), result.getEmail());
        assertEquals(coResident.getFullName(), result.getFullName());
        assertEquals(coResident.getPreferredName(), result.getPreferredName());
        assertEquals(coResident.getPhone(), result.getPhone());
    }

    @Test
    public void toCoResidentDto_WhenGivenAValidCoResident_ShouldMap() {
        // Arrange
        CoResident coResident = new CoResident();
        coResident.setEmail("coresident@email.com");
        coResident.setFullName("My resident");
        coResident.setPreferredName("Co Resident");
        coResident.setPhone("1231231234");

        // Act
        CoResidentDto result = residentMapper.toCoResidentDto(coResident);

        // Assert
        assertNotNull(result);
        assertEquals(coResident.getEmail(), result.getEmail());
        assertEquals(coResident.getFullName(), result.getFullName());
        assertEquals(coResident.getPreferredName(), result.getPreferredName());
        assertEquals(coResident.getPhone(), result.getPhone());
    }

    @Test
    public void update_WhenGivenNullUpdateResidentDto_ShouldReturnNull() {
        // Arrange
        PrimaryResident primaryResident = new PrimaryResident();
        primaryResident.setPropertyId(1L);
        primaryResident.setPreferredName("Preferred Name");
        primaryResident.setFullName("Simple Test");
        primaryResident.setEmail("simpletest@email.com");
        primaryResident.setPhone("1234567890");

        // Act
        residentMapper.update(null, primaryResident);

        // Assert
        assertNotNull(primaryResident);
    }

    @Test
    public void update_WhenGivenAnUpdateResidentDto_ShouldMap() {
        // Arrange
        PrimaryResident primaryResident = new PrimaryResident();
        primaryResident.setPropertyId(1L);
        primaryResident.setPreferredName("Preferred Name");
        primaryResident.setFullName("Simple Test");
        primaryResident.setEmail("simpletest@email.com");
        primaryResident.setPhone("1234567890");

        UpdateResidentDto updateResident = UpdateResidentDto.builder()
            .preferredName("Updated Name")
            .phone("9876543210")
            .build();

        // Act
        residentMapper.update(updateResident, primaryResident);

        // Assert
        assertNotNull(primaryResident);
        assertEquals(updateResident.getPreferredName(), primaryResident.getPreferredName());
        assertEquals("Simple Test", primaryResident.getFullName());
        assertEquals("simpletest@email.com", primaryResident.getEmail());
        assertEquals(updateResident.getPhone(), primaryResident.getPhone());
    }

    @Test
    public void update_WhenGivenAnUpdateResidentDtoWithVehicles_ShouldMap() {
        // Arrange
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenseNumber("123-ABC")
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
        assertEquals(vehicle.getLicenseNumber(), resident.getVehicles().get(0).getLicenseNumber());
    }

    @Test
    public void update_WhenAResidentHasAVehicleAndGivenUpdateResidentDtoHasNoVehicles_ShouldMap() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Make");
        vehicle.setModel("Model");
        vehicle.setYear(1000);
        vehicle.setColor("Color");
        vehicle.setLicenseNumber("123-ABC");

        Resident resident = new Resident();
        resident.setVehicles(Collections.singletonList(vehicle));

        UpdateResidentDto updateResidentDto = UpdateResidentDto.builder().build();

        // Act
        residentMapper.update(updateResidentDto, resident);

        // Assert
        assertNotNull(resident);
        assertNull(resident.getVehicles());
    }

    @Test
    public void update_WhenAResidentHasMoreVehiclesThanGivenUpdateResidentDtoVehicles_ShouldMap() {
        // Arrange
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setMake("Make");
        vehicle1.setModel("Model");
        vehicle1.setYear(1000);
        vehicle1.setColor("Color");
        vehicle1.setLicenseNumber("123-ABC");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setMake("Make");
        vehicle2.setModel("Model");
        vehicle2.setYear(1000);
        vehicle2.setColor("Color");
        vehicle2.setLicenseNumber("123-ABC");

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        Resident resident = new Resident();
        resident.setVehicles(vehicles);

        VehicleDto vehicleDto = VehicleDto.builder()
            .make("Updated")
            .model("Model")
            .year(2020)
            .color("Silver")
            .licenseNumber("XXX-999")
            .build();

        UpdateResidentDto updateResidentDto = UpdateResidentDto.builder()
            .vehicles(Collections.singletonList(vehicleDto))
            .build();

        // Act
        residentMapper.update(updateResidentDto, resident);

        // Assert
        assertNotNull(resident);
        assertEquals(1, resident.getVehicles().size());
        assertEquals(vehicleDto.getMake(), resident.getVehicles().get(0).getMake());
        assertEquals(vehicleDto.getModel(), resident.getVehicles().get(0).getModel());
        assertEquals(vehicleDto.getYear(), resident.getVehicles().get(0).getYear());
        assertEquals(vehicleDto.getColor(), resident.getVehicles().get(0).getColor());
        assertEquals(vehicleDto.getLicenseNumber(), resident.getVehicles().get(0).getLicenseNumber());
    }

    @Test
    public void toCoResidentModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        CoResident result = residentMapper.toCoResidentModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toCoResidentModel_WhenGivenASaveCoResidentInfoDto_ShouldMap() {
        // Arrange
        SaveCoResidentInfoDto saveCoResidentInfo = SaveCoResidentInfoDto.builder()
            .preferredName("Preferred Name")
            .fullName("Simple Test")
            .email("simpletest@email.com")
            .phone("1234567890")
            .build();

        // Act
        CoResident result = residentMapper.toCoResidentModel(saveCoResidentInfo);

        // Assert
        assertNotNull(result);
        assertEquals(saveCoResidentInfo.getPreferredName(), result.getPreferredName());
        assertEquals(saveCoResidentInfo.getFullName(), result.getFullName());
        assertEquals(saveCoResidentInfo.getEmail(), result.getEmail());
        assertEquals(saveCoResidentInfo.getPhone(), result.getPhone());
    }
}
