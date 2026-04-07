Feature: Tenforce Career Navigation

  Scenario: Validate article and job section
    Given User launches Tenforce website
    When User navigates to Career page
    And User opens Life at Tenforce section
    And User opens "Life of two interns" article
    And User scrolls through the article
	Then User verifies job section text