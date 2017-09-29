package com.shenhua.outer.security.report.bean;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class WarningCount {


    /**
     * success : true
     * msg : 获取成功
     * data : {"unusual":"7","onLine":"0","all":"7","warning":"12"}
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
        /**
         * unusual : 7
         * onLine : 0
         * all : 7
         * warning : 12
         */

        private int unusual;
        private int onLine;
        private int all;
        private int warning;

        public int getUnusual() {
            return unusual;
        }

        public void setUnusual(int unusual) {
            this.unusual = unusual;
        }

        public int getOnLine() {
            return onLine;
        }

        public void setOnLine(int onLine) {
            this.onLine = onLine;
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getWarning() {
            return warning;
        }

        public void setWarning(int warning) {
            this.warning = warning;
        }
    }
}
