package org.rent.circle.resident.api.persistence.model;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CoResidentTest {

    @Test
    public void CoResidentTest_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(CoResident.class);

        // Assert
    }
}
