package tests.day19_testNGreports;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class C01_raporluTest extends TestBaseRapor {

    @Test
    public void nutellaTesti(){
        extentTest = extentReports.createTest("amazon arama Testi","Kullanici amazonda nutella aratip ilk urune gidebilmeli");
        // amazon anasayfaya gidelim
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        extentTest.info("kullanici amazon anasa<yfaya gidier");


        // url'in amazon icerdigini test edelim
        String expectedIcerik = "amazon";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedIcerik));
        extentTest.pass("Url amazon icerdigini test eder");
        // aranacak kelimeyi aratalim

        AmazonPage amazonPage = new AmazonPage();
        amazonPage.aramaKutusu.sendKeys(ConfigReader.getProperty("amazonAranacakKelime")+ Keys.ENTER);
        extentTest.info("Test datasi olarak belirlenen kelimeyi aratir");

        // sonuclarin aranacak kelimeyi icerdigini test edelim

        String expectedSonucIcerik = ConfigReader.getProperty("amazonAranacakKelime");
        String actualSonucYazisi = amazonPage.sonucYaziElementi.getText();
        Assert.assertTrue(actualSonucYazisi.contains(expectedSonucIcerik));
        extentTest.pass("amazon sonuclarinin aranan kelimeyi icerdigini test eder");

        // ilk urune tiklayalim

        amazonPage.ilkUrunElementi.click();
        // ilk urun isminde aranacak kelime bulundugunu test edelim
        extentTest.info("ilk urune tiklar");

        String expectedUrunIcerik = ConfigReader.getProperty("amazonAranacakKelime2");
        String actualIsim = amazonPage.ilkUrunIsimElementi.getText();
        Assert.assertTrue(actualIsim.contains(expectedUrunIcerik));
        extentTest.pass("ilk urun isminde aranan kelime bulundugunu test eder");

        // sayfayi kapatalim

        Driver.closeDriver();
        extentTest.info("sayfayi kapatir.");
    }
}
