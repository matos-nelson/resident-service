package org.rent.circle.resident.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.resident.api.annotation.AuthUser;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;

@QuarkusTest
@TestHTTPEndpoint(ResidentResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
@AuthUser
public class ResidentResourceTest {

    @Test
    public void Post_WhenGivenAValidRequestToSave_ShouldReturnSavedResidentId() {
        // Arrange
        SaveResidentInfoDto saveResidentInfoDto = SaveResidentInfoDto.builder()
            .propertyId(1L)
            .tenantId(2L)
            .userId("123")
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveResidentInfoDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }

    @Test
    public void Post_WhenGivenAnInValidRequestToSave_ShouldReturnBadRequest() {
        // Arrange
        SaveResidentInfoDto saveResidentInfoDto = SaveResidentInfoDto.builder()
            .propertyId(1L)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveResidentInfoDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void GET_WhenResidentCantBeFoundById_ShouldReturnNoContent() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/1")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GET_WhenResidentIsFoundById_ShouldReturnResident() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("id", is(100),
                "propertyId", is(1),
                "tenantId", is(200));
    }
}
