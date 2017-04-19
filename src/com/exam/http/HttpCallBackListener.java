package com.exam.http;

public interface HttpCallBackListener {
	void onFinish(String response);
	void onError(Exception e);
}
