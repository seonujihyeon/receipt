package dds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class receipt {

	
	public static void main(String[] args) {
		
		
		ArrayList<OrderListArray> orderlist = new ArrayList<OrderListArray>();
		InputOrder order = new InputOrder();
		int count = 0;
		int total = 0;
		Scanner scan = new Scanner(System.in); 
		
		while(true) {
		OrderListArray item = new OrderListArray();
		
			try {
				 Class.forName("com.mysql.cj.jdbc.Driver"); 
			     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/goodslist", "root", "112023");
			 
			     int inputNo;
			 
			     System.out.print("��ǰ ��ȣ�� �Է��ϼ��� : ");
			     inputNo = scan.nextInt();
			     
			     Statement stmt = conn.createStatement(); 
			     ResultSet rset = stmt.executeQuery("select * from goods where no =" + inputNo); 
			     if(rset.next()) { 
			          System.out.printf("%d %s %d�� %s\n", rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getString(4));
			    
			          item.name = rset.getString(2);
			          item.price = rset.getInt(3);
			 
				}else {
					System.out.println("�ش��ϴ� ��ǰ�� �����ϴ�.\n");
					continue;
				}
			
				
			     rset.close();
			     stmt.close();
			     conn.close();
			} catch(Exception e) {
					e.printStackTrace();
			}
		
			System.out.print("���� ���� : " );
		    count = scan.nextInt();
		    item.count = count;
		    item.total = item.price * count; 
		    		
		    orderlist.add(item);
		    
		    int type;
		    
		    System.out.print("1. �߰�����, 2. �������� : " );
		    
		    type = scan.nextInt();	   
		    System.out.println("--------------------------------------------------" );
		    
			if(type == 2) {
				break;
			}else if(type == 1) {
				continue;
			}else{
			System.out.print("�߸� �Է��Ͽ����ϴ�. �ٽ� �Է����ּ���.\n" );	
			}	
		  
		scan.close();
		
		
		for(int i = 0; i < orderlist.size(); i++) {
			total += orderlist.get(i).price * orderlist.get(i).count;
		}
		System.out.println("");
		System.out.println("");
		System.out.println("                       CU");
		System.out.println("");
		System.out.println("����ڹ�ȣ:15416001462");
		System.out.println("��ǥ:��汤");
		System.out.println("��ȭ:027010602");
		System.out.println("�ּ�:����Ư���� ��걸 û�ķ� 74, 1�� (�Ѱ���3��, ���ڷ���)");
		System.out.println("");
		System.out.println("--------------------------------------------------");
		System.out.printf("%2s %18s %12s %10s \n", "��ǰ��", "�ܰ�", "����", "�ݾ�" );
		System.out.println("--------------------------------------------------");
		
		for(int i = 0; i < orderlist.size(); ++i) {
            System.out.printf("%-20s%d\t     %d\t     %d\n", 
            		orderlist.get(i).name, orderlist.get(i).price, orderlist.get(i).count, orderlist.get(i).total); 
		}
		System.out.println("--------------------------------------------------");
		System.out.printf("�ѱݾ� : %d\n", total);
		System.out.println("--------------------------------------------------");
		System.out.println("      �� �������� �ŷ��� ��������� ����Ͻñ� �ٶ��ϴ�.");
		System.out.println("                                                  ");
		}

	}
}

