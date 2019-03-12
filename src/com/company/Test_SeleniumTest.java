package com.company;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class Test_SeleniumTest {
    private WebDriver webD = null;

    @Before
    public void Setup()
    {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver.exe");

        ChromeOptions option = new ChromeOptions();
        option.addArguments("incognito", "disable-infobars", "start-maximized");

        webD = new ChromeDriver(option);
        webD.manage().window().maximize();

    }

    @After
    public void Exit()
    {

        try
        {
           webD.quit();
        }
        catch(NullPointerException n) {}

    }

    @Test
    public void LoginSuccess() throws InterruptedException
    {

        webD.get("https://english.fdc-inc.com/");

        WebElement txtSearch;

        WebElement LoginBtn = webD.findElement(By.xpath("//a[contains(@href, '/login')]"));
        LoginBtn.click();

        txtSearch = webD.findElement(By.id("UserEmail"));
        txtSearch.sendKeys("jengo@gmail.com");

        txtSearch = webD.findElement(By.id("UserPassword"));
        txtSearch.sendKeys("admin123");

        txtSearch = webD.findElement(By.xpath("//button[@type='submit']"));
        txtSearch.click();

        try
        {
            Thread.sleep(10000);
        }catch (InterruptedException e){}

        String currentUrl = webD.getCurrentUrl();
        System.out.print("URL After hitting login button: " + currentUrl);
        if(currentUrl.contains("login"))
        {
            Assert.fail("Failed to Login after 10 seconds, Please check your login credential");
        }

        webD.get("https://english.fdc-inc.com/logout");

    }

    @Test
    public void LoginNoInput()
    {

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
        txtSearch.click();

        try
        {
            Thread.sleep(10000);
        }catch (InterruptedException e){}

        String currentUrl = webD.getCurrentUrl();
        System.out.print("URL After hitting login button: " + currentUrl);

        if(currentUrl.contains("login"))
        {
            Assert.fail("Failed to Login after 10 seconds, Please check your login credential");
        }

    }

    @Test
    public void LoginIncorrectPassword()
    {

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
        txtSearch.click();

        String currentUrl = webD.getCurrentUrl();
        System.out.print("URL After hitting login button: " + currentUrl);

        if(currentUrl.contains("login"))
        {
            Assert.fail("Failed to Login after 10 seconds, Please check your login credential");
        }

    }

}