package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {
    public static void main(String[] args) {
        //System.out.println(new SignUpApi().fetchRegisterNonceValueUsingGroovy()); //152
        //System.out.println(new SignUpApi().fetchRegisterNonceValueUsingJsoup());

        /*String userName = "demouser" + new FakerUtils().generateRandomNumber(); //157
        User user = new User().
                setUsername(userName).
                setPassword("demopwd").
                setEmail(userName + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        System.out.println(signUpApi.register(user));
        System.out.println(signUpApi.getCookies());*/

        /*CartApi cartApi = new CartApi(); // 157 user is not log in
        cartApi.addToCart(1215, 1);
        System.out.println(cartApi.getCookies());
*/
        String userName = "demouser" + new FakerUtils().generateRandomNumber(); //157 user is log in
        User user = new User().
                setUsername(userName).
                setPassword("demopwd").
                setEmail(userName + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        System.out.println("REGISTER COOKIES: " + signUpApi.getCookies());
        CartApi cartApi = new CartApi(signUpApi.getCookies()); // 157 user is log in
        cartApi.addToCart(1215, 5);
        System.out.println("CART COOKIES: " + cartApi.getCookies());
    }
}
