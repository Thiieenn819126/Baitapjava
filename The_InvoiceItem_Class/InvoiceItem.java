package The_InvoiceItem_Class;

public class InvoiceItem {
private String id;
private String desc;
private int qty;
private double unitprice;

public InvoiceItem(String id, String desc, int qty, double unitprice) {
	this.id = id;
	this.desc = desc;
	this.qty = qty;
	this.unitprice = unitprice;
}
public String getId() {
	return id;
}
public String getDesc() {
	return desc;
}

public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public double getUnitprice() {
	return unitprice;
}
public void setUnitprice(double unitprice) {
	this.unitprice = unitprice;
}
public double getTotal() {
	return unitprice*qty;
	
}
public String toString() {
	return "InvoiceItem[id= "+id+",desc= "+desc+",Qty= "+qty+",UnitPrice= "+unitprice +"]";
}
}
