package com.consumercredits.dod;

import com.consumercredits.commonsteps.commonSteps;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import com.consumercredits.pagesdod.AccountSearchPage;

public class SearchPageLangBOReachTest extends commonSteps {

    @Test
    public void languageChangeCheck_nl() {
        test = extent.createTest("Verify that the Dutch text(s) in the page are correct");
        AccountSearchPage.language_button_nl(driver).click();
        wait.until(ExpectedConditions.and(
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_naam(driver),"Naam"),
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_rekeningnummer(driver),"Rekeningnummer"),
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_aaccode(driver),"AAC@Code"),
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_materaid(driver),"Matera ID"),
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_t24id(driver),"T24 Id")
        ));
        Assert.assertEquals("Rekeningnummer", AccountSearchPage.text_rekeningnummer(driver).getText());

    }

    @Test (priority = 1)
    public void languageChangeCheck_en() {
        test = extent.createTest("Verify that the English text(s) in the page are correct");
        AccountSearchPage.language_button_en(driver).click();
        wait.until(ExpectedConditions.and(
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_name(driver),"Name"),
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_accountnumber(driver),"Account Number"),
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_aaccode(driver),"AAC@Code"),
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_materaid(driver),"Matera ID"),
                ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_t24id(driver),"T24 Id")
        ));
        Assert.assertEquals("Account Number", AccountSearchPage.text_accountnumber(driver).getText());
    }

    @Test (priority = 2)
    public void boReachCheck() {
        test = extent.createTest("Verify that the account outside the BO reach should not be accessible");
        AccountSearchPage.account_id_search(driver).sendKeys("0496254847");
        AccountSearchPage.account_id_search(driver).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.textToBe(By.tagName("h3"),"You are not allowed to use this functionality"),
                ExpectedConditions.textToBe(By.tagName("h3"),"You are not allowed to use this functionality")
        ));
        String testResponse = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals("You are not allowed to use this functionality", testResponse);
    }
}
