package org.rent.circle.resident.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import java.util.Collections;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.rent.circle.resident.api.dto.SaveResidentInfoDto;
import org.rent.circle.resident.api.dto.VehicleDto;

@QuarkusTest
@TestHTTPEndpoint(ResidentResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ResidentResourceTest {

    @Test
    public void Post_WhenGivenAValidRequestToSave_ShouldReturnSavedResidentId() {
        // Arrange
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenceNumber("123-ABC")
            .build();
        SaveResidentInfoDto saveResidentInfoDto = SaveResidentInfoDto.builder()
            .addressId(1L)
            .preferredName("Preferred Name")
            .fullName("Simple Test")
            .email("simpletest@email.com")
            .phone("1234567890")
            .vehicles(Collections.singletonList(vehicle))
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
            .addressId(1L)
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
    public void GET_WhenResidentCantBeFound_ShouldReturnNoContent() {
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
    public void GET_WhenResidentIsFound_ShouldReturnResident() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("addressId", is(1),
                "fullName", is("First Resident"),
                "email", is("firstresident@email.com"),
                "phone", is("1234445555"),
                "vehicles", is(Matchers.hasSize(1)),
                "vehicles[0].make", is("Nissan"),
                "vehicles[0].model", is("Rogue"),
                "vehicles[0].year", is(2000),
                "vehicles[0].color", is("Blue"),
                "vehicles[0].licenceNumber", is("AAA-123"));
    }
}
