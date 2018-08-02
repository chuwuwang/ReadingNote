package com.zhou.chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.zhou.chat.entity.User;
import com.zhou.chat.utils.DiskFileUtils;
import com.zhou.chat.utils.JFrameUtils;
import com.zhou.chat.widget.ImagePanel;

public class Client extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel mLoginPanel; 	// 登录面板
	private JLabel mUserNameLabel;  // 用户名
	private JLabel mServerLabel;    // 服务器
	private JLabel mPortLabel;      // 端口号
	private JTextField mUserNameField;   // 用户名文本框
	private JTextField mServerField; 	 // 服务器文本框
	private JTextField mPortField;       // 端口号文本框
	private JButton mLoginBtn; 		// 登录按钮
	private JLabel mRegisterLabel;  // 注册账号

	public static void main(String[] args) {
		new Client();
	}

	public Client() {
		initComponents();
	}

	private void initComponents() {
		Font songFont = new Font("新宋体", Font.BOLD, 14);
		Font flexFont = new Font("flexibl", Font.PLAIN, 12);
		Color white = Color.decode("#ffffff");
		Color blue = Color.decode("#3cc3f5");
		Color gray = Color.decode("#ebf2f9");
		
		mLoginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		mUserNameLabel = new JLabel();
		mServerLabel = new JLabel();
		mPortLabel = new JLabel();
		mUserNameField = new JTextField(15);
		mServerField = new JTextField(15);
		mPortField = new JTextField(15);
		mLoginBtn = new JButton();
		mRegisterLabel = new JLabel();
		
		mLoginPanel.setBackground(gray);
		initTopPicture();
		
		// 用户名
		JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		mUserNameLabel.setText("用户名");
		mUserNameLabel.setFont(songFont);
		mUserNameField.setText("leeshenzhou");
		mUserNameField.setFont(flexFont);
		mUserNameField.setPreferredSize(new Dimension(0, 25));
		userPanel.add(mUserNameLabel);
		userPanel.add(mUserNameField);
		userPanel.setBackground(gray);
		userPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 0, 30));
		mLoginPanel.add(userPanel);
		
		// 服务器
		JPanel serverPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		mServerLabel.setText("服务器");
		mServerLabel.setFont(songFont);
		mServerField.setText("localhost");
		mServerField.setFont(flexFont);
		mServerField.setPreferredSize(new Dimension(0, 25));
		serverPanel.add(mServerLabel);
		serverPanel.add(mServerField);
		serverPanel.setBackground(gray);
		serverPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 0, 30));
		mLoginPanel.add(serverPanel);
		
		// 端口号
		JPanel portPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		mPortLabel.setText("端口号");
		mPortLabel.setFont(songFont);
		mPortField.setText("5000");
		mPortField.setFont(flexFont);
		mPortField.setPreferredSize(new Dimension(0, 25));
		portPanel.add(mPortLabel);
		portPanel.add(mPortField);
		portPanel.setBackground(gray);
		portPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 0, 30));
		mLoginPanel.add(portPanel);
		
		// 登录按钮
		JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		mLoginBtn.setText("登录");
		mLoginBtn.setFont(songFont);
		mLoginBtn.setBackground(blue);
		mLoginBtn.setForeground(white);
		mLoginBtn.setPreferredSize(new Dimension(240, 30));
		loginPanel.add(mLoginBtn);
		loginPanel.setBackground(gray);
		loginPanel.setBorder(BorderFactory.createEmptyBorder(15, 80, 0, 80));
		mLoginPanel.add(loginPanel);
		
		// 注册账号
		JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
		registerPanel.setPreferredSize(new Dimension(450, 20));
		mRegisterLabel.setText("注册账号");
		mRegisterLabel.setFont(songFont);
		mRegisterLabel.setForeground(blue);
		registerPanel.add(mRegisterLabel);
		registerPanel.setBackground(gray);
		mLoginPanel.add(registerPanel);
		
		initEvent();
		
		this.getContentPane().add(mLoginPanel);
		// 用户单击窗口的关闭按钮时程序执行的操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 父窗口调到屏幕最前面
		// this.setAlwaysOnTop(true);
		// 窗口大小是否可以改变
		this.setResizable(false);
		this.setSize(450, 350);
		// 去除标题栏
		this.setUndecorated(true);
		this.setLocation(JFrameUtils.getCenterLocation(this));
		this.setVisible(true);

	}
	
	private void initEvent() {
		mLoginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				new ChatClient();
				List<User> list = new ArrayList<User>();
				User u1 = new User();
				u1.setId(System.currentTimeMillis());
				u1.setName("leeshenzhou");
				list.add(u1);
				User u2 = new User();
				u2.setId(System.currentTimeMillis());
				u2.setName("hekai");
				list.add(u2);
				DiskFileUtils.writeUserRepertory2Disk(list);
			}
		});
		
		mRegisterLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				List<User> list = DiskFileUtils.readUserRepertory2Disk();
				for (User u : list) {
					System.out.println(u.toString());
				}
			}
			
		});
		
	}

	/**
	 * 初始化顶部图片
	 */
	private void initTopPicture() {
		Image bgImage = Toolkit.getDefaultToolkit().getImage("images/bg.png");
		ImagePanel bgPanel = new ImagePanel(bgImage);
		bgPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		bgPanel.setPreferredSize(new Dimension(450, 160));
		mLoginPanel.add(bgPanel, BorderLayout.NORTH);
		
		Image xImage = Toolkit.getDefaultToolkit().getImage("images/icon_delete.png");
		ImagePanel xPanel = new ImagePanel(xImage);
		// 设置背景透明
		xPanel.setOpaque(false);
		xPanel.setPreferredSize(new Dimension(20, 20));
		bgPanel.add(xPanel);
		xPanel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			
		});
	}
	

}
