package ex2;

public class Book {
	private String name;
	private Author author;
	private double price;
	private int qty =0;
	
	public Book(String name, Author author, double price) {
		this.name = name;
		this.author = author;
		this.price = price;
	}

	public Book(String name, Author author, double price, int qty) {
	
		this.name = name;
		this.author = author;
		this.price = price;
		this.qty = qty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", Author[" + " name=" + author.getName() + " ,email=" + author.getEmail() + ", gender=" + author.getGender() +"]"+ ", price=" + price + ", qty=" + qty + "]";
	}
	public static void main(String[] args) {
		// Construct an author instance
		Author ahTeck = new Author("Tan Ah Teck", "ahteck@nowhere.com", 'm');
		System.out.println(ahTeck);  // Author's toString()

		Book dummyBook = new Book("Java for dummy", ahTeck, 19.95, 99);  // Test Book's Constructor
		System.out.println(dummyBook);  // Test Book's toString()

		// Test Getters and Setters
		dummyBook.setPrice(29.95);
		dummyBook.setQty(28);
		System.out.println("name is: " + dummyBook.getName());
		System.out.println("price is: " + dummyBook.getPrice());
		System.out.println("qty is: " + dummyBook.getQty());
		System.out.println("Author is: " + dummyBook.getAuthor());  // Author's toString()
		System.out.println("Author's name is: " + dummyBook.getAuthor().getName());
		System.out.println("Author's email is: " + dummyBook.getAuthor().getEmail());

		// Use an anonymous instance of Author to construct a Book instance
		Book anotherBook = new Book("more Java", 
		      new Author("Paul Tan", "paul@somewhere.com", 'm'), 29.95);
		System.out.println(anotherBook);  // toString()
	}
	

}
