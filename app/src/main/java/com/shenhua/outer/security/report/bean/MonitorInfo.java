package com.shenhua.outer.security.report.bean;

import java.util.List;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class MonitorInfo {

    /**
     * success : true
     * msg : 获取成功
     * data : {"sensors":[{"id":3,"gatewayId":1,"monitoringId":36,"sensorType":3,"name":null,"no":0,"address":null,"syncTime":1506504220000,"warningTotal":1,"status":0,"readValue":"32","linkType":0,"rsAddress":1,"branch":0,"mfNo":null,"loopBack":1,"resetFlag":1,"addTime":1506080350000,"updateTime":1506502971000},{"id":2,"gatewayId":1,"monitoringId":36,"sensorType":2,"name":null,"no":0,"address":null,"syncTime":1506504276000,"warningTotal":3,"status":0,"readValue":"48","linkType":0,"rsAddress":1,"branch":0,"mfNo":null,"loopBack":1,"resetFlag":1,"addTime":1506080344000,"updateTime":1506504550000},{"id":1,"gatewayId":1,"monitoringId":36,"sensorType":1,"name":null,"no":0,"address":null,"syncTime":1506664653000,"warningTotal":1,"status":0,"readValue":"","linkType":0,"rsAddress":1,"branch":0,"mfNo":null,"loopBack":1,"resetFlag":1,"addTime":1506080330000,"updateTime":1506421190000}],"monitoring":{"id":36,"gatewayId":1,"name":"103室","address":null,"syncTime":null,"modifyTime":null,"warningState":0,"status":0,"no":1,"loopBack":null,"notifyState":1,"addTime":1505914765000,"updateTime":1505914801000}}
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
         * sensors : [{"id":3,"gatewayId":1,"monitoringId":36,"sensorType":3,"name":null,"no":0,"address":null,"syncTime":1506504220000,"warningTotal":1,"status":0,"readValue":"32","linkType":0,"rsAddress":1,"branch":0,"mfNo":null,"loopBack":1,"resetFlag":1,"addTime":1506080350000,"updateTime":1506502971000},{"id":2,"gatewayId":1,"monitoringId":36,"sensorType":2,"name":null,"no":0,"address":null,"syncTime":1506504276000,"warningTotal":3,"status":0,"readValue":"48","linkType":0,"rsAddress":1,"branch":0,"mfNo":null,"loopBack":1,"resetFlag":1,"addTime":1506080344000,"updateTime":1506504550000},{"id":1,"gatewayId":1,"monitoringId":36,"sensorType":1,"name":null,"no":0,"address":null,"syncTime":1506664653000,"warningTotal":1,"status":0,"readValue":"","linkType":0,"rsAddress":1,"branch":0,"mfNo":null,"loopBack":1,"resetFlag":1,"addTime":1506080330000,"updateTime":1506421190000}]
         * monitoring : {"id":36,"gatewayId":1,"name":"103室","address":null,"syncTime":null,"modifyTime":null,"warningState":0,"status":0,"no":1,"loopBack":null,"notifyState":1,"addTime":1505914765000,"updateTime":1505914801000}
         */

        private MonitoringBean monitoring;
        private List<SensorsBean> sensors;

        public MonitoringBean getMonitoring() {
            return monitoring;
        }

        public void setMonitoring(MonitoringBean monitoring) {
            this.monitoring = monitoring;
        }

        public List<SensorsBean> getSensors() {
            return sensors;
        }

        public void setSensors(List<SensorsBean> sensors) {
            this.sensors = sensors;
        }

        public static class MonitoringBean {
            /**
             * id : 36
             * gatewayId : 1
             * name : 103室
             * address : null
             * syncTime : null
             * modifyTime : null
             * warningState : 0
             * status : 0
             * no : 1
             * loopBack : null
             * notifyState : 1
             * addTime : 1505914765000
             * updateTime : 1505914801000
             */

            private int id;
            private int gatewayId;
            private String name;
            private Object address;
            private Object syncTime;
            private Object modifyTime;
            private int warningState;
            private int status;
            private int no;
            private Object loopBack;
            private int notifyState;
            private long addTime;
            private long updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGatewayId() {
                return gatewayId;
            }

            public void setGatewayId(int gatewayId) {
                this.gatewayId = gatewayId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getSyncTime() {
                return syncTime;
            }

            public void setSyncTime(Object syncTime) {
                this.syncTime = syncTime;
            }

            public Object getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(Object modifyTime) {
                this.modifyTime = modifyTime;
            }

            public int getWarningState() {
                return warningState;
            }

            public void setWarningState(int warningState) {
                this.warningState = warningState;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public Object getLoopBack() {
                return loopBack;
            }

            public void setLoopBack(Object loopBack) {
                this.loopBack = loopBack;
            }

            public int getNotifyState() {
                return notifyState;
            }

            public void setNotifyState(int notifyState) {
                this.notifyState = notifyState;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }
        }

        public static class SensorsBean {
            /**
             * id : 3
             * gatewayId : 1
             * monitoringId : 36
             * sensorType : 3
             * name : null
             * no : 0
             * address : null
             * syncTime : 1506504220000
             * warningTotal : 1
             * status : 0
             * readValue : 32
             * linkType : 0
             * rsAddress : 1
             * branch : 0
             * mfNo : null
             * loopBack : 1
             * resetFlag : 1
             * addTime : 1506080350000
             * updateTime : 1506502971000
             */

            private int id;
            private int gatewayId;
            private int monitoringId;
            private int sensorType;
            private Object name;
            private int no;
            private Object address;
            private long syncTime;
            private int warningTotal;
            private int status;
            private String readValue;
            private int linkType;
            private int rsAddress;
            private int branch;
            private Object mfNo;
            private int loopBack;
            private int resetFlag;
            private long addTime;
            private long updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGatewayId() {
                return gatewayId;
            }

            public void setGatewayId(int gatewayId) {
                this.gatewayId = gatewayId;
            }

            public int getMonitoringId() {
                return monitoringId;
            }

            public void setMonitoringId(int monitoringId) {
                this.monitoringId = monitoringId;
            }

            public int getSensorType() {
                return sensorType;
            }

            public void setSensorType(int sensorType) {
                this.sensorType = sensorType;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public long getSyncTime() {
                return syncTime;
            }

            public void setSyncTime(long syncTime) {
                this.syncTime = syncTime;
            }

            public int getWarningTotal() {
                return warningTotal;
            }

            public void setWarningTotal(int warningTotal) {
                this.warningTotal = warningTotal;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getReadValue() {
                return readValue;
            }

            public void setReadValue(String readValue) {
                this.readValue = readValue;
            }

            public int getLinkType() {
                return linkType;
            }

            public void setLinkType(int linkType) {
                this.linkType = linkType;
            }

            public int getRsAddress() {
                return rsAddress;
            }

            public void setRsAddress(int rsAddress) {
                this.rsAddress = rsAddress;
            }

            public int getBranch() {
                return branch;
            }

            public void setBranch(int branch) {
                this.branch = branch;
            }

            public Object getMfNo() {
                return mfNo;
            }

            public void setMfNo(Object mfNo) {
                this.mfNo = mfNo;
            }

            public int getLoopBack() {
                return loopBack;
            }

            public void setLoopBack(int loopBack) {
                this.loopBack = loopBack;
            }

            public int getResetFlag() {
                return resetFlag;
            }

            public void setResetFlag(int resetFlag) {
                this.resetFlag = resetFlag;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
