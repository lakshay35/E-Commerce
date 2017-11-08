package object;
import persistent.PromotionDA;

public class Promotion {
	private int promoID;
	private String name;
	private double percent;
	private String expiration;
	
	public Promotion(int promoID, String name, double percent, String expiration) {
		setPromoID(promoID);
		setName(name);
		setPercent(percent);
		setExpiration(expiration);
	}
	
	public int addPromo() {
		int value = PromotionDA.addPromoToDA(promoID, name, percent, expiration);
		return value;
	}
	
	public void setPromoID(int promoID) {
		this.promoID = promoID;
	}
	
	public int getPromoID() {
		return promoID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPercent() {
		return percent;
	}
	
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	public String getExpiration() {
		return expiration;
	}
	
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
}
