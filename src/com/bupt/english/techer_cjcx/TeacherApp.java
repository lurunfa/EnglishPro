package com.bupt.english.techer_cjcx;

public class TeacherApp {
	// cjcx
	String text, hint;
	String backData;
	String backId;
	int imageId_CJCX;

	public void settext(String text) {
		this.text = text;
	}

	public void setimageId_CJCX(int imageId) {
		imageId_CJCX = imageId;
	}

	public String gettext() {
		return text;
	}

	public int getImageId_CJCX() {
		return imageId_CJCX;
	}

	public String getHint() {
		// TODO Auto-generated method stub
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getBackData() {
		// TODO Auto-generated method stub
		return backData;
	}

	public void setBackData(String backData) {
		this.backData = backData;
	}

	public String getBackId() {
		// TODO Auto-generated method stub
		return backId;
	}

	public void setBackId(String backId) {
		this.backId = backId;
	}

	//
	String name, stuid, taskId, pptpath, wordpath, audiopath, videopath, sign,evaluate;
	String flag = "1";
	public void setflag(String flag) {
		this.flag = flag;
	}

	public String getflag() {
		return flag;
	}
	
	public void setevaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getevaluate() {
		return evaluate;
	}
	
	public void setsign(String sign) {
		this.sign = sign;
	}

	public String getsign() {
		return sign;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setstuid(String stuid) {
		this.stuid = stuid;
	}

	public void settaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setpptpath(String pptpath) {
		this.pptpath = pptpath;
	}

	public void setvideopath(String videopath) {
		this.videopath = videopath;
	}

	public void setwordpath(String wordpath) {
		this.wordpath = wordpath;
	}

	public void setaudiopath(String audiopath) {
		this.audiopath = audiopath;
	}

	public String getname() {
		return name;
	}

	public String getstuid() {
		return stuid;
	}

	public String gettaskId() {
		return taskId;
	}

	public String getpptpath() {
		return pptpath;
	}

	public String getwordpath() {
		return wordpath;
	}

	public String getaudiopath() {
		return audiopath;
	}

	public String getvideopath() {
		return videopath;
	}
	//
	String data,datapath;
	public void setdata(String data){
		this.data = data;
	}
	public void setdatapath(String datapath){
		this.datapath = datapath;
	}
	public String getdata(){
		return data;
	}
	public String getdatapath(){
		return datapath;
	}
}
