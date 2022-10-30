package food;

import java.util.*;


public class Special extends SetMenu {
	int date;
	public int Enddate;

	@Override
	public void read(Scanner scan) {
		super.read(scan);
		date = scan.nextInt();
		Enddate = scan.nextInt();
	}

	@Override
	public void print() {
		super.print();
		System.out.printf("기간 %d ~ %d\n", date, Enddate);
	}

	@Override
	public boolean matches(String kwd) {
		if (super.matches(kwd))
			return true;
		return false;
	}

	@Override
	public String getLine() {
		String a = super.getLine();
		a = a + (date + " " + Enddate + "\n");
		return a;
	}

	@Override
	public void add(Scanner scan) {
		super.add(scan);
		System.out.print("시작기간 입력:");
		try {
			date = scan.nextInt();
		}
		catch(InputMismatchException e)
		{

		}
		
		System.out.print("종료기간 입력:");
		try {
			Enddate = scan.nextInt();
		}
		catch(InputMismatchException e)
		{
			return;
		}
		

	}

	public void modify(Scanner scan) {
		super.modify(scan);
		System.out.printf("기간시작 : %d -> ", date);
		String temp = scan.nextLine();
		if (temp.length() > 0)
			try {
				date = Integer.parseInt(temp);	
			}
			catch(NumberFormatException e)
			{
				date= 0;
			}
			
		System.out.printf("기간종료 : %d -> ", Enddate);
		temp = scan.nextLine();
		if (temp.length() > 0)
			try {
				Enddate = Integer.parseInt(temp);	
			}
			catch(NumberFormatException e)
			{
				Enddate= 0;
			}
		System.out.print("수정 완료: ");
	}
	
}
