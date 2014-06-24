package test.hib;

import java.awt.Button;
import java.net.*;
import java.util.*;

import org.osgi.framework.Bundle;
import org.quickconnectfamily.json.*;


public class SimpleExampleServer {
    
	public static void main(String[] args) {
		
		try {
			//a socket opened on the specified port
			ServerSocket aListeningSocket = new ServerSocket(80);
			
			while(true){
				//wait for a connection
				System.out.println("Waiting for client connection request.");
				Socket clientSocket = 
                aListeningSocket.accept();
				//setup the JSON streams for later use.
				JSONInputStream inFromClient = 
                new JSONInputStream(clientSocket.getInputStream());
				JSONOutputStream outToClient = 
                new JSONOutputStream(clientSocket.getOutputStream());
				//read until the client closes 
				//the connection.
				while(true){
					try{
					System.out.println("Waiting for a message from the server.");
					HashMap aMap = 
                    (HashMap)inFromClient.readObject();
					System.out.println("Just got:"
                                       +aMap+" from client");
					CommunicationBean aResponse = new CommunicationBean("Done",(ArrayList)aMap.get("data"));
					outToClient.writeObject(aResponse);
					}catch(Exception e){
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
}