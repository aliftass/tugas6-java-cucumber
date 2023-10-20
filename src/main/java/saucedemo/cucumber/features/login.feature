Feature: Login Aplikasi saucedemo

  @Regression @Positive
  Scenario: Success Login
    Given Halaman login saucedemo
    When Input Username
    And Input Password
    And Click login button
    Then User masuk ke halaman produk

  @Regression @Negative
  Scenario: Failed Login
    Given Halaman login saucedemo
    When Input Invalid Username
    And Input Password
    And Click login button
    Then User get error message