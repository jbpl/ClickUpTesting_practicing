package pl.jb.requests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.jb.properties.ClickUpProperties;
import pl.jb.url.ClickUpUrl;

public class BaseRequest {

    private static RequestSpecBuilder requestBuilder;

    public static RequestSpecification requestSpec() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(ClickUpUrl.getBaseUrl());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addHeader("Authorization", ClickUpProperties.getToken());
        requestBuilder.addFilter(new AllureRestAssured());

        return requestBuilder.build();
    }
    public static RequestSpecification requestSpecWithLogs() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(ClickUpUrl.getBaseUrl());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addHeader("Authorization", ClickUpProperties.getToken());
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());
        requestBuilder.addFilter(new AllureRestAssured());

        return requestBuilder.build();
    }
}
