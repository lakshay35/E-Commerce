package object;

import persistent.BookDA;

public class Book {
	private int isbn;
	private String category;
	private String author;
	private String title;
	private int edition;
	private String publisher;
	private int year;
	private int quantity;
	private int threshold;
	private String picture;
	private Double buyingPrice;
	private Double sellingPrice;
	
	public Book(int isbn, String cat, String author, String title, int edition, 
			String publisher, int year, int thresh, int quantity, Double buyingPrice, Double 
			sellingPrice, String url)
	{
		setIsbn(isbn);
		setCategory(cat);
		setAuthor(author);
		setTitle(title);
		setEdition(edition);
		setPublisher(publisher);
		setYear(year);
		setQuantity(quantity);
		setBuyingPrice(buyingPrice);
		setSellingPrice(sellingPrice);
		setPicture(url);
		setThreshold(thresh);
	}
	
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(Double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public Double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int addBook() {
		int value = BookDA.addBookToDA(isbn, category, author, title, edition, publisher, year, quantity, threshold, picture,
				buyingPrice, sellingPrice);
		return value;
	}
	
	
}
