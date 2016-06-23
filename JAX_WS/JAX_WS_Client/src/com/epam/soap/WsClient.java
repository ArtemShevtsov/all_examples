package com.epam.soap;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class WsClient {

    // use wsimport -keep http://localhost:1313/ws/getBeer?wsdl

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:1313/ws/getBeer?wsdl");
            QName qname = new QName("http://soap.epam.com/", "WsServiceImplService");
            // Service service = WsServiceImplService.create(url, qname);
            // WsService port = service.getPort(WsService.class);
            // Beer beer = port.getBeer("warsteiner");
            // System.out.println(beer.getName());
            // System.out.println(beer.getType());
            // System.out.println(beer.getPrice());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handleAttachements() throws SOAPException {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();
        SOAPPart soapPart = message.getSOAPPart();
        SOAPHeader header = message.getSOAPHeader();
        SOAPBody body = message.getSOAPBody();

        AttachmentPart attachment = message.createAttachmentPart();
        attachment.setContent("some content", "text/plain");
        attachment.setContentId("update_address");
        message.addAttachmentPart(attachment);

        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = soapConnectionFactory.createConnection();

        SOAPMessage response = connection.call(message, "http://soap.epam.com/getBeer");
        connection.close();

        SOAPBody soapBody = response.getSOAPBody();
        java.util.Iterator iterator = soapBody.getChildElements();
        SOAPBodyElement bodyElement = (SOAPBodyElement)iterator.next();
        String value = bodyElement.getValue();
    }
}