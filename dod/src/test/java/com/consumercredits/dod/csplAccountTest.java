package com.consumercredits.dod;

import com.consumercredits.commonsteps.commonSteps;
import com.consumercredits.pagesdod.AccountSearchPage;
import com.consumercredits.pagesdod.TriggerViewSearchPage;
import com.consumercredits.test_data.testData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class csplAccountTest extends commonSteps {

    String accountNumber = testData.testAccountNumbers("CSPL");

    @Test
    public void searchAccountNumber() {
        test = extent.createTest("Verify that CSPL accounts are found in the CRIS database");
        AccountSearchPage.language_button_en(driver).click();
        wait.until(ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_name(driver), "Name"));
        AccountSearchPage.account_id_search(driver).sendKeys(accountNumber);
        AccountSearchPage.account_id_search(driver).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(),'Legal problems')]")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@class = 'ng-binding ng-scope' and contains(text(),'Veroordeling+')]"))
        ));
        Assert.assertEquals("Account Number", AccountSearchPage.text_accountnumber(driver).getText());
    }

    @Test(priority = 1)
    public void checkClientManualTrigger() {
        String fontWeight;
        test = extent.createTest("Verify that Client level Manual triggers are visible in bold");
        if (AccountSearchPage.language_button_en(driver).isEnabled()) {
            fontWeight = TriggerViewSearchPage.trigger_search(driver, "Legal problems").getCssValue("font-weight");
        } else {
            fontWeight = TriggerViewSearchPage.trigger_search(driver, "Veroordeling+").getCssValue("font-weight");
        }
        Assert.assertEquals("700", fontWeight);
    }

    @Test (priority = 2)
    public void checkAccountManualTrigger() {
        test = extent.createTest("Verify that Account level Manual triggers are visible in bold");
        TriggerViewSearchPage.related_accounts_search(driver,accountNumber).click();
        wait.until(ExpectedConditions.visibilityOf(TriggerViewSearchPage.trigger_search(driver,"Forbearance")));
        JavascriptExecutor scroll_driver = (JavascriptExecutor) driver;
        scroll_driver.executeScript("window.scrollBy(0,1000)");
        String fontWeight = TriggerViewSearchPage.trigger_search(driver, "Forbearance").getCssValue("font-weight");
        Assert.assertNotEquals("700",fontWeight);
    }

    @Test(priority = 3)
    public void saveTriggerFunctionality() throws Exception{
        test = extent.createTest("Verify that trigger saving functionality works as expected from the User Interface");
        TriggerViewSearchPage.trigger_search(driver,"Legal problems").click();

        WebElement element = driver.findElement(By.xpath(".//*[contains(text(),'Legal problems')]"));
        element.click();

        TriggerViewSearchPage.save_button(driver).click();
    }


}