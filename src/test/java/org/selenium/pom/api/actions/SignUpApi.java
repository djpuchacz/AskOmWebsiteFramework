package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.constants.EndPoint;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }
    public String fetchRegisterNonceValueUsingGroovy() { //151
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce' }.@value");
    }
    public String fetchRegisterNonceValueUsingJsoup() { //152
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");
    }
    public Response getAccount() {
        Cookies cookies = new Cookies();
        Response response = given().
                baseUri(ConfigLoader.getInstance().getBaseUrl()).
                cookies(cookies).
                log().all().
        when().
                get(EndPoint.ACCOUNT.url).
        then().
                log().all().
                extract().
                response();
        if (response.getStatusCode() != 200){
            throw
                    new RuntimeException("Failed to fetch the account, HTTP Status Code: " + response.getStatusCode());
        }
        return response;
    }
    public Response register(User user) { //153
        Cookies cookies = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("username", user.getUsername());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJsoup());
        formParams.put("register", "Register");

        Response response = given().
                baseUri(ConfigLoader.getInstance().getBaseUrl()).
                headers(headers).
                formParams(formParams).//155
                cookies(cookies).
                log().all().
                when().
                post(EndPoint.ACCOUNT.url).
                then().
                log().all().
                extract().
                response();
        if (response.getStatusCode() != 302){
            throw
                    new RuntimeException("Failed to register the account, HTTP Status Code: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
