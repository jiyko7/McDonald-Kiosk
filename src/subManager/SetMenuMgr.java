package subManager;


import food.SetMenu;
import mcDonaldKiosk.Manager;
import mgr.Factory;

public class SetMenuMgr extends Manager<SetMenu> implements Factory<SetMenu> 
{
	private static SetMenuMgr INSTANCE = new SetMenuMgr();
	private SetMenuMgr() {}
	public static SetMenuMgr getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new SetMenuMgr();
		}
		return SetMenuMgr.INSTANCE;
	}
   	public SetMenu create() {
   		return new SetMenu();
   	}

	
}

