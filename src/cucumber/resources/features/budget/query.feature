@user
Feature: Query budget

  Scenario: Query budget within single month
    Given has budgets
      | amount  | month   |
      | 30000 | 2016-04 |
    When query from 2016-04-05 o 2016-04-14
    Then show budget 10000.00