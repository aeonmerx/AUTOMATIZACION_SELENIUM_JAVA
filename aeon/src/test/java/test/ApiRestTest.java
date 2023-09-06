package test;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import mapObjects.MapPageObjects;
public class ApiRestTest {

    @Test
    public void testFetchUserInfoFromAPI() {
        try {
            // Definir la URL del endpoint
            String apiUrl = "https://aeonmerx.com/api/users";

            // Realizar la solicitud GET al endpoint
            RequestSpecification request = RestAssured.given();
            Response response = request.get(apiUrl);

            // Verificar el código de respuesta
            int statusCode = response.getStatusCode();
            if (statusCode == 200) {
                // Obtener el contenido de la respuesta en formato JSON
                String jsonResponse = response.getBody().asString();
                System.out.println("Response JSON:\n" + jsonResponse);
                
                // Aquí puedes analizar el JSON y realizar las operaciones necesarias
                // Puedes utilizar bibliotecas como Jackson o Gson para analizar el JSON
            } else {
                System.out.println("La solicitud no se completó correctamente. Código de respuesta: " + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetUserById() {
        int userId = 3; // Reemplaza con un ID de usuario válido
        given()
            .pathParam("id", userId)
        .when()
            .get(MapPageObjects.USERS_ENDPOINT + "/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(userId))
            .log().ifValidationFails(); // Mostrar log si la validación tiene éxito
    }

    @Test
    public void testCreateUser() {
        String newUserJson = "{\"name\":\"Mellanie Cortes\",\"email\":\"mellanie@example.com\"}";

        Response response = given()
            .contentType("application/json")
            .body(newUserJson)
        .when()
            .post(MapPageObjects.USERS_ENDPOINT)
        .then()
            .statusCode(201)
            .log().ifValidationFails() // Mostrar log si la validación tiene éxito
            .extract().response();

        String userId = response.jsonPath().getString("id");
        System.out.println("Usuario creado exitosamente con ID: " + userId);
    }

    @Test
    public void testUpdateUser() {
        int userId = 5; // Reemplaza con un ID de usuario válido
        String updatedUserJson = "{\"name\":\"Updated User\"}";

        given()
            .contentType("application/json")
            .pathParam("id", userId)
            .body(updatedUserJson)
        .when()
            .put(MapPageObjects.USERS_ENDPOINT + "/{id}")
        .then()
            .statusCode(200)
            .log().ifValidationFails() // Mostrar log si la validación tiene éxito
            .body("name", equalTo("Updated User"));

        System.out.println("Usuario actualizado exitosamente con ID: " + userId);
    }

    @Test
    public void testDeleteUser() {
        int userId = 6; // Reemplaza con un ID de usuario válido

        given()
            .pathParam("id", userId)
        .when()
            .delete(MapPageObjects.USERS_ENDPOINT + "/{id}")
        .then()
            .statusCode(200)
            .log().ifValidationFails(); // Mostrar log si la validación tiene éxito

        System.out.println("Usuario eliminado exitosamente con ID: " + userId);
    }
}
