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

public class logout {

    WebDriver driver;
    String baseurl="https://www.saucedemo.com/";

    @Given("Login pada halaman saucedemo")
    public void loginPadaHalamanSaucedemo() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseurl);
        String loginPageAssert = driver.findElement(By.xpath("//div[@class='login_logo']")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }

    @When("Input Username dan password")
    public void inputUsernameDanPassword() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Click tombol login")
    public void clickTombolLogin() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @And("User masuk dihalaman produk")
    public void userMasukDihalamanProduk() {
        String halamanProduct = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        Assert.assertEquals(halamanProduct, "Swag Labs");
    }

    @And("click sidebar")
    public void clickSidebar() {
        driver.findElement(By.xpath("//div[@class='bm-burger-button']")).click();
    }

    @And("click menu logout")
    public void clickMenuLogout() {
        driver.findElement(By.xpath("//*[@id='logout_sidebar_link']")).click();
    }

    @Then("user keluar halaman")
    public void userKeluarHalaman() {
        driver.close();

    }
}
