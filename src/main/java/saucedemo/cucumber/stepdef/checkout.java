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

public class checkout {

    WebDriver driver;
    String baseurl="https://www.saucedemo.com/";

    @Given("Terdapat produk yang berada dikeranjang")
    public void terdapatProdukYangBeradaDikeranjang() {

        loginFeature("standard_user","secret_sauce");

        String header_title = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(header_title, "Products");

        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-bike-light']")).click();
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
    }

    private void loginFeature(String username, String password) {
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

    @When("Proses untuk checkout")
    public void prosesUntukCheckout() {
        driver.findElement(By.xpath("//*[@id='checkout']")).click();

        driver.findElement(By.id("first-name")).sendKeys("Alifta");
        driver.findElement(By.id("last-name")).sendKeys("Shafira");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        driver.findElement(By.xpath("//*[@id='continue']")).click();
    }

    @And("Memberikan informasi pengiriman dan pembayaran")
    public void memberikanInformasiPengirimanDanPembayaran() {
        String payment_information = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[1]")).getText();
        String shipping_information = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[3]")).getText();
        String price_total = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[5]")).getText();

        Assert.assertEquals(payment_information, "Payment Information");
        Assert.assertEquals(shipping_information, "Shipping Information");
        Assert.assertEquals(price_total, "Price Total");

        driver.findElement(By.xpath("//*[@id='finish']")).click();
    }

    @Then("Checkout berhasil")
    public void checkoutBerhasil() {
        String header_text = driver.findElement(By.xpath("//*[@id='checkout_complete_container']/h2")).getText();
        Assert.assertEquals(header_text, "Thank you for your order!");
        driver.close();
    }
}
