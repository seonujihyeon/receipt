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
			 
			     System.out.print("상품 번호를 입력하세요 : ");
			     inputNo = scan.nextInt();
			     
			     Statement stmt = conn.createStatement(); 
			     ResultSet rset = stmt.executeQuery("select * from goods where no =" + inputNo); 
			     if(rset.next()) { 
			          System.out.printf("%d %s %d원 %s\n", rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getString(4));
			    
			          item.name = rset.getString(2);
			          item.price = rset.getInt(3);
			 
				}else {
					System.out.println("해당하는 상품이 없습니다.\n");
					continue;
				}
			
				
			     rset.close();
			     stmt.close();
			     conn.close();
			} catch(Exception e) {
					e.printStackTrace();
			}
		
			System.out.print("구매 갯수 : " );
		    count = scan.nextInt();
		    item.count = count;
		    item.total = item.price * count; 
		    		
		    orderlist.add(item);
		    
		    int type;
		    
		    System.out.print("1. 추가구매, 2. 구매종료 : " );
		    
		    type = scan.nextInt();	   
		    System.out.println("--------------------------------------------------" );
		    
			if(type == 2) {
				break;
			}else if(type == 1) {
				continue;
			}else{
			System.out.print("잘못 입력하였습니다. 다시 입력해주세요.\n" );	
			}	
		  
		scan.close();
		
		
		for(int i = 0; i < orderlist.size(); i++) {
			total += orderlist.get(i).price * orderlist.get(i).count;
		}
		System.out.println("");
		System.out.println("");
		System.out.println("                       CU");
		System.out.println("");
		System.out.println("사업자번호:15416001462");
		System.out.println("대표:장경광");
		System.out.println("전화:027010602");
		System.out.println("주소:서울특별시 용산구 청파로 74, 1층 (한강로3가, 전자랜드)");
		System.out.println("");
		System.out.println("--------------------------------------------------");
		System.out.printf("%2s %18s %12s %10s \n", "상품명", "단가", "수량", "금액" );
		System.out.println("--------------------------------------------------");
		
		for(int i = 0; i < orderlist.size(); ++i) {
            System.out.printf("%-20s%d\t     %d\t     %d\n", 
            		orderlist.get(i).name, orderlist.get(i).price, orderlist.get(i).count, orderlist.get(i).total); 
		}
		System.out.println("--------------------------------------------------");
		System.out.printf("총금액 : %d\n", total);
		System.out.println("--------------------------------------------------");
		System.out.println("      본 영수증은 거래의 참고용으로 사용하시기 바랍니다.");
		System.out.println("                                                  ");
		}

	}
}

