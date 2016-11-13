package sh.jack.example;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Really basic step definitions for Cucumber testing.
 */
public class BasicStepDefinitions {

    private WebDriver driver;

    @Before
    public void setup(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Given("^I am on BBC News page$")
    public void visitBBC(){
        driver.get("http://www.bbc.co.uk/news");
    }

    @Then("the title should contain \"(.*)\"$")
    public void checkTitle(){
        //wait for DOM to be ready.
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        assertThat(driver.getTitle(), containsString("BBC News"));
    }

    @After
    public void tearDown(){
        driver.close();
    }

}
