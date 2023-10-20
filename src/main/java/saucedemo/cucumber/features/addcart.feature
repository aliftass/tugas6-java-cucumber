Feature: Menambahkan barang dalam keranjang saucedemo

  @Regression @Positive
  Scenario: menambahkan barang dalam keranjang
    Given user login pada saucedemo
    When user melihat katalog produk
    And user menambahkan produk ke keranjang
    Then produk terlihat di keranjang