package subManager;

import food.SideMenu;
import mcDonaldKiosk.Manager;
import mgr.Factory;
import mgr.SellAble;

public class SideMgr extends Manager<SideMenu> implements Factory<SideMenu> ,SellAble
{
	private static SideMgr INSTANCE = new SideMgr();
	private SideMgr() {}
	public static SideMgr getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new SideMgr();
		}
		return SideMgr.INSTANCE;
	}
	@Override
	public SideMenu create() {
		return new SideMenu();
	}
	public void nowSell(String kwd)
	{
		SideMenu temp;
		temp = find(kwd);
		if(temp!=null)
		{
			mList.remove(temp);
			temp.sellAmount++;
			mList.add(temp);
		}
	}

		

}