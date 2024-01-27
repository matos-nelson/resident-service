package org.rent.circle.resident.api.persistence.model;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PrimaryResidentTest {

    @Test
    public void PrimaryResident_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(PrimaryResident.class);

        // Assert
    }
}
