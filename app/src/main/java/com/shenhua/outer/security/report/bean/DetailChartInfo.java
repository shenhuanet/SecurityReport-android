package com.shenhua.outer.security.report.bean;

import java.util.List;

/**
 * Created by shenhua on 2017-10-13-0013.
 * Email shenhuanet@126.com
 */
public class DetailChartInfo {
    /**
     * success : true
     * msg :
     * data : {"timeList":["10:27:39","10:27:43","10:27:47","10:27:48","10:27:49","10:27:50"],"dataList":["32","32","0","32","32","32"]}
     * operateCode : 0
     */

    private boolean success;
    private String msg;
    private DataBean data;
    private int operateCode;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(int operateCode) {
        this.operateCode = operateCode;
    }

    public static class DataBean {
        private List<String> timeList;
        private List<String> dataList;

        public List<String> getTimeList() {
            return timeList;
        }

        public void setTimeList(List<String> timeList) {
            this.timeList = timeList;
        }

        public List<String> getDataList() {
            return dataList;
        }

        public void setDataList(List<String> dataList) {
            this.dataList = dataList;
        }
    }
}
