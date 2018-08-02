package com.zhou.chat.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel mChatInfoPanel; 	  		// 聊天信息面板
	private JScrollPane mMessageScrollPane; // 消息滚动条
	private JTextArea mMessageArea;	  		// 消息区域
	
	private JPanel mUserListPanel;	     // 用户列表面板
	private JScrollPane mUserScrollPane; //用户列表滚动条
	private JTextArea mUserArea;		 // 用户区域
	
	private JPanel mEditMessagePanel; // 编辑消息面板
	private JButton mSendMessageBtn;  // 发送消息按钮
	private JButton mResetBtn;		  // 清屏按钮
	private JTextField mEditField;
	
	public static void main(String[] args) {
		new ChatClient();
	}

	public ChatClient() {
		initComponents();
	}

	private void initComponents() {
		mChatInfoPanel = new JPanel();
		mUserListPanel = new JPanel();
		mEditMessagePanel = new JPanel();

		mSendMessageBtn = new JButton();
		mResetBtn = new JButton();

		mMessageScrollPane = new JScrollPane();
		mUserScrollPane = new JScrollPane();

		mMessageArea = new JTextArea();
		mUserArea = new JTextArea();
		mEditField = new JTextField(20);

		mChatInfoPanel.setBorder(BorderFactory.createTitledBorder("聊天室信息"));
		mUserListPanel.setBorder(BorderFactory.createTitledBorder("在线用户列表"));
		mMessageArea.setColumns(30);
		mMessageArea.setRows(25);
		mMessageArea.setEditable(false);
		mUserArea.setColumns(20);
		mUserArea.setRows(25);
		mUserArea.setEditable(false);

		mEditMessagePanel.add(mEditField);
		mEditMessagePanel.add(mSendMessageBtn);
		mEditMessagePanel.add(mResetBtn);

		mChatInfoPanel.setLayout(new BorderLayout());
		mChatInfoPanel.add(mMessageScrollPane, BorderLayout.NORTH);
		mChatInfoPanel.add(mEditMessagePanel, BorderLayout.SOUTH);
		mMessageScrollPane.setViewportView(mMessageArea);

		mUserListPanel.add(mUserScrollPane);
		mUserScrollPane.setViewportView(mUserArea);

		mSendMessageBtn.setText("发送");
		mResetBtn.setText("清屏");
		mSendMessageBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		mResetBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		this.setLayout(new FlowLayout());
		this.getContentPane().add(mChatInfoPanel);
		this.getContentPane().add(mUserListPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("聊天室");
		this.setResizable(false);
		this.pack();
		this.setVisible(true);

	}

}
