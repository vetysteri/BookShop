package wad.controller;


import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OnlyForTheSelectedApplicationTest extends FluentTest {

    public WebDriver webDriver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @Before
    public void setup() {
        webDriver.manage().deleteAllCookies();
    }

    @LocalServerPort
    private Integer port;

    @Test
    public void anyoneCanSeeHappypath() throws Throwable {
        goTo("http://localhost:" + port + "/happypath");
        assertThat(pageSource()).contains("Happy!");
    }

    @Test
    public void userCanSeeSecretAfterLogin() throws Throwable {
        goTo("http://localhost:" + port + "/secretpath");
        assertThat(pageSource()).doesNotContain("Secret!");
        enterDetailsAndSubmit("larry", "larry");
        assertThat(pageSource()).contains("Secret!");
    }

    @Test
    public void userCannotSeeAdminPathEvenAfterLogin() throws Throwable {
        goTo("http://localhost:" + port + "/adminpath");
        assertThat(pageSource()).doesNotContain("Admin!");
        enterDetailsAndSubmit("larry", "larry");
        assertThat(pageSource()).doesNotContain("Admin!");
    }

    @Test
    public void adminCanSeeAdminPathAfterLogin() throws Throwable {
        goTo("http://localhost:" + port + "/adminpath");
        assertThat(pageSource()).doesNotContain("Admin!");
        enterDetailsAndSubmit("curly", "curly");
        assertThat(pageSource()).contains("Admin!");
    }

    private void enterDetailsAndSubmit(String username, String password) {
        fill(find(By.name("username"))).with(username);
        fill(find(By.name("password"))).with(password);
        find(By.name("password")).submit();
    }
}
