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
public class PathTest extends FluentTest {

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
    public void anyoneCanSeeBooks() throws Throwable {
        goTo("http://localhost:" + port + "/books");
        assertThat(pageSource()).contains("Books on Sale");
    }
    
    @Test
    public void anyoneCanSeeGenres() throws Throwable {
        goTo("http://localhost:" + port + "/genres");
        assertThat(pageSource()).contains("Available Genres");
    }
    
    @Test
    public void anyoneCanSeeAuthors() throws Throwable {
        goTo("http://localhost:" + port + "/authors");
        assertThat(pageSource()).contains("Available Authors");
    }

}
