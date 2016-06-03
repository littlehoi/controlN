package com.pru.hk.aes;
/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumUtil {


  public static WebDriver createFireFoxWebDriver() {
    WebDriver driver = new FirefoxDriver();
    return driver;
  }

  public static void quitDriver(WebDriver driver) {
    driver.quit();
  }
}
