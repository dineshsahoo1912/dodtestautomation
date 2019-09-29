package com.consumercredits.pagesdod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TriggerViewSearchPage {

    public static WebElement element;

    public static WebElement trigger_search(WebDriver driver, String trigger_name) {
        String triggerXpath = ".//span[@class = 'ng-binding ng-scope' and contains(text(),'" + trigger_name + "')]";
        element = driver.findElement(By.xpath(triggerXpath));
        return element;
    }

    public static WebElement save_button(WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and contains(text(),'Save')]"));
        return element;
    }

    public static WebElement related_accounts_search(WebDriver driver,String account_number) {
        String related_acc_xpath = ".//span[@class = 'col-xs-11 ng-binding' and not(contains(text(),'" + account_number + "'))]";
        element = driver.findElement(By.xpath(related_acc_xpath));
        return element;
    }

    public static WebElement saved_successfully(WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(text(),'Saved')]"));
        return element;
    }
}
