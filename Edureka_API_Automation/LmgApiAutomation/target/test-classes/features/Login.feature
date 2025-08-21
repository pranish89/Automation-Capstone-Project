Feature: Application Login

Scenario: Application login and Adding product to basket 
Given User is on landing page 
When Create token for Reg user
And create cart Reg user
And Product detail page
Then Product detail is displayed 