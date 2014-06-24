package com.example.test.app;

import java.io.Serializable;
import java.util.ArrayList;

public class CommunicationBean implements Serializable {

	private String command;
	private ArrayList data;

    public CommunicationBean(){

    }
	public CommunicationBean(String command, ArrayList data){
	    this.command = command;
        this.data = data;
	}
	
	public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    
    public ArrayList getData() {
        return data;
    }
    public void setData(ArrayList data) {
        this.data = data;
    }
}
