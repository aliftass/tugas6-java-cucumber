Feature: Keluar dari aplikasi saucedemo

  @Regression @Positive
  Scenario: Keluar dari aplikasi
    Given Login pada halaman saucedemo
    When Input Username dan password
    And Click tombol login
    And User masuk dihalaman produk
    And click sidebar
    And click menu logout
    Then user keluar halaman