@user @budget
Feature: Add budget

Scenario: Add budget successfully
  When Add budget 1000 for "2017-10"
  Then exists budget 1000 for "2017-10"

Scenario: Input already exist and unique record
  Given budget 1000 for "2017-10" already exist
  When Add budget 2000 for "2017-10"
  Then exists budget 2000 for "2017-10"

Scenario: Input a new budget following an existing budget
  Given budget 4000 for "2017-02" already exist
  When Add budget 1000 for "2017-03"
  Then exists budget 1000 for "2017-03"

Scenario: Input a new budget not following by an existing budget
  Given budget 500 for "2017-01" already exist
  When Add budget 1000 for "2017-03"
  Then not exists budget 1000 for "2017-03"
  And display error msg "error"
