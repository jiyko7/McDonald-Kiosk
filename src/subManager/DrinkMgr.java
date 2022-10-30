package subManager;

import food.Drink;
import mcDonaldKiosk.Manager;
import mgr.Factory;
import mgr.SellAble;

public class DrinkMgr extends Manager<Drink> implements Factory<Drink> ,SellAble
{
	private static DrinkMgr INSTANCE = new DrinkMgr();
	private DrinkMgr() {}
	public static DrinkMgr getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new DrinkMgr();
		}
		return DrinkMgr.INSTANCE;
	}
	@Override
	public Drink create() {
		return new Drink();
	}
	public void nowSell(String kwd)
	{
		Drink temp;
		temp = find(kwd);
		if(temp!=null)
		{
			mList.remove(temp);
			temp.sellAmount++;
			mList.add(temp);
		}
	}
	
}