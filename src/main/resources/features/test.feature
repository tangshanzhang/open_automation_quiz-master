Feature: TestSubmission
	

	@tag
  	Scenario: Test Fill form for selenium
    Given I navigate to templates.jinshuju.net
    When I switch to iframe
    And I slide down iframe
    And I choose the second option
	Then I screenshot
	When I click next button
	And I slide down iframe
	And I fill in the second form
	Then I screenshot
	When I click next button
	And I slide down iframe
	And I fill in the third form
	Then I screenshot
	When I click submission button
    Then I screenshot