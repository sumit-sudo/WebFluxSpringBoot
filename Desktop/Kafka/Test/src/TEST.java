
public class TEST {

	public static void main(String[] args) {
	
		try {
			int a[]=new int[1];
			a[1]=20/0;
			a[2]=50;
		}catch(ArithmeticException e) {
			System.out.println(e);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
		}catch(Exception e){
			System.out.println("hi");
		}
		System.out.println("rest");
	}

}
