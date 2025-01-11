package org.tugceyildirim.selenium.tests;

import org.openqa.selenium.TimeoutException;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tugceyildirim.selenium.consts.TestData;
import org.tugceyildirim.selenium.consts.Path;
import org.tugceyildirim.selenium.pages.OpenPositionsPage;
import org.tugceyildirim.selenium.pages.QualityAssurancePage;

import java.time.Duration;

public class QualityAssurancePageTest extends BaseTest {

    /**
     * Test Senaryosu: QA sayfasında iş filtreleme ve iş ilanı sayfası doğrulama
     * Adımlar:
     * 1. Careers/Quality Assurance sayfasını aç.
     * 2. "See all QA jobs" (Tüm QA işlerini gör) butonuna tıkla.
     * 3. Filtrelerden bir konum seç.
     * 4. Filtrelerden bir departman seç.
     * 5. Görüntülenen iş ilanlarının seçilen konum ve departmanla eşleştiğini doğrula.
     * 6. Her bir iş ilanı için:
     *      a. İlgili iş ilanı detay sayfasına git.
     *      b. İş ilanı detay sayfasının URL'sinin beklenen URL ile eşleştiğini doğrula.
     *      c. Doğrulama işleminden sonra orijinal sekmeye geri dön.
     * Beklenen Sonuçlar:
     * - İş ilanı listesi, seçilen filtrelere göre doğru şekilde güncellenmelidir.
     * - Her bir iş ilanı detay sayfasının URL'si, beklenen URL ile eşleşmelidir.
     */
    @Test
    public void shouldListFilteredJobsAndVerifyJobUrl() throws InterruptedException {
        driver.get(Path.QA_PAGE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        QualityAssurancePage qualityAssurancePage = new QualityAssurancePage(driver);
        qualityAssurancePage.acceptAllCookies();
        qualityAssurancePage.clickSeeAllJobsBtn();

        OpenPositionsPage openPositionsPage = new OpenPositionsPage(driver);

        // Lokasyon seçeneklerinin yüklenmesini bekle
        wait.until((WebDriver d) -> openPositionsPage.isLocationOptionsLoaded());

        // İş listesini ekrana kaydır
        openPositionsPage.scrollToJobList();
        //wait.until(ExpectedConditions.visibilityOf(openPositionsPage.jobList));

        Thread.sleep(500);

        // Başlangıçtaki iş ilanı sayısını al
        int actualJobSize = openPositionsPage.getJobSize();

        // Lokasyon ve departman filtrelerini seç
        openPositionsPage.selectLocationByName(TestData.LOCATION);
        Thread.sleep(1000);
        openPositionsPage.selectDepartmentByName(TestData.DEPARTMENT);

        try {
            //Filtreleme sonrası iş ilanı sayısında değişiklik olup olmadığını kontrol et
            wait.until((WebDriver d) -> openPositionsPage.getJobSize() != actualJobSize);
        } catch (TimeoutException ignored) {
        }

        openPositionsPage.scrollToJobList();
        //wait.until(ExpectedConditions.visibilityOfAllElements(openPositionsPage.jobs));

        Thread.sleep(1000);
        String originalTab = driver.getWindowHandle();

        // Her bir iş ilanının içeriğini kontrol et
        openPositionsPage.jobs.forEach(job -> {
            assertThat(openPositionsPage.getLocationLabelText(job))
                    .isEqualTo(TestData.LOCATION);
            assertThat(openPositionsPage.getDepartmentLabelText(job))
                    .isEqualTo(TestData.DEPARTMENT);

            String jobUrl = openPositionsPage.getViewRoleBtnHref(job);
            openPositionsPage.clickViewRoleBtn(job);

            // Açılan yeni pencereyi bul
            String newTab = driver.getWindowHandles()
                    .stream()
                    .filter(handle -> !handle.equals(driver.getWindowHandle()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Tab is not found"));
            driver.switchTo().window(newTab);

            assertThat(driver.getCurrentUrl()).isEqualTo(jobUrl);

            driver.close();
            driver.switchTo().window(originalTab);
        });
    }
}
