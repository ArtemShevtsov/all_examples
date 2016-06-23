package com.company;/* HttpsHello.java
 - Copyright (c) 2014, HerongYang.com, All Rights Reserved.
 */
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.security.*;
import java.security.cert.CertificateException;
import javax.net.ssl.*;

public class Main {
    public static final String ksName = "C:\\Program Files\\Java\\jdk1.8.0_74\\bin\\keystore.jks";
    public static final String path = "D:\\Projects\\examples\\HttpsServer\\index.html";
    public static final String ksPass = "password";
    public static final String ctPass = "password";
    public static final int port = 9443;

    public static void main(String[] args)
            throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException,
            UnrecoverableKeyException, KeyManagementException {


        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(ksName), ksPass.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(keyStore, ctPass.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
        SSLServerSocket sslServerSocket = (SSLServerSocket) ssf.createServerSocket(port);

        System.out.println("Server started:");
        printServerSocketInfo(sslServerSocket);

        while (true) {
            try {
                // Listening to the port
                SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
//                sslSocket.getHandshakeSession();
                sslSocket.startHandshake();
                printSocketInfo(sslSocket);
                // File
                FileReader fr=new FileReader(path);
                BufferedReader br = new BufferedReader(fr);
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(sslSocket.getOutputStream()) );
                bufferedWriter.write("HTTP/1.0 200 OK");
                bufferedWriter.newLine();
                bufferedWriter.write("Content-Type: text/html");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                String contentLine = br.readLine();
                while(contentLine != null)
                {
                    bufferedWriter.write(contentLine);
                    contentLine = br.readLine();
                }
                bufferedWriter.flush();
                bufferedWriter.close();
                sslSocket.close();

                Thread.currentThread().sleep(500L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

/*
        HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(port), 0);
        httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext){
            public void configure(HttpsParameters params) {
                try {
                    // initialise the SSL context
                    SSLContext c = SSLContext.getDefault();
                    SSLEngine engine = c.createSSLEngine();
                    params.setNeedClientAuth(false);
                    params.setCipherSuites(engine.getEnabledCipherSuites());
                    params.setProtocols(engine.getEnabledProtocols());
                    // get the default parameters
                    SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
                    params.setSSLParameters(defaultSSLParameters);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Failed to create HTTPS server");
                }
            }
        });
*/
    }
    private static void printSocketInfo(SSLSocket s) {
        System.out.println("Socket class: "+s.getClass());
        System.out.println("   Remote address = "
                +s.getInetAddress().toString());
        System.out.println("   Remote port = "+s.getPort());
        System.out.println("   Local socket address = "
                +s.getLocalSocketAddress().toString());
        System.out.println("   Local address = "
                +s.getLocalAddress().toString());
        System.out.println("   Local port = "+s.getLocalPort());
        System.out.println("   Need client authentication = "
                +s.getNeedClientAuth());
        SSLSession ss = s.getSession();
        System.out.println("   Cipher suite = "+ss.getCipherSuite());
        System.out.println("   Protocol = "+ss.getProtocol());
    }
    private static void printServerSocketInfo(SSLServerSocket s) {
//        System.out.printf("Server socket class:\t%s\n\tSocket address\t");
        System.out.println("Server socket class: "+s.getClass());
        System.out.println("   Socket address = "
                +s.getInetAddress().toString());
        System.out.println("   Socket port = "
                +s.getLocalPort());
        System.out.println("   Need client authentication = "
                +s.getNeedClientAuth());
        System.out.println("   Want client authentication = "
                +s.getWantClientAuth());
        System.out.println("   Use client mode = "
                +s.getUseClientMode());
    }
}
