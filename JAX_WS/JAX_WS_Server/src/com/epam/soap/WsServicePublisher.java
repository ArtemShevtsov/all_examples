package com.epam.soap;

import javax.xml.ws.Endpoint;

public class WsServicePublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:1313/ws/getBeer", new WsServiceImpl());
	}

}
