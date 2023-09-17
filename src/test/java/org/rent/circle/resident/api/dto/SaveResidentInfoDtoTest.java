package org.rent.circle.resident.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SaveResidentInfoDtoTest {

    @Test
    public void SaveResidentInfoDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(SaveResidentInfoDto.class);

        // Assert

    }
}
