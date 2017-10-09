package com.shenhua.outer.security.report.bean;

import java.util.List;

/**
 * Created by shenhua on 2017/9/29.
 * Email shenhuanet@126.com
 */
public class OneDayChart {

    /**
     * success : true
     * msg : 获取成功
     * data : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * operateCode : 0
     */

    private boolean success;
    private String msg;
    private int operateCode;
    private List<Integer> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(int operateCode) {
        this.operateCode = operateCode;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
