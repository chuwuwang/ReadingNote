package com.zhou.prototype;

import java.util.ArrayList;

/**
 * 文档类型。是ConcretePrototype角色，而Cloneable是代表prototype角色
 * 
 * @author zhou
 *
 */
public class WordDocument implements Cloneable {

	private String text;
	private ArrayList<String> imageList = new ArrayList<>();

	public WordDocument() {
		System.out.println("--------document constructor--------");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object clone() {
		try {
			WordDocument doc = (WordDocument) super.clone();
			doc.text = this.text;
			doc.imageList = (ArrayList<String>) this.imageList.clone();
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<String> imageList) {
		this.imageList = imageList;
	}

	public void addImage(String image) {
		imageList.add(image);
	}

	/**
	 * 打印文档内容
	 */
	public void showDocument() {
		System.out.println("--------document start--------");
		System.out.println("Text : " + text);
		System.out.println("Images List : ");
		for (String name : imageList) {
			System.out.println("image name : " + name);
		}
		System.out.println("--------document end--------");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageList == null) ? 0 : imageList.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordDocument other = (WordDocument) obj;
		if (imageList == null) {
			if (other.imageList != null)
				return false;
		} else if (!imageList.equals(other.imageList))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}
