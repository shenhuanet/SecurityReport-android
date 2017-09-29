package com.shenhua.outer.security.report.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class WarningList {

    /**
     * success : true
     * msg : 获取成功
     * data : {"pageNum":1,"pageSize":2,"size":2,"startRow":1,"endRow":2,"total":43,"pages":22,"list":[{"id":65,"flag":1,"sensorId":1,"monitoringId":36,"gatewayId":1,"stationId":1,"sensorType":1,"startTime":1506665746000,"endTime":1506669827000,"warningStatus":1,"notifyStatus":4,"stationName":"同济大学-站点","gatewayName":"实验楼-258网关","monitoringName":"103室","typeName":"故障电弧","disposeNote":"处理完毕"},{"id":64,"flag":1,"sensorId":1,"monitoringId":36,"gatewayId":1,"stationId":1,"sensorType":1,"startTime":1506664653000,"endTime":1506669871000,"warningStatus":1,"notifyStatus":4,"stationName":"同济大学-站点","gatewayName":"实验楼-258网关","monitoringName":"103室","typeName":"故障电弧","disposeNote":"处理"}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"navigateFirstPage":1,"navigateLastPage":8,"firstPage":1,"lastPage":8}
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
         * pageNum : 1
         * pageSize : 2
         * size : 2
         * startRow : 1
         * endRow : 2
         * total : 43
         * pages : 22
         * list : [{"id":65,"flag":1,"sensorId":1,"monitoringId":36,"gatewayId":1,"stationId":1,"sensorType":1,"startTime":1506665746000,"endTime":1506669827000,"warningStatus":1,"notifyStatus":4,"stationName":"同济大学-站点","gatewayName":"实验楼-258网关","monitoringName":"103室","typeName":"故障电弧","disposeNote":"处理完毕"},{"id":64,"flag":1,"sensorId":1,"monitoringId":36,"gatewayId":1,"stationId":1,"sensorType":1,"startTime":1506664653000,"endTime":1506669871000,"warningStatus":1,"notifyStatus":4,"stationName":"同济大学-站点","gatewayName":"实验楼-258网关","monitoringName":"103室","typeName":"故障电弧","disposeNote":"处理"}]
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         * navigateFirstPage : 1
         * navigateLastPage : 8
         * firstPage : 1
         * lastPage : 8
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean implements Serializable {
            private static final long serialVersionUID = -6977210919715204132L;
            /**
             * id : 65
             * flag : 1
             * sensorId : 1
             * monitoringId : 36
             * gatewayId : 1
             * stationId : 1
             * sensorType : 1
             * startTime : 1506665746000
             * endTime : 1506669827000
             * warningStatus : 1
             * notifyStatus : 4
             * stationName : 同济大学-站点
             * gatewayName : 实验楼-258网关
             * monitoringName : 103室
             * typeName : 故障电弧
             * disposeNote : 处理完毕
             */

            private int id;
            private int flag;
            private int sensorId;
            private int monitoringId;
            private int gatewayId;
            private int stationId;
            private int sensorType;
            private long startTime;
            private long endTime;
            private int warningStatus;
            private int notifyStatus;
            private String stationName;
            private String gatewayName;
            private String monitoringName;
            private String typeName;
            private String disposeNote;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getSensorId() {
                return sensorId;
            }

            public void setSensorId(int sensorId) {
                this.sensorId = sensorId;
            }

            public int getMonitoringId() {
                return monitoringId;
            }

            public void setMonitoringId(int monitoringId) {
                this.monitoringId = monitoringId;
            }

            public int getGatewayId() {
                return gatewayId;
            }

            public void setGatewayId(int gatewayId) {
                this.gatewayId = gatewayId;
            }

            public int getStationId() {
                return stationId;
            }

            public void setStationId(int stationId) {
                this.stationId = stationId;
            }

            public int getSensorType() {
                return sensorType;
            }

            public void setSensorType(int sensorType) {
                this.sensorType = sensorType;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public int getWarningStatus() {
                return warningStatus;
            }

            public void setWarningStatus(int warningStatus) {
                this.warningStatus = warningStatus;
            }

            public int getNotifyStatus() {
                return notifyStatus;
            }

            public void setNotifyStatus(int notifyStatus) {
                this.notifyStatus = notifyStatus;
            }

            public String getStationName() {
                return stationName;
            }

            public void setStationName(String stationName) {
                this.stationName = stationName;
            }

            public String getGatewayName() {
                return gatewayName;
            }

            public void setGatewayName(String gatewayName) {
                this.gatewayName = gatewayName;
            }

            public String getMonitoringName() {
                return monitoringName;
            }

            public void setMonitoringName(String monitoringName) {
                this.monitoringName = monitoringName;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getDisposeNote() {
                return disposeNote;
            }

            public void setDisposeNote(String disposeNote) {
                this.disposeNote = disposeNote;
            }
        }
    }
}
