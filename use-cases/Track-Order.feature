@Product-feature
Feature: Product-entry
  product entry must be correct and accurate

  @Unsuccessful
  Scenario Outline: Unsuccessful Product-entry
  the worker should see an error message on the screen

    Given I filled the product details
    When I enter an invalid Phone number '<PhoneNumber>' format
    And I enter an invalid name '<name>' format
    And I enter an empty '<address>' Address
    And I enter an empty '<Area>' Area
    And I enter an empty '<Quantity>' Quantity
    And I enter an invalid Quantity '<Quantity>' format
    And I enter an invalid Area '<Area>' format
    Then I should show a warning message '<message>'
    Examples:
      | PhoneNumber | name   | address | Quantity | Area | message                           |
      | 975684126   | carpet | Nablus  | 1        | 300  | Please enter a valid Phone Number |
      | 97059251756 | carpet |         | 2        | 200  | Address can't be empty            |
      | 97059251711 | carpet | Jenin   |          | 30   | Quantity can't be empty           |
      | 97059251789 | 14     | Jenin   | 4        | 200  | Name can't contain digits         |
      | 97056251744 | carpet | Jenin   | 2        |      | Area can't be empty               |
      | 97056251755 | carpet | Jenin   | a        | 45   | Quantity must contain Digits only |
      | 97056251777 | carpet | Jenin   | 2        | na   | Area must contain Digits only     |

  @Successful
  Scenario Outline: Successful Product-entry

    Given I filled the product details
    When I entered details with valid  phone number '<PhoneNumber>' and name '<name>' and address '<address>' and quantity '<Quantity>' and area '<Area>'
    Then I should see a message expressing validation '<message>'
    Examples:
      | PhoneNumber | name   | address | Quantity | Area | message              |
      | 97056251710 | carpet | Nablus  | 4        | 300  | Successful insertion |
