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

public class addcart {

    WebDriver driver;

    String baseurl="https://www.saucedemo.com/";


    @Given("user login pada saucedemo")
    public void userLoginPadaSaucedemo() { Loginfeature("standard_user","secret_sauce");
    }

    private void Loginfeature(String username, String password) {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseurl);

        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @When("user melihat katalog produk")
    public void userMelihatKatalogProduk() {
        String header_title = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(header_title, "Products");
    }

    @And("user menambahkan produk ke keranjang")
    public void userMenambahkanProdukKeKeranjang() {
        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
    }

    @Then("produk terlihat di keranjang")
    public void produkTerlihatDiKeranjang() {
        String yourCart = driver.findElement(By.xpath("//span[@class='title']")).getText();
        System.out.println(yourCart);
        Assert.assertEquals(yourCart, "Your Cart");
        driver.close();
    }
}
