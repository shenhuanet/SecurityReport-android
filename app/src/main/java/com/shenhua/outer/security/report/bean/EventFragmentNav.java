package com.shenhua.outer.security.report.bean;

/**
 * Created by shenhua on 2017/9/30.
 * Email shenhuanet@126.com
 */
public class EventFragmentNav {

    private int pageId;
    private int dataId;

    public EventFragmentNav(int pageId, int dataId) {
        this.pageId = pageId;
        this.dataId = dataId;
    }

    public int getPageId() {
        return pageId;
    }

    public int getDataId() {
        return dataId;
    }

    @Override
    public String toString() {
        return "页码：" + pageId + " 参数：" + dataId;
    }
}
