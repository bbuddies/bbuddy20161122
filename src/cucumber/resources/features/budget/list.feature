@user @budget
Feature: List budget

  Scenario: Already have budget in database
    Given has budgets
      | amount  | month   |
      | 1000 | 2016-08 |
      | 1500 | 2016-07 |
      | 1600 | 2016-09 |
    When search
    Then list budgets
      | amount  | month   |
      | 1500 | 2016-07 |
      | 1000 | 2016-08 |
      | 1600 | 2016-09 |

  Scenario: No budget in database
    When search
    Then show "No budget"
