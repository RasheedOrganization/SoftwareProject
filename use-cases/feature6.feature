Feature: Customer discount
  As a business owner, i want to be able to offer a discount to customers who frequently use our service
  and have spent over a certain amount of money

  Scenario: valid discount calculation
    Given a customer with an order value of 1000
    When the customer is eligible for a 10% discount
    Then the discount amount should be 100
    And the total order value should be 900

  Scenario: Invalid discount calculation
    Given a customer with an order value of 50
    When the customer is not eligible for any discount
    Then the discount amount should be 0
    And the total order value should be 50

  Scenario: Maximum discount applied
    Given a customer with an order value of 5000
    When the customer is eligible for a maximum discount of 20%
    Then the discount amount be should be 1000
    And the total order value should be 4000

  Scenario: Minimum discount applied
    Given a customer with an order value of 100
    When the customer is not eligible for any discount
    Then the discount amount be should be 0
    And an error message should be displayed stating that the minimum order value of 200 was not met