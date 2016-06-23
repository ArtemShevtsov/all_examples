package com.company;

import com.company.exception.MyException;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Artem_Shevtsov on 3/16/2016.
 */
public class MainEmbeddedJetty extends AbstractHandler {
    public static final String ksName = "C:\\Program Files\\Java\\jdk1.8.0_74\\bin\\keystore.jks";
    public static final String path = "D:\\Projects\\examples\\HttpsServer\\index.html";
    public static final String ksPass = "password";
    public static final String ctPass = "password";
    public static final int port = 9443;

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        FileReader fr=new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        PrintWriter responseWriter = response.getWriter();

        String contentLine = br.readLine();
        while(contentLine != null)
        {
            responseWriter.write(contentLine);
            contentLine = br.readLine();
        }
        baseRequest.setHandled(true);
    }

    public static void main(String... args) throws Exception {
        String text = "qqq";
        throwEx();
        String text2 = "www";

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(ksName);
        sslContextFactory.setKeyStorePassword(ksPass);
        sslContextFactory.setKeyManagerPassword(ctPass);

        SslSocketConnector sslSocketConnector = new SslSocketConnector(sslContextFactory);
        sslSocketConnector.setPort(port);

        Server server = new Server();
        server.setConnectors(new Connector[]{sslSocketConnector});
        server.setHandler(new MainEmbeddedJetty());
        server.start();
        server.join();
    }

    public static void throwEx() throws MyException {
        String e = "wwwttt";
        System.out.println(e);
        throw new MyException();
    }
}
