package com.bupt.english.main;

public class AppClass {
	// teacherFZBZ
	private String backData, hint;
	private int imageId;
	public static String  path = "http://10.204.24.74:8011/";
	public static String ip = "10.8.82.45";
public String getpath(){
	return path;
}
public void setPath(String path) {
	this.path = path;
}
	public void setBackData(String backData) {
		this.backData = backData;
	}

	public String getBackData() {
		return backData;
	}

	public void setimageId(int imageId) {
		this.imageId = imageId;
	}

	public int getimageId() {
		return imageId;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getHint() {
		return hint;
	}

	// 册
	private String bookname, bookNumber;

	public void setBookName(String bookname) {
		this.bookname = bookname;
	}

	public String getBookName() {
		return bookname;
	}

	public String getBookNum() {
		return bookNumber;
	}

	public void setBookNum(String bookNum) {

		this.bookNumber = bookNum;
	}

	// 单元
	public String unitId, unitName;

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitName() {
		return unitName;
	}

	// 任务
	private String taskTitle;
	private String taskContent;
	private String taskId;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	// 班级
	private String ClassNum;
	private String className;

	public String getClassName() {
		return className;

	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassNum() {
		return ClassNum;
	}

	public void setClassNum(String ClassNum) {
		this.ClassNum = ClassNum;
	}

	// 分组
	private String group, name, id;

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getgroup() {
		return group;
	}

	public void setgroup(String Group) {
		this.group = Group;
	}

	public String getname() {
		return name;
	}

	public void setname(String Name) {
		this.name = Name;
	}

}
