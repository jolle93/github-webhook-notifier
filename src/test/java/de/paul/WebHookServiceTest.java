package de.paul;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class WebHookServiceTest {

    @Test
    public void testGetWebHookNotification() {
        given()
          .when().get("/notify")
          .then()
             .statusCode(200);
    }

}
