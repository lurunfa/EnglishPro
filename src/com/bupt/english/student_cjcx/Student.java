package com.bupt.english.student_cjcx;
/*
 * 学生基本类
 */

public class Student {
	public String stuname,stuid,grade,evaluate,task,taskId,sign;
	public void setstuname(String stuname){
		this.stuname = stuname;
	}
	public void setsign(String sign){
		this.sign = sign;
	}
	public String getsign(){
		return sign;
	}
	public void setstuid(String stuname){
		this.stuid = stuname;
	}
	public void setgrade(String stuname){
		this.grade = stuname;
	}
	public void setevaluate(String stuname){
		this.evaluate = stuname;
	}
	public void settask(String stuname){
		this.task = stuname;
	}
	public void settaskId(String stuname){
		this.taskId = stuname;
	}
	public String getstuid(){
		return stuid;
	}
	public String getstuname(){
		return stuname;
	}
	public String gettask(){
		return task;
	}
	public String gettaskId(){
		return taskId;
	}
	public String getevaluate(){
		return evaluate;
	}
	public String getgrade(){
		return grade;
	}
}
