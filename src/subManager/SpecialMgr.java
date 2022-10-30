package subManager;

import food.Special;
import mcDonaldKiosk.Manager;
import mgr.Factory;

public class SpecialMgr extends Manager<Special> implements Factory<Special>
{
	private static SpecialMgr INSTANCE = new SpecialMgr();
	private SpecialMgr() {}
	public static SpecialMgr getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new SpecialMgr();
		}
		return SpecialMgr.INSTANCE;
	}
	@Override
   	public Special create() {
   		return new Special();
   	}

	






	   
	


}