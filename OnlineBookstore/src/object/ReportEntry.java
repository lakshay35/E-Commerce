package object;

import java.util.Date;

public class ReportEntry {

  Date orderDate;
  int isbn;
  int qty;
  double total;
  String authorName;
  String title;

  public ReportEntry(Date orderDate, int isbn, int qty, double total, String authorName, String title) {
    this.orderDate = orderDate;
    this.isbn = isbn;
    this.qty = qty;
    this.total = total;
    this.authorName = authorName;
    this.title = title;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public int getIsbn() {
    return isbn;
  }

  public int getQty() {
    return qty;
  }

  public double getTotal() {
    return total;
  }

  public String getAuthorName() {
    return authorName;
  }

  public String getTitle() {
    return title;
  }


}
