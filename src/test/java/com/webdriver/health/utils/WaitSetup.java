package com.webdriver.health.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WaitSetup 
{
public void implicitWait(WebDriver driver,int timeout);
public void explicitWait(WebDriver driver,int timeout,WebElement element);
public void fluenttWait();
public void waitForElementToVisible(WebDriver driver, int timeout, WebElement element);
public void waitForElementToBeInvisible(WebDriver driver, int timeout, WebElement element);
public void waitForElementToBeInvisibleFastAndFluent(WebDriver driver, int timeout, WebElement element);
public void waitForElementTextToBeVisible(WebDriver driver,int timeout,WebElement element,String text);
}
