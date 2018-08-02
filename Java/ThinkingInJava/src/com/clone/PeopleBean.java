package com.clone;

public class PeopleBean implements Cloneable {

	public String vipId;
	public InfoBean infoBean;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		PeopleBean clone = (PeopleBean) super.clone();
		InfoBean ib = (InfoBean) infoBean.clone();
		clone.infoBean = ib;
		return clone;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		PeopleBean bean = new PeopleBean();
		InfoBean infoBean = new InfoBean();
		infoBean.age = 15;
		infoBean.name = "lisi";
		bean.infoBean = infoBean;
		bean.vipId = "123456";
		PeopleBean clone = (PeopleBean) bean.clone();
		System.out.println(bean.toString());
		System.out.println(clone.toString());
	}

	@Override
	public String toString() {
		return "PeopleBean [vipId=" + vipId + ", infoBean=" + infoBean + "]";
	}

}
