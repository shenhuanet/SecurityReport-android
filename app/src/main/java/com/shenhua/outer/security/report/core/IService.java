package com.shenhua.outer.security.report.core;

import com.shenhua.outer.security.report.bean.MonitorInfo;
import com.shenhua.outer.security.report.bean.User;
import com.shenhua.outer.security.report.bean.UserStations;
import com.shenhua.outer.security.report.bean.WarningCount;
import com.shenhua.outer.security.report.bean.WarningList;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public interface IService {

    /**
     * 报警处理
     *
     * @param warningId 报警id
     * @param msg       处理意见
     * @return String
     */
    @POST("app/doHandle")
    Call<String> resolveWarning(@Query("id") int warningId, @Query("remark") String msg);

    /**
     * 获取所有报警列表
     *
     * @param userId   用户id
     * @param pageNum  页码
     * @param pageSize 页码大小
     * @return 所有报警列表
     */
    @POST("app/getWarningList")
    Call<WarningList> getWarningList(@Query("userId") int userId, @Query("pageNum") int pageNum,
                                     @Query("pageSize") int pageSize);

    /**
     * 获取所有报警列表
     *
     * @param userId   用户id
     * @param pageNum  页码
     * @param pageSize 页码大小
     * @param flag     0-获取未处理的 1 获取已处理
     * @return 所有报警列表
     */
    @POST("app/getWarningList")
    Call<WarningList> getWarningList(@Query("userId") int userId, @Query("pageNum") int pageNum,
                                     @Query("pageSize") int pageSize, @Query("flag") int flag);

    /**
     * 获取报警数
     *
     * @param userId 用户id
     * @return WarningCount
     */
    @POST("app/getSensorCount")
    Call<WarningCount> getWarningCount(@Query("userId") int userId);

    /**
     * 消音
     *
     * @param sersorId sersorId
     * @return UserStations
     */
    @POST("app/sensor/noiseReduction")
    Call<String> noiseReduction(@Query("monitoringId") int sersorId);

    /**
     * 复位
     *
     * @param sersorId sersorId
     * @return UserStations
     */
    @POST("app/sensor/reset")
    Call<String> reset(@Query("monitoringId") int sersorId);

    /**
     * 获取监测点详情
     *
     * @param monitoringId monitoringId
     * @return UserStations
     */
    @POST("app/getMonitoringInfo")
    Call<MonitorInfo> getMonitoringInfo(@Query("monitoringId") int monitoringId);

    /**
     * 获取网关监测点
     *
     * @param gatewayId gatewayId
     * @param pageNum   pageNum
     * @param pageSize  pageSize
     * @return UserStations
     */
    @POST("app/getMonitoringByGateWayId")
    Call<UserStations> getMonitoringByGateWayId(@Query("gatewayId") int gatewayId, @Query("pageNum") int pageNum,
                                                @Query("pageSize") int pageSize);

    /**
     * 获取站点
     *
     * @param stationId stationId
     * @param pageNum   pageNum
     * @param pageSize  pageSize
     * @return UserStations
     */
    @POST("app/getGatewayByStationId")
    Call<UserStations> getGateways(@Query("stationId") int stationId, @Query("pageNum") int pageNum,
                                   @Query("pageSize") int pageSize);

    /**
     * 获取站点
     *
     * @param userId   userId
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @return UserStations
     */
    @POST("app/getUserStations")
    Call<UserStations> getStations(@Query("userId") int userId, @Query("pageNum") int pageNum,
                                   @Query("pageSize") int pageSize);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param clientId 客户端id
     * @param sysType  系统类型 1 android
     * @return User
     */
    @POST("app/login")
    Call<User> login(@Query("tel") String username, @Query("password") String password,
                     @Query("clientId") String clientId, @Query("sysType") int sysType);

}
