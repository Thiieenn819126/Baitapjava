package BaiTap;

public class bai2_deadlock {
	
	public void haha() {
		System.out.println("hahahaha");
	}
	public void ngu(Thread a) throws InterruptedException {
		haha();
		a.wait();
		
		
		
	}
	
	public Runnable tat(Thread a) {
		return new Runnable() {
			public void run() {
				
				try {
					ngu(a);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					System.out.println("tắt cu kia rồi");
				}
			     
			}
		};
		
		
	}
	
	
	public static void main(String[] args) {
	
		bai2_deadlock c= new bai2_deadlock();
		Thread a = new Thread(c.tat(null));
		Thread b = new Thread(c.tat(a));
		
		a.start();
		b.start();
	}

}
