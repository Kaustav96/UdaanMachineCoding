package udaan;

import java.time.LocalDateTime;
import java.util.*;

public class LimitedDealsService {

	HashMap<String, Users> userMap;
	HashMap<String, Deals> dealsMap;
	HashMap<String, Items> itemsMap;
	HashMap<String, List<Items>> itemsInDealMap;
	HashMap<String, List<Users>> usersAvailingDealsMap;
	Scanner sc = new Scanner(System.in);

	public LimitedDealsService() {
		// TODO Auto-generated constructor stub
		this.userMap = new HashMap<String, Users>();
		this.dealsMap = new HashMap<String, Deals>();
		this.itemsMap = new HashMap<String, Items>();
		this.itemsInDealMap = new HashMap<String, List<Items>>();
		this.usersAvailingDealsMap = new HashMap<String, List<Users>>();
	}

	// 1. create user

	public void createuser(Users user) throws UserAlreadyCreatedException {

		if (userMap.containsKey(user.getId())) {
			throw new UserAlreadyCreatedException("User with id -" + user.getId() + " is already created.");
		}

		userMap.put(user.getId(), user);
		System.out.println("User created with id - " + user.getId());
	}

	// 2. create deals
	public void createDeals(Deals deal) throws DealAlreadyCreatedException {

		if (dealsMap.containsKey(deal.getId())) {
			throw new DealAlreadyCreatedException(
					"Deal with id - " + deal.getId() + " a;ready exists. Please add items to the deal");
		}

		dealsMap.put(deal.getId(), deal);
		itemsInDealMap.put(deal.getId(), new ArrayList<Items>());
		usersAvailingDealsMap.put(deal.getId(), new ArrayList<Users>());
		System.out.println("Deal created with id -> " + deal.getId());
	}

	// 3. create items
	public void createItems(Items item) throws ItemAlreadyCreatedException {
		if (itemsMap.containsKey(item.getId())) {
			throw new ItemAlreadyCreatedException("Item with id - " + item.getId() + " already exists");
		}

		itemsMap.put(item.getId(), item);
		System.out.println("Item created with id -> " + item.getId());
	}

	// 4. update deal
	public void updateDeal(String dealId, String itemId) throws DealNotExistException {

		if (!dealsMap.containsKey(dealId)) {
			throw new DealNotExistException("Deal does not exist for id - " + dealId);
		}

		System.out.println("Choose between the 2 options\n1. Add new items to deal\n2. Change the end time of deal.");

		int ch = sc.nextInt();

		if (ch == 1) {
			addItemToDeals(dealId, itemId);
		} else {
			updateEndTimeOfDeal(dealId);
		}
	}

	public void addItemToDeals(String dealId, String itemId) throws DealNotExistException {
		// TODO Auto-generated method stub
		if (!dealsMap.containsKey(dealId)) {
			throw new DealNotExistException("Deal does not exist for id - " + dealId);
		}

		List<Items> itemList = itemsInDealMap.get(dealId);

		if (itemList == null) {
			itemList = new ArrayList<Items>();
		}
		if (itemList.contains(itemsMap.get(itemId))) {
			System.out.println("Item already present cannot add again");
		} else {
			itemList.add(itemsMap.get(itemId));
			System.out.println("item " + itemsMap.get(itemId).getItemName() + " has been added to deal - " + dealId);
		}
	}

	private void updateEndTimeOfDeal(String dealId) throws DealNotExistException {
		// TODO Auto-generated method stub
		if (!dealsMap.containsKey(dealId)) {
			throw new DealNotExistException("Deal does not exist for id - " + dealId);
		}

		Deals dealData = dealsMap.get(dealId);
		LocalDateTime endDateTime = dealData.getEndDateTime();

		endDateTime = endDateTime.minusHours(1);

		dealData.setEndDateTime(endDateTime);

		dealsMap.put(dealId, dealData);
		System.out.println("Updated end time of deal - " + dealId);
	}

	// 5. show deals
	public void showDeals() {
		for (Map.Entry<String, Deals> entry : dealsMap.entrySet()) {
			Deals deal = entry.getValue();
			System.out.println("{\nId: " + deal.getId() + "\nDealname: " + deal.getDealName() + "\nDiscount Offered: "
					+ deal.getDiscountValue() + "\nStart Time: " + deal.getStartDateTime() + "\nEnd Time: "
					+ deal.getEndDateTime() + "\n}\n");
		}
	}

	// 6. claim deal
	public void claimDeal(String dealId, String userId, String itemId)
			throws DealNotExistException, UserNotPresentException, UserClaimedDealException, DealExpiredException {
		if (!userMap.containsKey(userId)) {
			throw new UserNotPresentException("user not present");
		}
		if (!dealsMap.containsKey(dealId)) {
			throw new DealNotExistException("Deal does not exist for id - " + dealId);
		}

		List<Users> userList = usersAvailingDealsMap.get(dealId);
		// check user has deal
		if (userList.size() == 0) {
			userList = new ArrayList<Users>();
			usersAvailingDealsMap.put(dealId, userList);
			List<Items> itemList = itemsInDealMap.get(dealId); // list of items in deal
			for (Items item : itemList) {
				if (item.getId().equals(itemId)) {
					if (item.getItemsOnDeal() > 0 && (dealsMap.get(dealId).getEndDateTime().isAfter(LocalDateTime.now())
							|| dealsMap.get(dealId).getEndDateTime().isEqual(LocalDateTime.now()))) {
						item.setItemsOnDeal(item.getItemsOnDeal() - 1);
						System.out.println("user availed deal - " + dealsMap.get(dealId).getDealName() + " for item - "
								+ item.getItemName());
						break;
					}else {
						throw new DealExpiredException("Deal expired");
					}
				}
			}
		}else {
			throw new UserClaimedDealException("user has already claimed the deal");
		}
	}

}
