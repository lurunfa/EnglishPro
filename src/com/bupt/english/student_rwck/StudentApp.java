package com.bupt.english.student_rwck;
/*
 * 学生查看任务基本类
 */
public class StudentApp {
	// 学生查看任务
	String taskId, taskName, teacherId, teacherName, classNum, className,
			partner, beginDate, deadline, flag;
	String video, voice, ppt, word,type;
	
	public void settype(String type) {
		this.type = type;
	}

	public String gettype() {
		return type;
	}
	public void setvideo(String video) {
		this.video = video;
	}

	public String getvideo() {
		return video;
	}

	public void setword(String word) {
		this.word = word;
	}

	public String getword() {
		return word;
	}

	public void setvoice(String voice) {
		this.voice = voice;
	}

	public String getvoice() {
		return voice;
	}

	public void setppt(String ppt) {
		this.ppt = ppt;
	}

	public String getppt() {
		return ppt;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;

	}

	public void settaskId(String taskId) {
		this.taskId = taskId;
	}

	public void settaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public String getClassNum() {
		return classNum;
	}

	public String getClassName() {
		return className;
	}

	public String getPartnet() {
		return partner;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public String getDeadline() {
		return deadline;
	}
	//
	String askText,hint;
	int imageId;
	public void setImage(int imageId){
		this.imageId = imageId;
	}
	public void setAskText(String text){
		askText = text;
	}
	public int getImageId(){
		return imageId;
	}
	public String getAskText(){
		return askText;
	}
	public void setHint(String hint){
		this.hint = hint ;
	}
	public String getHint(){
		return hint;
	}
}
