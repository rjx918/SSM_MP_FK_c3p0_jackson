package com.rj.bd.users.domain;
import java.util.Date;


public class Chaoqifakuan{
	private String chaoqifakuanid;
	private String jieyueid;
	private String guanliyuanid;
	private String yujifakuanjine;
	private String shijifakuanjine;
	public String getChaoqifakuanid() {
		return chaoqifakuanid;
	}
	public void setChaoqifakuanid(String chaoqifakuanid) {
		this.chaoqifakuanid = chaoqifakuanid;
	}
	public String getJieyueid() {
		return jieyueid;
	}
	public void setJieyueid(String jieyueid) {
		this.jieyueid = jieyueid;
	}
	public String getGuanliyuanid() {
		return guanliyuanid;
	}
	public void setGuanliyuanid(String guanliyuanid) {
		this.guanliyuanid = guanliyuanid;
	}
	public String getYujifakuanjine() {
		return yujifakuanjine;
	}
	public void setYujifakuanjine(String yujifakuanjine) {
		this.yujifakuanjine = yujifakuanjine;
	}
	public String getShijifakuanjine() {
		return shijifakuanjine;
	}
	public void setShijifakuanjine(String shijifakuanjine) {
		this.shijifakuanjine = shijifakuanjine;
	}
	public Chaoqifakuan(String chaoqifakuanid, String jieyueid, String guanliyuanid, String yujifakuanjine,
			String shijifakuanjine) {
		super();
		this.chaoqifakuanid = chaoqifakuanid;
		this.jieyueid = jieyueid;
		this.guanliyuanid = guanliyuanid;
		this.yujifakuanjine = yujifakuanjine;
		this.shijifakuanjine = shijifakuanjine;
	}
	public Chaoqifakuan() {
		super();
	}
	@Override
	public String toString() {
		return "Chaoqifakuan [chaoqifakuanid=" + chaoqifakuanid + ", jieyueid=" + jieyueid + ", guanliyuanid="
				+ guanliyuanid + ", yujifakuanjine=" + yujifakuanjine + ", shijifakuanjine=" + shijifakuanjine + "]";
	}
	
	
	
	
	
}

