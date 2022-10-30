package subManager;

import java.util.Scanner;

import food.Burger;
import mcDonaldKiosk.Manager;
import mgr.Factory;
import mgr.SellAble;

public class BurgerMgr extends Manager<Burger> implements Factory<Burger>,SellAble
{
	
	private static BurgerMgr INSTANCE = new BurgerMgr();
	private BurgerMgr() {}
	public static BurgerMgr getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new BurgerMgr();
		}
		return BurgerMgr.INSTANCE;
	}
	public Burger popBurger(Scanner scan,String kwd)
	{
		System.out.printf("%s할 번호 입력하세요>>",kwd);
		try
		{
		return this.popObject(scan.nextInt());
		}
		catch(Exception e)
		{
			return null;
		}
	}
	@Override
	public Burger create() {
	
		return new Burger();
	}
	public void nowSell(String kwd)
	{
		Burger temp;
		temp = find(kwd);
		if(temp!=null)
		{
			mList.remove(temp);
			temp.sellAmount++;
			mList.add((Burger)temp);
		}
	}
	
	
	
}