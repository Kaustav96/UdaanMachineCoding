# UdaanMachineCoding
Problem Statement
You happen to be a budding entrepreneur and you have come up with an idea to build an ecommerce giant like amazon, flipkart, walmart, etc. As part of this ambition, you want to build a platform to duplicate the concept of Limited Time Deals.

Limited Time Deals
A limited time deal implies that a seller will put up an item on sale for a limited time period, say, 2 hours and will keep a maximum limit on the number of items that would be sold as part of that deal. 
Users cannot buy the deal if the deal time is over 
Users cannot buy if the maximum allowed deal has already been bought by other users.
One user cannot buy more than one item as part of the deal.

Task is to create APIs to enable following operations
Create a deal with price and number of items to be sold as part of the deal
End a deal 
Update a deal to increase the number of items or end time
Claim a deal


Guidelines
Document and communicate  your assumptions in README. 
Create a working solution with production quality code.
Use an external database like postgres/mysql or any noSQL database
Define and Create APIs to support the operations mentioned above
Define the associated entities related to inventory by creating relevant tables in Database. You do not need to create APIs to manage inventory
Write few unit tests for the most important code



What are we looking for?
Your approach towards the solution
How you write code in terms of readability and maintainability
Designing Database Schema 
Usage of best practices
Testing skills

Models -
User Model -> user name , email, phone
Deal Model-> dealname, discount value, start time, end time.
Item model-> itemName, itemActualPrice,maxItemsonDeal.
Service -
LimitedDealsService->
1. createUser(user);
2. createDeal(deal,item);  // 1 deal-> multiple items.
3. claimDeal(dealId);
	a. check if user has already claimed the deal.
	b. check if maxItemsOnDeal is not 0.
		a. if 0 and user buys then throw exception -> 			MaxItemsOnDealOverException()
		b. if not 0 but time limit exceeds for deal -> TimeLimitOnDealException();
		c. if not 0 and time limit not exceeds -> claimDeal(dealId);
4. createItem(item);
5. updateDeal(dealid);  // we can add more items to the existing deal or increase the end time. ( can have a switch case to select option between the 2)
6. endDeal(dealId); // to end the deal before end time or if items count == 0.
