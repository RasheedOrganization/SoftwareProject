@Product-feature
  Feature: Product-entry
    product entry must be correct and accurate

    @Unsuccessful
    Scenario Outline: I have chosen to enter the product details
    the worker should see an error message on the screen

      Given I filled product details
      When I enter an invalid Shape type '<shapeType>'
      And I enter an invalid Phone number format'<PhoneNumber>'
      And I enter an invalid name format'<name>'
      And I enter an empty Address'<address>'
      And I enter an invalid Height format'<Height>'
      And I enter an invalid Width format'<Width>'
      And I enter an invalid Radius format'<Radius>'
      And I enter an invalid Quantity format'<Quantity>'
      Then I should tell the worker a warning message'<message>'
      Examples:
        | shapeType   | PhoneNumber | name   | address | Height | Width | Radius | Quantity | message                                             |
        | Cube        | 97059251755 | pillow | Jenin   | 20     | 30    |        | 1        | Shape type must only be in 'Rectangular , Circular' |
        | Rectangular | 975684126   | carpet | Nablus  | 10     | 20    |        | 1        | Please enter a valid Phone Number                   |
        | Circular    | 97059251756 | carpet |         |        |       | 1      | 2        | Address can't be empty                              |
        | Rectangular | 97059251757 | carpet | Jenin   | as     |       |        |          | Height can only be a number                         |
        | Rectangular | 97059251758 | carpet | Nablus  | be     |       |        |          | Width can only be a number                          |
        | Circular    | 97059251743 | carpet | Jenin   |        |       | asd    |          | Radius can only be a number                         |
        | Circular    | 97059251711 | carpet | Jenin   |        |       | 1      |          | Quantity can't be empty                             |
        | Rectangular | 97056251721 | 14     | Jenin   | 12     | 3     |        | 4        | Name can't contain digits                           |

    @Successful
    Scenario Outline: Successful Product-entry

      Given I filled product details
      When I entered details with valid  shape type'<shapeType>' and phone number'<PhoneNumber>' and name'<name>' and address'<address>' and '<Height>' and'<Width>' and'<Radius>'and quantity'<Quantity>'
      Then I should see a message expressing validation'<message>'
      Examples:
        | shapeType | PhoneNumber | name   | address | Height | Width | Radius | Quantity | message              |
        | Cube      | 97059251755 | pillow | Jenin   | 20     | 30    |        | 1        | Successful Insertion |
