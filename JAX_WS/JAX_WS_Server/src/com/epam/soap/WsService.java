package com.epam.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding
public interface WsService {

	@WebMethod
	// @WebResult(partName = "output")
	// @WebParam(partName = "input")
	Beer getBeer(String name);
}
