package mcDonaldKiosk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import food.Food;
import food.SetMenu;
import food.Special;
import subManager.SetMenuMgr;
import subManager.SpecialMgr;

public class SubSet {

	SetMenuMgr setMenuMgr = SetMenuMgr.getInstance();
	SpecialMgr specialMgr = SpecialMgr.getInstance();

	void readFile() {
		setMenuMgr.readAll("Set.txt", setMenuMgr);
		specialMgr.readAll("Special.txt", specialMgr);
	}

	void setPrint() {
		System.out.println("=======세트메뉴========");
		setMenuMgr.printAll();
		System.out.println("=======행사메뉴========");
		specialMgr.printAll();

	}

	public void addSet(Scanner scan) {
		SetMenu M = new SetMenu();
		M.add(scan);
		setMenuMgr.mList.add(M);
	}

	void modifySet(Scanner scan) {
		int findIndex = 0;
		System.out.println("수정할 세트메뉴를 선택하세요 >>\n " + "==========================");
		setMenuMgr.printAll();
		System.out.println("==========================\n" + "수정할 번호 입력하세요>>");
		try {
			findIndex = scan.nextInt();
		} catch (InputMismatchException e) {
			findIndex = -1;
		}
		if (findIndex == -1)
			return;
		SetMenu s = (SetMenu) setMenuMgr.mList.get(findIndex);
		s.modify(scan);
		s.print();
		System.out.print("수정 완료: ");
	}

	public void deleteSet(Scanner scan) {
		int findIndex = 0;
		System.out.println("삭제할 세트메뉴를 선택하세요 >>\n " + "==========================");
		setMenuMgr.printAll();
		System.out.println("==========================\n" + "삭제할 번호 입력하세요>>");
		try {
			findIndex = scan.nextInt();
		} catch (InputMismatchException e) {
			findIndex = -1;
		}
		if (findIndex == -1)
			return;
		setMenuMgr.mList.remove(findIndex);

	}

	public void addSp(Scanner scan) {
		Special s = new Special();
		s.add(scan);
		specialMgr.mList.add(s);

	}

	public void modifySp(Scanner scan) {
		int findIndex = 0;
		System.out.println("수정할 할인행사를 선택하세요 >>\n " + "==========================");
		specialMgr.printAll();
		System.out.println("==========================\n" + "수정할 번호 입력하세요>>");
		try {
			findIndex = scan.nextInt();
		} catch (InputMismatchException e) {
			findIndex = -1;
		}
		if (findIndex == -1)
			return;
		Special s = (Special) specialMgr.mList.get(findIndex);
		s.modify(scan);
		s.print();

	}

	public void deleteSp() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar c1 = Calendar.getInstance();

		String tmp = sdf.format(c1.getTime());
		int today = Integer.parseInt(tmp);

		ArrayList<Special> DeleteList = new ArrayList<>();
		for (Special S : specialMgr.mList) {
			if (S.Enddate < today) {
				DeleteList.add(S);
			}
		}
		specialMgr.mList.removeAll(DeleteList);
		System.out.println("기간이 지난 행사는 모두 삭제하였습니다");
	}

	void deleteSetMenu(Food tempFood) {
		ArrayList<SetMenu> deleteArrayList = new ArrayList<>();
		for (SetMenu sm : setMenuMgr.mList) {
			if (sm.haveBurger.contains(tempFood)) {
				deleteArrayList.add(sm);
			}
		}
		setMenuMgr.mList.removeAll(deleteArrayList);
		deleteArrayList.clear();
		for (Special sp : specialMgr.mList) {
			if (sp.haveBurger.contains(tempFood)) {
				deleteArrayList.add(sp);
			}
		}
		specialMgr.mList.removeAll(deleteArrayList);
		System.out.print(" 변경되어 필요 없어진 세트까지 삭제 완료되었습니다.\n");
	}

}
