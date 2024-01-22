package org.rent.circle.resident.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.resident.api.persistence.model.PrimaryResident;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PrimaryResidentRepositoryTest {

    @Inject
    PrimaryResidentRepository primaryResidentRepository;

    @Test
    @TestTransaction
    public void findResidentByEmail_WhenResidentDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        PrimaryResident result = primaryResidentRepository.findByEmail("notfound@email.com");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByEmail_WhenResidentDoesExist_ShouldReturnPrimaryResident() {
        // Arrange

        // Act
        PrimaryResident result = primaryResidentRepository.findByEmail("firstresident@email.com");

        // Assert
        assertNotNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByUserId_WhenResidentDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        PrimaryResident result = primaryResidentRepository.findByUserId("invalid_user");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByUserId_WhenResidentDoesExist_ShouldReturnPrimaryResident() {
        // Arrange

        // Act
        PrimaryResident result = primaryResidentRepository.findByUserId("auth_user");

        // Assert
        assertNotNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByIdAndManagerId_WhenResidentDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        PrimaryResident result = primaryResidentRepository.findByIdAndManagerId(100L, "invalid_user");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findResidentByIdAndManagerId_WhenResidentDoesExist_ShouldReturnPrimaryResident() {
        // Arrange

        // Act
        PrimaryResident result = primaryResidentRepository.findByIdAndManagerId(100L, "auth_user");

        // Assert
        assertNotNull(result);
    }
}
