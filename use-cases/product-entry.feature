@tag
  Feature: Product-entry
    product entry must be correct and accurate

  @tag1
  Scenario: successful product-entry
    Given i filled product details
    When i fill a valid data
    Then i should take an invoice
