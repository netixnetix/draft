package api;
import config.ApiConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Courier;
import models.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import steps.CourierSteps;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.OrderStep;

import java.util.Date;
import java.util.stream.Stream;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static steps.FakerData.FirstName;
import static steps.FakerData.Pwd;
import static steps.FakerData.Login;
import testData.OrdersData.*;



public class CreateOrder {

    @BeforeEach
    public void setUp(){
        ApiConfig.setUp();
    }

    @ParameterizedTest
    @MethodSource("testData.OrdersData#provideData")
    public void fieldIsRequiredForCreateCourier(
            String firstName,
            String lastName,
            String address,
            String metroStation,
            String phone,
            Integer rentTime,
            String deliveryDate)

    {
        Response response = OrderStep.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate);
        response.then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(201)
                .and().body("track", instanceOf(Integer.class));
    }


}