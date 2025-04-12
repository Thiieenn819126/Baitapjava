
package BaiTap;

public class BaiToan5Trietgia {
	private int n =1;
	
	public int dem() {
		if(n==4) {
			return n=1;
		}else if(n==5) {
			return n=2;
		}else 
			return n+=2;
	};
	
	public void an(int maso,String ten) throws InterruptedException {
	    synchronized (this) {
		if( maso != n && maso != (n + 2) % 5) {
			System.out.println(ten+" nhịn!");
			wait();
		}
		Thread.sleep(5000);
		System.out.println(ten+" Đang  ăn");
		
		dem();
		notifyAll();
		
	}
	}
   public Runnable KhoiTao(int maso,String ten) {
	   return  new Runnable() {
		public void run() {
			try {
				while(true) {
					an( maso, ten);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
	     
			
		}
	};
	   
	   
   }
	public static void main(String[] args) {
		BaiToan5Trietgia trietgia = new BaiToan5Trietgia();
		Thread nguoiA = new Thread(trietgia.KhoiTao(1, "người 1"));
		Thread nguoiB = new Thread(trietgia.KhoiTao(2, "người 2"));
		Thread nguoiC = new Thread(trietgia.KhoiTao(3, "người 3"));
		Thread nguoiD = new Thread(trietgia.KhoiTao(4, "người 4"));
		Thread nguoiE = new Thread(trietgia.KhoiTao(5, "người 5"));
		nguoiA.start();
		nguoiB.start();
		nguoiC.start();
		nguoiD.start();
		nguoiE.start();
	}

}
