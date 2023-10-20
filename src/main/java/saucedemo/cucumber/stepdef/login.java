package saucedemo.cucumber.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseurl="https://www.saucedemo.com/";

    @Given("Halaman login saucedemo")
    public void halamanLoginSaucedemo() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseurl);
        String loginPageAssert = driver.findElement(By.xpath("//div[@class='login_logo']")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }

    @When("Input Username")
    public void inputUsername() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Input Password")
    public void inputPassword() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Click login button")
    public void clickLoginButton() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("User masuk ke halaman produk")
    public void userMasukKeHalamanProduk() {
        String halamanProduct = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        Assert.assertEquals(halamanProduct, "Swag Labs");
    }

    @When("Input Invalid Username")
    public void inputInvalidUsername() { driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        String ErrorLogin = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(ErrorLogin, "Epic sadface: Sorry, this user has been locked out.");
        driver.close();
    }
}
