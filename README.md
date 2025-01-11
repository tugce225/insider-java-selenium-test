# Insider - Selenium, Java Test Automation
Insider `useinsider.com` web otomasyon testi projesidir. 
Java ile Maven, TestNG, Selenium kullanılarak geliştirilmiştir. 
Test senaryoları aşağıdaki şekildedir.

## Test Cases
* MainPageTest - Ana sayfanın başarıyla açıldığını doğrulama.
* CareerPageTest - Career (Kariyer) sayfasındaki işlevsellik ve ana bölümlerin doğrulanması.
* QualityAssurancePageTest - QA sayfasında iş filtreleme ve iş ilanı sayfasını doğrulama.

## How to Run
```bash
git clone git@github.com:tugce225/insider-java-selenium-test.git

cd insider-java-selenium-test
mvn clean install

mvn test
```

## Project Folder
```bash
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── org.tugceyildirim.selenium
│   │   │   │   ├── const # Test dataları ve sabitler
│   │   │   │   ├── pages # POM sınıfları 
│   │   │   │   ├── tests # Test sınıfları
│   ├── resources # TestNG konfigurasyonu vb.
```