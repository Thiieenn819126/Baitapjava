package BaiTap;

public class bai1_counter {
	
	public static  int counter=0;
	 public int  Counter() {
		 return counter+=1000;
		 
	 }
	 static int getCounter() {
		 return counter;
	 }
	public  Runnable counter () {
	return new Runnable() {
			public void run() {
			Counter();
			System.out.println(counter);
		
		}
		};
	}
	public static void main(String[] args) {
		bai1_counter a = new bai1_counter();
		Thread counter1 = new Thread(a.counter());
		Thread counter2 = new Thread(a.counter());
		counter1.start();
		counter2.start();
	
		
		
	}

}
