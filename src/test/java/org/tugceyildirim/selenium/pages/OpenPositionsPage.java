package org.tugceyildirim.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OpenPositionsPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[@id='jobs-list']")
    public WebElement jobList;

    @FindBy(xpath = "//div[@id='jobs-list']/div")
    public List<WebElement> jobs;

    @FindBy(xpath = "//span[@id='select2-filter-by-location-container']")
    private WebElement locationDropdown;

    @FindBy(xpath = "//ul[@id='select2-filter-by-location-results']")
    private WebElement locationDropdownList;

    @FindBy(xpath = "//span[@id='select2-filter-by-department-container']")
    private WebElement departmentDropdown;

    @FindBy(xpath = "//ul[@id='select2-filter-by-department-results']")
    private WebElement departmentDropdownList;

    @FindBy(xpath = "//select[@id='filter-by-location']//option")
    private List<WebElement> locationOptions;

    public OpenPositionsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isLocationOptionsLoaded() {
        return this.locationOptions.size() > 1;
    }

    public void scrollToJobList() {
        new Actions(this.driver)
                .scrollToElement(jobList)
                .perform();
    }

    public int getJobSize() {
        return jobs.size();
    }

    public void selectLocationByName(String location) {
        locationDropdown.click();
        String xpath = String.format(".//li[text()='%s']", location);
        locationDropdownList.findElement(By.xpath(xpath)).click();
    }

    public void selectDepartmentByName(String department) {
        this.departmentDropdown.click();
        String xpath = String.format(".//li[text()='%s']", department);
        departmentDropdownList.findElement(By.xpath(xpath)).click();
    }

    public String getLocationLabelText(WebElement job) {
        return job.findElement(By.cssSelector(".position-location")).getText();
    }

    public String getDepartmentLabelText(WebElement job) {
        return job.findElement(By.cssSelector(".position-department")).getText();
    }

    public void clickViewRoleBtn(WebElement job) {
        job.findElement(By.cssSelector("a.btn")).click();
    }

    public String getViewRoleBtnHref(WebElement job) {
        return job.findElement(By.cssSelector("a.btn"))
                .getDomAttribute("href");
    }
}
