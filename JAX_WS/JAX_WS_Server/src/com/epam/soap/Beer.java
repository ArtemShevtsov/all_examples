package com.epam.soap;

//@XmlRootElement
public class Beer {

	private String name;
	private Long price;
	private BeerType type;

	public Beer(String name, Long price, BeerType type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public Beer() {
		// MUST HAVE!
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public BeerType getType() {
		return type;
	}

	public void setType(BeerType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Here is your bottle of " + this.name + " " + this.type;
	}

}
