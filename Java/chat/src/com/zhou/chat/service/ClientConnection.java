package com.zhou.chat.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.zhou.chat.client.ChatClient;
import com.zhou.chat.client.Client;

public class ClientConnection extends Thread {

	private String userName;
	private String hostAddress;
	private int port;

	private Client mClient;
	private ChatClient mChatClient;

	private Socket socket;
	private InputStream is;
	private OutputStream os;

	public ClientConnection(String userName, String hostAddress, int port,
			Client client) {
		super();
		this.userName = userName;
		this.hostAddress = hostAddress;
		this.port = port;
		this.mClient = client;
		
		connect2Server();
	}
	
	// 连接服务器
	private void connect2Server() {
		try {
			socket = new Socket(hostAddress, port);
			is = socket.getInputStream();
			os = socket.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean login() {
		return false;
	}
	
	private void ss() {
		
	}
	
	
}
