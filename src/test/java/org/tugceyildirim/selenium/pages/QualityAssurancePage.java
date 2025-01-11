package org.tugceyildirim.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QualityAssurancePage {
    @FindBy(xpath = "//a[contains(text(),'See all QA jobs')]")
    public WebElement seeAllJobsBtn;

    @FindBy(xpath = "//a[@id='wt-cli-accept-all-btn']")
    public WebElement acceptAllCookiesBtn;

    public QualityAssurancePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickSeeAllJobsBtn(){
        this.seeAllJobsBtn.click();
    }

    public void acceptAllCookies(){
        acceptAllCookiesBtn.click();
    }

}
