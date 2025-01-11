package org.tugceyildirim.selenium.tests;


import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;

import org.tugceyildirim.selenium.consts.Path;
import org.tugceyildirim.selenium.consts.TestData;
import org.tugceyildirim.selenium.pages.CareerPage;

public class CareerPageTest extends BaseTest {

    /**
     * Test Senaryosu: Career (Kariyer) sayfasındaki işlevsellik ve ana bölümlerin doğrulanması
     * Adımlar:
     * 1. Ana sayfayı aç.
     * 2. "Company" (Şirket) menüsüne tıkla.
     * 3. Açılır menüden "Careers" (Kariyer) sekmesini seç.
     * 4. Career sayfasının başlığının beklenen başlıkla eşleştiğini doğrula.
     * 5. Career sayfasında aşağıdaki bölümlerin görünürlüğünü kontrol et:
     *      a. "Locations" (Lokasyonlar) bloğu
     *      b. "Teams" (Ekipler) bloğu
     *      c. "Life at Insider" (Insider'da Hayat) bloğu
     * Beklenen Sonuçlar:
     * - Career sayfası başarıyla açılmalıdır.
     * - Career sayfasının başlığı beklenen başlıkla eşleşmelidir.
     * - "Locations", "Teams" ve "Life at Insider" bölümleri Career sayfasında görünür olmalıdır.
     */
    @Test
    public void shouldOpenAndVerifyBlocks() {
        driver.get(Path.MAIN_PAGE_URL);
        CareerPage careerPage = new CareerPage(driver);

        careerPage.acceptAllCookies();
        careerPage.clickCareerLink();

        assertThat(careerPage.getPageTitle())
                .contains(TestData.CAREER_PAGE_TITLE);

        assertThat(careerPage.ourLocationSection.isDisplayed())
                .isTrue();

        assertThat(careerPage.teamSection.isDisplayed())
                .isTrue();

        assertThat(careerPage.lifeAtInsiderSection.isDisplayed())
                .isTrue();
    }
}
