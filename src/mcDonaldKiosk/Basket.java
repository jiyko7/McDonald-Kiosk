package mcDonaldKiosk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

import food.Burger;
import food.Drink;
import food.Food;
import food.OtherMenu;
import food.SetMenu;
import food.SideMenu;
import food.Special;
import subManager.BurgerMgr;
import subManager.DrinkMgr;
import subManager.SetMenuMgr;
import subManager.SideMgr;
import subManager.SideOtherMgr;
import subManager.SpecialMgr;

public class Basket {

	int priceAll = 0;
	Food f = new Food();
	int burgerAmount = 0;
	int noBurgerAmount = 0;
	int drinkAmount = 0;
	int sideMenuAmount = 0;
	int salePrice = 0;
	ArrayList<Food> BasketList = new ArrayList<>();

	Admin ad = null;
	Scanner scan = null;
	SetMenuMgr setMenuMgr = SetMenuMgr.getInstance();
	BurgerMgr burgerMgr = BurgerMgr.getInstance();
	DrinkMgr drinkMgr = DrinkMgr.getInstance();
	SideMgr sideMgr = SideMgr.getInstance();
	SpecialMgr specialMgr = SpecialMgr.getInstance();
	SideOtherMgr sideOtherMgr = SideOtherMgr.getInstance();

	Basket(Scanner scanner, Admin admin) {
		scan = scanner;
		ad = admin;
	}


	public Basket(int burgerAmount2, int drinkAmount2, int sideAmount, int priceAll2) {
		drinkAmount = drinkAmount2;
		burgerAmount = burgerAmount2;
		sideMenuAmount = sideAmount;
		priceAll = priceAll2;
	}


	void initBasket() {
		BasketList.clear();
		Kiosk01.BBasket.clear();
		burgerAmount = 0;
		noBurgerAmount = 0;
		drinkAmount = 0;
		sideMenuAmount = 0;
		priceAll = 0;
		salePrice = 0;

	}

	void addBasket(Food f) {
		BasketList.add(f);
		priceAll = priceAll + f.price;

	}

	void deleteBasket() {
		for (Food f : BasketList) {
			f.print();
		}
		System.out.println("\n삭제할 메뉴를 입력해주십시오: ");
		String menu1 = scan.next();
		Food deleteFood = null;
		for (Food b : BasketList) {
			if (b.name.contentEquals(menu1)) {
				deleteFood = b;
			}
		}
		f = (Burger) burgerMgr.find(menu1);
		if (f != null) {
			Special sp = (Special) specialMgr.find(menu1);
			if (sp == null) {
				SetMenu s = (SetMenu) setMenuMgr.find(menu1);
				if (s == null) {
					noBurgerAmount = noBurgerAmount - 1;
				} else {
					Kiosk01.BBasket.remove(f);
					burgerAmount = burgerAmount - 1;
				}
			} else {
				Kiosk01.BBasket.remove(f);
				burgerAmount = burgerAmount - 1;
			}
		}
		if (f == null) {
			f = (Drink) drinkMgr.find(menu1);
			if (f != null) {
				Special sp = (Special) specialMgr.find(menu1);
				if (sp == null) {
					SetMenu s = (SetMenu) setMenuMgr.find(menu1);
					if (s == null) {

					} else {
						drinkAmount = drinkAmount - 1;
					}
				} else {
					drinkAmount = drinkAmount - 1;
				}
			}

		}
		if (f == null) {
			f = (SideMenu) sideMgr.find(menu1);
			if (f != null) {
				sideMenuAmount = sideMenuAmount - 1;
			}
		}
		if (f == null)
			f = (OtherMenu) sideOtherMgr.find(menu1);
		if (f == null) {
			System.out.println("해당 메뉴 없음");
			return;
		}
		if (deleteFood != null)
			BasketList.remove(deleteFood);
		priceAll = priceAll - deleteFood.price;
	}

	int CalculateBasket() {
		int x = 0;
		int y = 0;

		for (Food f : Kiosk01.BBasket) {
			SetMenu s = (Special) specialMgr.find(f.name);
			if (s == null) {
				s = (SetMenu) setMenuMgr.find(f.name);
				if (s == null) {
					continue;
				}
			}
			Kiosk01.SetList.add(s);
		}
		if (drinkAmount >= sideMenuAmount) {
			x = drinkAmount;
			y = sideMenuAmount;
		} else {
			x = sideMenuAmount;
			y = drinkAmount;
		}
		Collections.sort(Kiosk01.SetList);


		if (x >= y && burgerAmount > y && y >= 1) {
			for (int i = 0; i < y; i++) {
				priceAll = priceAll - Kiosk01.SetList.get(i).getPrice();
			}

		} else if (burgerAmount <= x && burgerAmount <= y && burgerAmount >= 1) {
			for (int i = 0; i < burgerAmount; i++) {
				priceAll = priceAll - Kiosk01.SetList.get(i).getPrice();
			}
		}
		return priceAll;
	}

	void print() {
		for (Food f : BasketList) {
			f.print();
		}
		System.out.printf("세트에 포함되지 않는 햄버거의 양은 %d\n" + "세트의 포함되는 햄버거의 양은 %d\n음료수의 양은 %d\n" + "사이드 메뉴의 양은 %d \n",
				noBurgerAmount, burgerAmount, drinkAmount, sideMenuAmount);
		System.out.printf("\n 총 금액은 %d원 입니다.\n\n", priceAll);
	}
}