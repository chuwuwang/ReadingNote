package com.zhou.chat.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class JFrameUtils {
	
	/**
	 * 获取中间位置
	 */
	public static Point getCenterLocation(JFrame frame) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int width = (int) (screenSize.getWidth() - frame.getWidth()) / 2;
		int height = (int) (screenSize.getHeight() - frame.getHeight()) / 2;
		Point point = new Point(width, height);
		return point;
	}

}
