package com.company;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;

import java.util.EmptyStackException;


public class Test_SeleniumTest {
    @Test
    public void LoginSuccess() throws InterruptedException
    {
        String home = System.getProperty(("user.home"));

        System.setProperty("webdriver.chrome.driver", "/Users/lesterpadul/Desktop/chromedriver");
        WebDriver webD = new ChromeDriver();
        webD.get("https://english.fdc-inc.com/");


        WebElement txtSearch;
        WebElement LoginBtn;
        LoginBtn = webD.findElement(By.xpath("//a[contains(@href, '/login')]"));
        LoginBtn.click();
        txtSearch = webD.findElement(By.id("UserEmail"));
        txtSearch.sendKeys("jengo@gmail.com");
        txtSearch = webD.findElement(By.id("UserPassword"));
        txtSearch.sendKeys("admin123");
        txtSearch = webD.findElement(By.xpath("//button[@type='submit']"));
        txtSearch.submit();

        try
        {
            Thread.sleep(10000);
        }catch (InterruptedException e){}


        if(webD.findElement(By.xpath("//button[@type='submit']")).isDisplayed())
        {
            webD.quit();
            Assert.fail("Failed to Login after 10 seconds");
        }

        webD.get("https://english.fdc-inc.com/logout");
        webD.quit();

    }

    @Test
    public void LoginNoInput()
    {
        String home = System.getProperty(("user.home"));

        System.setProperty("webdriver.chrome.driver", "/Users/lesterpadul/Desktop/chromedriver");
        WebDriver webD = new ChromeDriver();
        webD.get("https://english.fdc-inc.com/");


        WebElement txtSearch;
        WebElement LoginBtn;
        LoginBtn = webD.findElement(By.xpath("//a[contains(@href, '/login')]"));
        LoginBtn.click();
        txtSearch = webD.findElement(By.id("UserEmail"));
        txtSearch.sendKeys("");
        txtSearch = webD.findElement(By.id("UserPassword"));
        txtSearch.sendKeys("");
        txtSearch = webD.findElement(By.xpath("//button[@type='submit']"));
        txtSearch.submit();

        if(webD.findElement(By.xpath("//div[@id='authMessage']")).isDisplayed())
        {
            webD.quit();
            Assert.fail("No Input Values for UserEmail and Password.");
        }



    }

    @Test
    public void LoginIncorrectPassword()
    {
        String home = System.getProperty(("user.home"));

        System.setProperty("webdriver.chrome.driver", "/Users/lesterpadul/Desktop/chromedriver");
        WebDriver webD = new ChromeDriver();
        webD.get("https://english.fdc-inc.com/");


        WebElement txtSearch;
        WebElement LoginBtn;
        LoginBtn = webD.findElement(By.xpath("//a[contains(@href, '/login')]"));
        LoginBtn.click();
        txtSearch = webD.findElement(By.id("UserEmail"));
        txtSearch.sendKeys("jengo@gmail.com");
        txtSearch = webD.findElement(By.id("UserPassword"));
        txtSearch.sendKeys("test");
        txtSearch = webD.findElement(By.xpath("//button[@type='submit']"));
        txtSearch.submit();

        if(webD.findElement(By.xpath("//div[@id='authMessage']")).isDisplayed()) {
            webD.quit();
            System.out.println(webD.findElement(By.xpath("//div[@id='authMessage']")).getText());
            Assert.fail("No Input Values for UserEmail and Password");
        }



    }

}