package BaiTap;

public class VeTau {
    private int veTau = 10;

    public synchronized void banVe() throws InterruptedException {
        while (veTau < 0) {
        	System.out.println("Hết vé ,tạm ngưng để bổ sung vé!..");
        	wait();
        }
            veTau--;
            System.out.println(Thread.currentThread().getName() + ": Đã bán 1 vé");
            System.out.println("Số lượng còn: " + veTau);
            System.out.println(" ");
            notifyAll();
    }              
    
    
    public synchronized void bosungve() throws InterruptedException {
    	while(veTau>0){
    		wait();
    	 } 
    	Thread.sleep(1000);
    	for(int i=1;i<=10;i++) {
    		
    		
    	veTau++;
    	
    	System.out.println("Đã bổ sung 1 vé,lượng vé hiện tại: "+veTau);
    	
    	if(veTau==10) {System.out.println("Bổ sung hoàn tất tiếp tục bán!..");
    	notifyAll();
    	}
    	}
   	}
    
    public Runnable nguoiban() {
        return new Runnable() {
            @Override
            public void run() {
              try {
            	while(true) {
				banVe();				
				Thread.sleep(1000);
            	 }
			} catch (InterruptedException e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
        };
    }
 
 public Runnable Dailive() {
	 return new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while(true) {
			   	bosungve();
		    	Thread.sleep(1000);
				
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}; 
	 
 }
 
    public static void main(String[] args) {
        VeTau a = new VeTau();
        Thread[] nguoiBan = new Thread[4];
        for (int i = 1; i <= 4; i++) {
            nguoiBan[i - 1] = new Thread(a.nguoiban(), "Người bán " + i);
            nguoiBan[i - 1].start();
        }
        Thread nguoiBosung = new Thread(a.Dailive(), "Đại lí bổ sung: ");
        nguoiBosung.start();
    }
}