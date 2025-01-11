package org.tugceyildirim.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareerPage {
    private final WebDriver driver;

    @FindBy(xpath = "//a[@id='wt-cli-accept-all-btn']")
    private WebElement acceptAllCookiesBtn;

    @FindBy(xpath = "//a[@id='navbarDropdownMenuLink' and contains(text(),'Company')]")
    private WebElement companyDropdown;

    @FindBy(xpath = "//a[@class='dropdown-sub' and contains(text(),'Career')]")
    private WebElement careerLink;

    @FindBy(xpath = "//*[@id='career-our-location']")
    public WebElement ourLocationSection;

    @FindBy(xpath = "//*[@id='career-find-our-calling']")
    public WebElement teamSection;

    @FindBy(xpath = "//section[@data-id='a8e7b90']")
    public WebElement lifeAtInsiderSection;

    public CareerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getPageTitle(){
        return this.driver.getTitle();
    }

    public void acceptAllCookies() {
        acceptAllCookiesBtn.click();
    }

    public void clickCareerLink(){
        companyDropdown.click();
        careerLink.click();
    }
}
