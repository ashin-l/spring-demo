package com.example.springdemo.common;

public class Result {

  private Integer code;
  private String msg;
  private Object data;

  public static Result ok() {
    Result result = new Result();
    result.setCode(0);
    result.setMsg("操作成功");
    return result;
  }

  public static Result ok(Object data) {
    Result result = ok();
    result.setData(data);
    return result;
  }

  public static Result error() {
    Result result = new Result();
    result.setCode(500);
    result.setMsg("操作失败");
    return result;
  }

  public static Result error(String msg) {
    Result result = new Result();
    result.setCode(500);
    result.setMsg(msg);
    return result;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}