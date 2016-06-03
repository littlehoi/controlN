package com.pru.hk.aes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExampleForFireFox  {
    public static void main(String[] args) {
    	// ???? FireFox ????????????????????
    	System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
    	// ???? FireFox ??????
    	
        WebDriver driver = new FirefoxDriver();

        driver.get("http://www.baidu.com");
        // driver.navigate().to("http://www.baidu.com");

        // ?? ??? title
        System.out.println("1 Page title is: " + driver.getTitle());

        // ?? id ?? input ? DOM
        WebElement element = driver.findElement(By.id("kw"));

        // ?????
        element.sendKeys("tes?");

        // ?? input ???  form
        element.submit();
        
        // ???? title ???????????????10?
        new WebDriverWait(driver,10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().endsWith("test");
            }
        });

        // ????????? title
        System.out.println("2 Page title is: " + driver.getTitle());
        
        //?????
        driver.quit();
    }
}