package subManager;


import food.OtherMenu;
import mcDonaldKiosk.Manager;
import mgr.Factory;
import mgr.SellAble;

public class SideOtherMgr extends Manager<OtherMenu> implements Factory<OtherMenu> ,SellAble
{
	private static SideOtherMgr INSTANCE = new SideOtherMgr();
	private SideOtherMgr() {}
	public static SideOtherMgr getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new SideOtherMgr();
		}
		return SideOtherMgr.INSTANCE;
	}
	@Override
	public OtherMenu create() {
		return new OtherMenu();
	}
	public void nowSell(String kwd)
	{
		OtherMenu temp;
		temp = find(kwd);
		if(temp!=null)
		{
			mList.remove(temp);
			temp.sellAmount++;
			mList.add(temp);
		}
	}
}