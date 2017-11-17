package object;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import entity.IBook;
import persistent.BookDA;
import persistent.DbAccessImpl;

public class Book implements IBook{
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
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Book() {
		
	}
	
	public Book(int isbn, String cat, String author, String title, int edition, 
			String publisher, int year, int thresh, int quantity, Double buyingPrice, Double 
			sellingPrice, String url, String description)
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
		setDescription(description);
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
				buyingPrice, sellingPrice, description);
		return value;
	}
	
	public int editBook() {
		int value = BookDA.editBookDA(isbn, category, author, title, edition, publisher, year, quantity, threshold, picture,
				buyingPrice, sellingPrice, description);
		return value;
	}
	
	public int getBookInfo(int isbn) {
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = BookDA.getBookInfo(isbn, con);
		int check = 0;
		try {
			if (set.next())
			{
				setIsbn(set.getInt("isbn"));
				setCategory(set.getString("category"));
				setAuthor(set.getString("authorName"));
				setTitle(set.getString("title"));
				setEdition(set.getInt("edition"));
				setPublisher(set.getString("publisher"));
				setYear(set.getInt("publicationYear"));
				setQuantity(set.getInt("qtyInStock"));
				setBuyingPrice(set.getDouble("buyingPrice"));
				setSellingPrice(set.getDouble("sellingPrice"));
				setPicture(set.getString("picture"));
				setThreshold(set.getInt("minThreshold"));
				setDescription(set.getString("description"));
				check = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(con);
		return check;
	}
	
	public void printBook() {
		System.out.println(title);
		System.out.println(author);
		System.out.println(description);
		System.out.println(category);
		System.out.println(isbn);
		System.out.println(publisher);
		System.out.println(year);
		System.out.println(edition);
		System.out.println(threshold);
		System.out.println(quantity);
		System.out.println(buyingPrice);
		System.out.println(sellingPrice);
		System.out.println(picture);
	}
}
