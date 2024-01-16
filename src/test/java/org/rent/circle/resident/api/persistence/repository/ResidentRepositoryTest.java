package org.rent.circle.resident.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.resident.api.persistence.model.Resident;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ResidentRepositoryTest {

    @Inject
    ResidentRepository residentRepository;

    @Test
    @TestTransaction
    public void findResidentByEmail_WhenResidentDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Resident result = residentRepository.findByEmail("notfound@email.com");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByEmail_WhenResidentDoesExist_ShouldReturnResident() {
        // Arrange

        // Act
        Resident result = residentRepository.findByEmail("firstresident@email.com");

        // Assert
        assertNotNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByUserId_WhenResidentDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Resident result = residentRepository.findByUserId("invalid_user");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByUserId_WhenResidentDoesExist_ShouldReturnResident() {
        // Arrange

        // Act
        Resident result = residentRepository.findByUserId("auth_user");

        // Assert
        assertNotNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByIdAndManagerId_WhenResidentDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Resident result = residentRepository.findByIdAndManagerId(100L, "invalid_user");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByIdAndManagerId_WhenResidentDoesExist_ShouldReturnResident() {
        // Arrange

        // Act
        Resident result = residentRepository.findByIdAndManagerId(100L, "auth_user");

        // Assert
        assertNotNull(result);
    }
}
