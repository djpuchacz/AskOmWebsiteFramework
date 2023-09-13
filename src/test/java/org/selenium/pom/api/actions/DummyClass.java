package org.selenium.pom.api.actions;

import com.github.javafaker.Faker;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {
    public static void main(String[] args) {
        //System.out.println(new SignUpApi().fetchRegisterNonceValueUsingGroovy()); //152
        //System.out.println(new SignUpApi().fetchRegisterNonceValueUsingJsoup());

        String userName = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(userName).
                setPassword("demopwd").
                setEmail(userName + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        System.out.println(signUpApi.register(user));
        System.out.println(signUpApi.getCookies());


    }
}
