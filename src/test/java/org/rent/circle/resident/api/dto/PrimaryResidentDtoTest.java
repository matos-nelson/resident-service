package org.rent.circle.resident.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PrimaryResidentDtoTest {

    @Test
    public void PrimaryResidentDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(PrimaryResidentDto.class);

        // Assert

    }
}
