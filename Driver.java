package udaan;

import java.time.LocalDateTime;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 	Limited Time Deals
			A limited time deal implies that a seller will put up an item on sale for a limited time period, say, 2 hours and will keep a maximum limit on the number of items that would be sold as part of that deal. 
			Users cannot buy the deal if the deal time is over 
			Users cannot buy if the maximum allowed deal has already been bought by other users.
			One user cannot buy more than one item as part of the deal.
		 */
		
		/*
		 * 
		 */
		
		LimitedDealsService dealsService = new LimitedDealsService();
		
		Scanner sc= new Scanner(System.in);
		
		while(true) {
			String command = sc.nextLine();
			String[] cmdArr = command.split(" ");
			String taskType = cmdArr[0];
			
			switch (taskType) {
			case "create":
				String type = cmdArr[1];
				switch (type) {
				case "user":
					String userId = cmdArr[2],name=cmdArr[3],email=cmdArr[4];
					try {
						dealsService.createuser(new Users(userId, name, email));
					} catch (UserAlreadyCreatedException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "deals":
					String dealId = cmdArr[2],dealName=cmdArr[3];
					int discountValue = Integer.parseInt(cmdArr[4]);
					LocalDateTime startTime = LocalDateTime.now();
					LocalDateTime endTime = LocalDateTime.now().plusHours(2);
					try {
						dealsService.createDeals(new Deals(dealId, dealName, discountValue, startTime, endTime));
					} catch (DealAlreadyCreatedException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "items":
					String itemId = cmdArr[2],itemName=cmdArr[3];
					int itemsOnDeal = Integer.parseInt(cmdArr[5]);
					double actualPrice = Double.parseDouble(cmdArr[4]);
					try {
						dealsService.createItems(new Items(itemId, itemName, actualPrice, itemsOnDeal));
					} catch (ItemAlreadyCreatedException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("Give correct option");
					break;
				}
				break;
			case "add":
				String dealid = cmdArr[1],itemId = cmdArr[2];
				try {
					dealsService.addItemToDeals(dealid, itemId);
				} catch (DealNotExistException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
				break;
			case "update":
				dealid = cmdArr[1];
				itemId= cmdArr[2];
				try {
					dealsService.updateDeal(dealid,itemId);
				} catch (DealNotExistException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case "show":
				dealsService.showDeals();
				break;
			case "claim":
				dealid = cmdArr[1];
				String userId = cmdArr[2];
				itemId = cmdArr[3];
				try {
					dealsService.claimDeal(dealid, userId, itemId);
				} catch (DealNotExistException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (UserNotPresentException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (UserClaimedDealException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (DealExpiredException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			default:
				System.out.println("Give correct option");
				break;
			}
		}
		
	}

}
