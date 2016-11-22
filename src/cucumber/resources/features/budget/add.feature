@user
Feature: Add budget

Scenario: Add budget successfully
  When Add budget 1000 for "2017-10"
  Then exists budget 1000 for "2017-10"

Scenario: Input already exist and unique record
  Given budget 1000 for "2017-10" already exist
  When Add budget 2000 for "2017-10"
  Then exists budget 2000 for "2017-10"


