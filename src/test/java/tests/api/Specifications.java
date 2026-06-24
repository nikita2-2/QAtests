package tests.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Specifications {
    public static String apiUrl;
    public static String apiUser;
    private static String apiPassword;

    static {
        Properties properties = new Properties();
        try (InputStream input = Specifications.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл config.properties не найден в ресурсах!");
            }
            properties.load(input);

            apiUrl = properties.getProperty("api.url");
            apiUser = properties.getProperty("api.user");
            apiPassword = properties.getProperty("api.password");

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось прочитать конфигурацию API!");
        }
    }


    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(apiUrl)
                .setAuth(io.restassured.RestAssured.basic(apiUser, apiPassword))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .build();
    }

    public static void installSpecifications(RequestSpecification request, ResponseSpecification response) {
        io.restassured.RestAssured.requestSpecification = request;
        io.restassured.RestAssured.responseSpecification = response;
    }
}
