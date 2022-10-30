package food;

import java.util.InputMismatchException;
import java.util.Scanner;

import mgr.Manageable;

public class Food implements Manageable,Comparable<Food> {
	public String name;
	public int price =0;
	public int sellAmount =0;

	@Override
	public void read(Scanner scan) {
		name = scan.next();
		price = scan.nextInt();
		sellAmount = scan.nextInt();
	}

	@Override
	public void print() {
		System.out.printf("%s(%d원) 판매량:%d\n",name,price,sellAmount);
	}

	@Override
	public boolean matches(String kwd) {
		if(name.equals(kwd)) 
			return true;
		return false;
	}
	
	@Override
	public void add(Scanner scan) {
		System.out.printf("이름 입력 -> ");
		name = scan.next();
		System.out.printf("가격 입력 -> ");
		try
		{
			price = scan.nextInt();	
		}
		catch(InputMismatchException e)
		{
			return;
		}
		
		
	}
	@Override
	public void modify(Scanner scan) {
		scan.nextLine();
		System.out.println("수정할 내용을 작성해주세요.");
		System.out.printf("이름  : %s -> ", name);
		String temp = scan.nextLine();
		if (temp.length() > 0)
			name = temp;
		System.out.printf("가격 : %d -> ", price);
		temp = scan.nextLine();
		if (temp.length() > 0)
		{
			try {
				price = Integer.parseInt(temp);
			}
			catch(NumberFormatException e)
			{
				System.out.print("잘못 기입하셨습니다.");
				price = 0;
			}
		}
		System.out.print("수정완료");
	}
	
	@Override
	public String getLine() {
		return name+" "+price+" "+sellAmount+"\n";
	}

	@Override
	public int compareTo(Food other) {
		if(sellAmount > other.sellAmount) {
			return -1;
		}
		if(sellAmount < other.sellAmount) {
			return 1;
		}
		if(price < other.price) {
			return -1;
		}
		if(price < other.price) {
			return 1;
		}
		return 0;
	}
	
}