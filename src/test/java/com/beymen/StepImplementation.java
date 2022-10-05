package com.beymen;

import com.thoughtworks.gauge.Step;
import driver.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.util.Lists.list;

public class StepImplementation extends BaseTest {
    JavascriptExecutor jsdriver = (JavascriptExecutor) driver;

    @Step("<seconds> saniye kadar bekle")
    public void waitElement(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
        System.out.println(seconds + " saniye bekle.");

    }


    @Step({"<locator>'li <element> elemente tikla"})
    public void click(String locator, String element) {

        switch (locator) {
            case "id":
                driver.findElement(By.id(element)).click();
                System.out.println("id-Click");
                break;
            case "cssSelector":
                driver.findElement(By.cssSelector(element)).click();
                System.out.println("cssSelector-Click");
                break;
            case "xpath":
                driver.findElement(By.xpath(element)).click();
                System.out.println("xpath-Click");
                break;

        }

    }

    @Step("<locator>'li <element> elemente JS ile tikla")
    public void jsClick(String locator, String element) {

        switch (locator) {
            case "id":
                jsdriver.executeScript("arguments[0].click();", driver.findElement(By.id(element)));
                System.out.println("id-jsClick");
                break;
            case "cssSelector":
                jsdriver.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(element)));
                System.out.println("cssSelector-jsClick");
                break;
            case "xpath":
                jsdriver.executeScript("arguments[0].click();", driver.findElement(By.xpath(element)));
                System.out.println("xpath-jsClick");
                break;

        }

    }


    @Step("<locater>'li <element> elemente <sendKeys> yolla")
    public void sendKeys(String locater, String element, String sendKeys) {
        switch (locater) {
            case "id":
                driver.findElement(By.id(element)).sendKeys(sendKeys);
                System.out.println("id-sendKeys");
                break;
            case "cssSelector":
                driver.findElement(By.cssSelector(element)).sendKeys(sendKeys);
                System.out.println("cssSelector-sendKeys");
                break;
            case "xpath":
                driver.findElement(By.xpath(element)).sendKeys(sendKeys);
                System.out.println("xpath-sendKeys");
                break;
        }

    }


    @Step("<locater> üzerinde bekle")
    public void mouseOver(String locater) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(locater))).build().perform();
    }

    @Step("<xpath> üzerinde beklenir")
    public void mouseHover(String xpath) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(xpath))).build().perform();
    }


    /*@Step("<id>'li <element>'e doğru aşağı git")
    public void scrollWithAction(String element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id(element))).build().perform();
    }*/
    @Step("<id>'li objeye doğru in")
    public void scrollWithAction(String id) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id(id))).build().perform();
    }


    @Step("<xpath>'e doğru aşağı git rica ediyorum")
    public void scroll(String xpath) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(xpath))).build().perform();
    }

    @Step("<second> saniye kadar dinamik bekle")
    public void dynamicWait(int second) {
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
    }


    @Step("<xpath>' get the xpath of lıst")
    public void onClickListItem(String listPath) {
        Actions action = new Actions(driver);
        List<WebElement> links = driver.findElements(By.xpath(listPath));
        System.out.println(links.size());

        links.get(1).click();
    }

    @Step("<locator>'li elemente tikla")
    public void chooseClick(String locator) throws InterruptedException {
        List<WebElement> items = driver.findElements(By.cssSelector(locator));
        items.get(3).click();
        Thread.sleep(3);
    }

    @Step("classname ile <elementt> elementli <urun>  ürüne tıkla")
    public void secondElementClick(String elementt, int urun) {
        List<WebElement> productElems = driver.findElements(By.className(elementt));
        System.out.println(productElems);
        productElems.get(urun).click();

    }
    // web element list sadece selenium kütüphanesinde var

//  id:login-buton  Elemente tıkla.


    @Step("<locater> ile <element> elementli <urun>  ürüne tıkla")
    public void secondElementClickSwitch(String locater, String element, int urun) {

        switch (locater) {
            case "id":
                List<WebElement> productElements1 = driver.findElements(By.id(element));
                System.out.println(productElements1);
                productElements1.get(urun).click();
                break;
            case "cssSelector":
                List<WebElement> productElements2 = driver.findElements(By.cssSelector(element));
                System.out.println(productElements2);
                productElements2.get(urun).click();
                break;
            case "xpath":
                List<WebElement> productElements3 = driver.findElements(By.xpath(element));
                System.out.println(productElements3);
                productElements3.get(urun).click();
                break;
            case "classname":
                List<WebElement> productElements4 = driver.findElements(By.className(element));
                System.out.println(productElements4);
                productElements4.get(urun).click();
                break;
        }
    }



    @Step({"<locater>'lı <element>'li ürünü random seç"})
    public void randomItemPick(String locater, String element){
        List<WebElement> elementList = driver.findElements(By.xpath(element));


        switch (locater) {
            case "id":
                List<WebElement> productElements1 = driver.findElements(By.id(element));
                System.out.println(productElements1);
                Random random = new Random();
                int randomItemPicked = random.nextInt(productElements1.size());
                productElements1.get(randomItemPicked).click();
                break;
            case "cssSelector":
                List<WebElement> productElements2 = driver.findElements(By.cssSelector(element));
                System.out.println(productElements2);
                Random random1 = new Random();
                int randomItemPicked1 = random1.nextInt(productElements2.size());
                productElements2.get(randomItemPicked1).click();
                break;
            case "xpath":
                List<WebElement> productElements3 = driver.findElements(By.xpath(element));
                System.out.println(productElements3);
                Random random2 = new Random();
                int randomItemPicked2 = random2.nextInt(productElements3.size());
                productElements3.get(randomItemPicked2).click();
                break;
            case "classname":
                List<WebElement> productElements4 = driver.findElements(By.className(element));
                System.out.println(productElements4);
                Random random3 = new Random();
                int randomItemPicked3 = random3.nextInt(productElements4.size());
                productElements4.get(randomItemPicked3).click();
                break;
        }

    }
}

