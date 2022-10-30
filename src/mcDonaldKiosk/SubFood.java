package mcDonaldKiosk;

import java.util.InputMismatchException;
import java.util.Scanner;

import food.Burger;
import subManager.BurgerMgr;
import subManager.DrinkMgr;
import subManager.SideMgr;
import subManager.SideOtherMgr;

public class SubFood {

	BurgerMgr burgerMgr = BurgerMgr.getInstance();
	DrinkMgr drinkMgr = DrinkMgr.getInstance();
	SideMgr sideMgr = SideMgr.getInstance();
	SideOtherMgr sideOtherMgr = SideOtherMgr.getInstance();

	void readFile() {
		burgerMgr.readAll("Burger.txt", burgerMgr);
		drinkMgr.readAll("Drink.txt", drinkMgr);
		sideMgr.readAll("SideMenu.txt", sideMgr);
		sideOtherMgr.readAll("OtherMenu.txt", sideOtherMgr);
	}

	void addMenu(Scanner scan) {
		System.out.println("추가할 메뉴를 선택하세요\n" + "1.버거 2.음료 3.사이드메뉴 4.기타메뉴 5.나가기");
		int temp;
		try {
			temp = scan.nextInt();
		} catch (InputMismatchException e) {
			temp = 5;
		}
		switch (temp) {
		case 1:
			burgerMgr.newAdd(scan, burgerMgr);
			break;
		case 2:
			drinkMgr.newAdd(scan, drinkMgr);
			break;
		case 3:
			sideMgr.newAdd(scan, sideMgr);
			break;
		case 4:
			sideOtherMgr.newAdd(scan, sideOtherMgr);
			break;
		case 5:

			return;
		}
	}

	void modifiedMenu(Scanner scan, SubSet subSet) {
		int temp;
		System.out.println("수정할 메뉴를 선택하세요 >>");
		System.out.println("1.버거 2.음료 3.사이드메뉴 4.기타메뉴 5.나가기");
		try {
			temp = scan.nextInt();
		} catch (InputMismatchException e) {
			temp = 5;
		}

		switch (temp) {
		case 1:
			burgerMgr.printAll();
			Burger tempBurger = burgerMgr.popBurger(scan, "수정");
			try {
				tempBurger.modify(scan);
			} catch (NullPointerException e) {
				return;
			}

			burgerMgr.mList.add(tempBurger);
			subSet.deleteSetMenu(tempBurger);
			break;
		case 2:
			drinkMgr.printAll();
			drinkMgr.modifyFinder(scan);
			break;
		case 3:
			sideMgr.printAll();
			sideMgr.modifyFinder(scan);
			break;
		case 4:
			sideOtherMgr.printAll();
			sideOtherMgr.modifyFinder(scan);
			break;
		case 5:
			return;
		}
	}

	public void deleteMenu(Scanner scan, SubSet subSet) {
		while (true) {
			System.out.println("삭제할 메뉴를 선택하세요 >>");
			System.out.println("1.버거 2.음료 3.사이드메뉴 4.기타메뉴 5.나가기");
			int temp;
			try {
				temp = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("잘못된 입력으로 인해 삭제된 부분은 없습니다.");
				return;
			}

			switch (temp) {
			case 1:
				burgerMgr.printAll();
				subSet.deleteSetMenu(burgerMgr.popBurger(scan, "삭제"));
				break;
			case 2:
				drinkMgr.printAll();
				drinkMgr.remove(scan);

				break;
			case 3:
				sideMgr.printAll();
				sideMgr.remove(scan);
				break;
			case 4:
				sideOtherMgr.printAll();
				sideOtherMgr.remove(scan);
				break;
			case 5:
				return;

			}

		}
	}

	void menuPrint() {
		System.out.println("=======버거메뉴========");
		burgerMgr.printAll();
		System.out.println("=======음료메뉴========");
		drinkMgr.printAll();
		System.out.println("=======사이드메뉴========");
		sideMgr.printAll();
		System.out.println("=======기타메뉴========");
		sideOtherMgr.printAll();
	}

	static void sellUp(String kwd) {
		BurgerMgr.getInstance().nowSell(kwd);
		DrinkMgr.getInstance().nowSell(kwd);
		SideMgr.getInstance().nowSell(kwd);
		SideOtherMgr.getInstance().nowSell(kwd);
	}

}
