package tests.day15_testNG_framework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

public class C02_dependsOnMethods {

    @Test
    public void amazonTest(){
        Driver.getDriver().get("https://www.amazon.com");
        String expectedIcerik = "amazon";
        String actualIcerik  = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualIcerik.contains(expectedIcerik));

    }

    @Test(dependsOnMethods = "amazonTest")
    public void nutellaTest(){
        WebElement aramaKutusu=  Driver.getDriver().findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("Nutella" + Keys.ENTER);

        WebElement sonucYaziElementi = Driver.getDriver().findElement(By.xpath("//h1[@class='a-size-base s-desktop-toolbar a-text-normal']"));

        String expectedIcerik = "Nutella";
        String actualSonucYazisi = sonucYaziElementi.getText();

        Assert.assertTrue(actualSonucYazisi.contains(expectedIcerik));
    }

    @Test(priority = -1,dependsOnMethods = "nutellaTest")
    public void ilkUrunTest(){
        Driver.getDriver().findElement(By.xpath("(//*[@class='a-section aok-relative s-image-square-aspect'])[1]"))
                .click();

        WebElement urunIsimElementi = Driver.getDriver().findElement(By.xpath("//span[@id='productTitle']"));

        String expectedIcerik = "Nutella";
        String actualUrunIsmi = urunIsimElementi.getText();

        Assert.assertTrue(actualUrunIsmi.contains(expectedIcerik));

        Driver.closeDriver();
    }


}
