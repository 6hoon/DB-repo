package kh.book2;

public class Books {
	private int book_id;
	private String title;
	private String publisher;
	private String year;
	private int price;

	
	
	public Books() {
		super();
	}

	public Books(int book_id, String title, String publisher, String year, int price) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.publisher = publisher;
		this.year = year;
		this.price = price;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[" + book_id + ", " + title + ", " + publisher + ", " + year
				+ ", " + price + "]";
	}

}
