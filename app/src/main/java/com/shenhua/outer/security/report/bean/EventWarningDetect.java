package com.shenhua.outer.security.report.bean;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class EventWarningDetect {

    private int unusual;
    private int online;
    private int all;

    public EventWarningDetect(int unusual, int online, int all) {
        this.unusual = unusual;
        this.online = online;
        this.all = all;
    }

    @Override
    public String toString() {
        return "监测点总数: " + all + "报警监测点: " + unusual + "在线监测点: " + online;
    }

    public int getUnusual() {
        return unusual;
    }

    public int getOnline() {
        return online;
    }

    public int getAll() {
        return all;
    }
}
