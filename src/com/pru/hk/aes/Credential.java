package com.pru.hk.aes;

/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/




public class Credential {

  private String userId;
  private String password;
  private String url;

  public Credential(String userId, String password, String url) {
    this.userId = userId;
    this.password = password;
    this.setUrl(url);
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}
}
