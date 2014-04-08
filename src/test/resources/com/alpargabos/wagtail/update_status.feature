Feature: Update status
  As a registered user on Twitter
  I want to write a new tweet
  So my followers can see what is happening with me

  Scenario: Write a new tweet
    Given I am wagtail user
    When I update my status to: "I am on ACCU! #FTW"
    Then the following will appear on my time line
    """
    id:452257058373767200 Wagtail:I am on ACCU! #FTW
    """