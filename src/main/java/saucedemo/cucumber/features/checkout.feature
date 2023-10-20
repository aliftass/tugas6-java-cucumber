Feature: Checkout barang dari saucedemo

  @Regression @Positive
  Scenario: Checkout barang dari saucedemo
    Given Terdapat produk yang berada dikeranjang
    When Proses untuk checkout
    And Memberikan informasi pengiriman dan pembayaran
    Then Checkout berhasil