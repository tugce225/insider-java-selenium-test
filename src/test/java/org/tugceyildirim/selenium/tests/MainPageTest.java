package org.tugceyildirim.selenium.tests;

import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;

import org.tugceyildirim.selenium.consts.Path;
import org.tugceyildirim.selenium.consts.TestData;
import org.tugceyildirim.selenium.pages.MainPage;

public class MainPageTest extends BaseTest {

    /**
     * Test Senaryosu: Ana sayfanın başarıyla açıldığını doğrulama
     * Adımlar:
     * 1. Ana sayfa URL'sini aç.
     * 2. Ana sayfanın başlığını kontrol et.
     * Beklenen Sonuçlar:
     * - Ana sayfa başarıyla açılmalıdır.
     * - Ana sayfanın başlığı beklenen başlık ile eşleşmelidir.
     */
    @Test
    public void shouldOpenSuccessfully() {
        driver.get(Path.MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);

        assertThat(mainPage.getPageTitle())
                .contains(TestData.MAIN_PAGE_TITLE);
    }
}
