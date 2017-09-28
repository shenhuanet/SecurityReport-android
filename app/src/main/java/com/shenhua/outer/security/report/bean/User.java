package com.shenhua.outer.security.report.bean;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public class User {

    /**
     * success : true
     * msg : 登录成功
     * data : {"id":3,"name":"系统管理员","email":"it@yuxiao119.com","tel":"test","password":null,"salt":"ec98fbb1db3f86de589d10ad35901902","addTime":1468068822000,"modifyTime":1506418120000,"lastTime":1506486555648,"ip":null,"status":0,"identity":null,"openId":"o_AESv95xOER4TvUbuT0zKr8QPlw","previewTime":1468153614000,"notifyTime":1494862405000,"notifyLevel":100,"data":null,"roleId":1,"roleName":"系统管理员"}
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
         * id : 3
         * name : 系统管理员
         * email : it@yuxiao119.com
         * tel : test
         * password : null
         * salt : ec98fbb1db3f86de589d10ad35901902
         * addTime : 1468068822000
         * modifyTime : 1506418120000
         * lastTime : 1506486555648
         * ip : null
         * status : 0
         * identity : null
         * openId : o_AESv95xOER4TvUbuT0zKr8QPlw
         * previewTime : 1468153614000
         * notifyTime : 1494862405000
         * notifyLevel : 100
         * data : null
         * roleId : 1
         * roleName : 系统管理员
         */

        private int id;
        private String name;
        private String email;
        private String tel;
        private Object password;
        private String salt;
        private long addTime;
        private long modifyTime;
        private long lastTime;
        private Object ip;
        private int status;
        private Object identity;
        private String openId;
        private long previewTime;
        private long notifyTime;
        private int notifyLevel;
        private Object data;
        private int roleId;
        private String roleName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public long getLastTime() {
            return lastTime;
        }

        public void setLastTime(long lastTime) {
            this.lastTime = lastTime;
        }

        public Object getIp() {
            return ip;
        }

        public void setIp(Object ip) {
            this.ip = ip;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getIdentity() {
            return identity;
        }

        public void setIdentity(Object identity) {
            this.identity = identity;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public long getPreviewTime() {
            return previewTime;
        }

        public void setPreviewTime(long previewTime) {
            this.previewTime = previewTime;
        }

        public long getNotifyTime() {
            return notifyTime;
        }

        public void setNotifyTime(long notifyTime) {
            this.notifyTime = notifyTime;
        }

        public int getNotifyLevel() {
            return notifyLevel;
        }

        public void setNotifyLevel(int notifyLevel) {
            this.notifyLevel = notifyLevel;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }
}
