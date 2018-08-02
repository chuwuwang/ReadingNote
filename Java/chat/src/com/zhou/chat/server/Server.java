package com.zhou.chat.server;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel mServerPanel; // 服务器信息面板
	private JPanel mUserPanel;	 // 在线用户列表面板
	
	private JLabel mServerStateLabel; // 服务器状态
	private JLabel mStopLabel;		  // 停止	
	private JLabel mPortLabel;		  // 端口号
	private JTextField mPortField;    // 端口号文本框
	
	private JButton mStartServerBtn; // 启动服务器按钮
	private JScrollPane mScrollPane; // 滚动条
	private JTextArea mUserListArea; // 在线用户列表文本框
	
	public static void main(String[] args) {
		new Server();
	}
	
	public Server() {
		initComponents();
	}
	
	private void initComponents() {
		mServerStateLabel = new JLabel();
		mStopLabel = new JLabel();
		mPortLabel = new JLabel();
		
		mServerPanel = new JPanel();
		mUserPanel = new JPanel();
		
		mStartServerBtn = new JButton();
		mScrollPane = new JScrollPane();
		mUserListArea = new JTextArea();
		mPortField = new JTextField();
		
		mServerPanel.setBorder(BorderFactory.createTitledBorder("服务器信息"));
		mUserPanel.setBorder(BorderFactory.createTitledBorder("在线用户列表"));
		
		mPortField.setText("5000");
		
		mServerStateLabel.setText("服务器状态");
		mStopLabel.setText("停止");
		mStopLabel.setForeground(new Color(204, 0, 51));
		mPortLabel.setText("端口号");
		
		mStartServerBtn.setText("启动服务器");
		
		mServerPanel.add(mServerStateLabel);
		mServerPanel.add(mStopLabel);
		mServerPanel.add(mPortLabel);
		mServerPanel.add(mPortField);
		mServerPanel.add(mStartServerBtn);
		
		mUserListArea.setEditable(false); // 不允许用户手动修改在线用户列表
		mUserListArea.setRows(20);
		mUserListArea.setColumns(30);
		mUserListArea.setForeground(new Color(0, 51, 204));
		
		mScrollPane.setViewportView(mUserListArea); // 将JTextArea放置到JScrollPane中
		mUserPanel.add(mScrollPane);

		this.getContentPane().add(mServerPanel, BorderLayout.NORTH);
		this.getContentPane().add(mUserPanel, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
}
