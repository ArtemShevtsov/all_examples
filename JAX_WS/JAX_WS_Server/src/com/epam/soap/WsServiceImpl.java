package com.epam.soap;

import javax.jws.WebService;

@WebService(endpointInterface = "com.epam.soap.WsService")
public class WsServiceImpl implements WsService {

	@Override
	public Beer getBeer(String name) {
	    Beer beer = new Beer();
	    beer.setName(name);
	    beer.setType(BeerType.PORTER);
	    beer.setPrice(20L);
		return beer;
	}

}
