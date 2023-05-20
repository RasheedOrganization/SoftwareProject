@tag
Feature: Reports
  Print Reports should be more precise and quick

  @Successful
  Scenario: Successful Print rreport

  user should get a PDF file and open jasper report.

    Given I have chosen to print report screen
    When I press on button to print a report
    Then I should see a report on jasper report and transfer to PDF