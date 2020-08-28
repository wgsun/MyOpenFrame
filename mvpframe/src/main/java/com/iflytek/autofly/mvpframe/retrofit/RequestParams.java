package com.iflytek.autofly.mvpframe.retrofit;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author wgsun
 * @descrbe 请求参数工具类
 * @since 2019/12/18 11:24
 */
public class RequestParams {

    public static final int PARAMS_INVALID_PARAM_INT  = -1;
    public static final int PARAMS_INVALID_PARAM_RANK = 0;

    public static final int PAGE_SIZE_SINGLE = 20;
    public static final int PAGE_SIZE_TOTAL = 60;

    public static final String PARAMS_PPUID = "ppuid";
    public static final String PARAMS_TYPES = "types";
    public static final String PARAMS_ISPURCHASE = "ispurchase";

    public static final String PARAMS_ISPURCHASSE_FREE = "0";
    public static final String PARAMS_ISPURCHASSE_PAY = "2";

    public static final String PARAMS_KEY = "key";
    public static final String PARAMS_ONDEMAND = "ondemand";
    public static final String PARAMS_PAGENUM = "pageNo";
    public static final String PARAMS_PAGESIZE = "pageSize";
    public static final String PARAMS_CHANNELNAME = "chnName";
    public static final String PARAMS_MODE = "mode";

    public static final String PARAMS_THREECATEGORY = "categoryId";
    public static final String PARAMS_REQUIRETAGS = "needTags";

    public static final String PARAMS_YEAR = "year";
    public static final String PARAMS_POS = "pos";
    public static final String PARAMS_COUNT = "count";

    public static final String PARAMS_CIDS = "cids";
    public static final String PARAMS_VIP = "vip";

    public static final int SEARCH_MODE_RELATED = 1;
    public static final int SEARCH_MODE_NEW = 4;
    public static final int SEARCH_MODE_HOT = 11;

    /**
     * 推荐接口请求参数
     * @param type 可传递电视剧，电影，综艺，少儿，动漫五个值的组合 ，英文逗号分隔，不传取全部数据 (非必传)
     * @param ispurchase 付费方式 0：免费 • 2：付费已划价 (非必传)
     * @return
     */
    public static HashMap<String, String> getRecommendParams(String type, String ispurchase) {
        HashMap<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(type)) {
            map.put(PARAMS_TYPES, type);
        }
        if (!TextUtils.isEmpty(ispurchase)) {
            map.put(PARAMS_ISPURCHASE, ispurchase);
        }
        return map;
    }

    /**
     * 节目搜索接口请求参数
     * @param key 节目搜索关键字
     * @param ispurchase 付费方式，若无此参数，则返回所有付费和非付费 的 • 0：免费 • 1： 付费未划价 • 2：付费已划价 (非必传)
     * @param ondemand 是否付费点播, 若无此参数，则默认返回付费点播和非付费点播的 结果 • 0：不要付费点播的结果 • 1： 仅要付费点播的结果 • 不传：要付费点播和非付费点播的结果
     *                 说明：目前没有接奇谱付费点播的片子，此参 数无效 (非必传)
     * @param pageNum 查询页码，1代表第一页
     * @param pageSize 每页数据量，最大60条
     * @param mode 排序方式 • 1: 相关性 • 4: 最新 • 11: 最热 （非纪录片频道） • 10：最热 （纪录片频道）(非必传)
     * @return
     */
    public static HashMap<String, Object> getSearchParams(String key, String ispurchase, String ondemand
            , int pageNum, int pageSize, int mode) {
        HashMap<String, Object> map = new HashMap<>();
        if (!TextUtils.isEmpty(key)) {
            map.put(PARAMS_KEY, key);
        }
        if (!TextUtils.isEmpty(ispurchase)) {
            map.put(PARAMS_ISPURCHASE, ispurchase);
        }
        if (!TextUtils.isEmpty(ondemand)) {
            map.put(PARAMS_ONDEMAND, ondemand);
        }
        if (PARAMS_INVALID_PARAM_INT != pageNum) {
            map.put(PARAMS_PAGENUM, pageNum);
        }
        if (PARAMS_INVALID_PARAM_INT != pageSize) {
            map.put(PARAMS_PAGESIZE, pageSize);
        }
        if (PARAMS_INVALID_PARAM_INT != mode) {
            map.put(PARAMS_MODE, mode);
        }
        return map;
    }

    /**
     * 频道详情接口请求参数
     * @param chnName 频道名称
     * @param ispurchase  付费方式，若无此参数，则返回所有付费和非付费 的 • 0：免费 • 1： 付费未划价 • 2：付费已划价 (非必传)
     * @param ondemand 是否付费点播, 若无此参数，则默认返回付费点播和非付费点播的 结果 • 0：不要付费点播的结果 • 1： 仅要付费点播的结果 • 不传：要付费点播和非付费点播的结果
     *                 说明：目前没有接奇谱付费点播的片子，此参 数无效 (非必传)
     * @param mode 排序方式 • 1: 相关性 • 4: 最新 • 11: 最热 （非纪录片频道） • 10：最热 （纪录片频道）(非必传)
     * @param threeCategoryId 多个三级分类以逗号隔开
     * @param pageNum 查询页码，1代表第一页
     * @param pageSize 每页数据量，最大60条
     * @param requireTags 0：不需要TAG 1：需要TAG;默认不需要
     * @return
     */
    public static HashMap<String, Object> getChannelParams(String chnName, int ispurchase, int ondemand
            , int mode, String threeCategoryId, int pageNum, int pageSize, int requireTags) {
        HashMap<String, Object> map = new HashMap<>();
        if (!TextUtils.isEmpty(chnName)) {
            map.put(PARAMS_CHANNELNAME, chnName);
        }
        if (PARAMS_INVALID_PARAM_INT != ispurchase) {
            map.put(PARAMS_ISPURCHASE, ispurchase);
        }
        if (PARAMS_INVALID_PARAM_INT != ondemand) {
            map.put(PARAMS_ONDEMAND, ondemand);
        }
        if (PARAMS_INVALID_PARAM_INT != mode) {
            map.put(PARAMS_MODE, mode);
        }
        if (!TextUtils.isEmpty(threeCategoryId)) {
            map.put(PARAMS_THREECATEGORY, threeCategoryId);
        }
        if (PARAMS_INVALID_PARAM_INT != pageNum) {
            map.put(PARAMS_PAGENUM, pageNum);
        }
        if (PARAMS_INVALID_PARAM_INT != pageSize) {
            map.put(PARAMS_PAGESIZE, pageSize);
        }
        if (PARAMS_INVALID_PARAM_INT != requireTags) {
            map.put(PARAMS_REQUIRETAGS, requireTags);
        }
        return map;
    }

    /**
     * 剧集列表接口请求参数
     * @param year 来源类专辑的年份，格式如：2018 (非必传)
     * @param pageNo 游标开始位置，每次请求后返回，初始传0。如果传递 非标准参数，会默认为0
     * @param pageSize 每页显示条目数，最大60条，超过60，只返回前60条。 如果传递非标准参数，会默认为60
     * @return
     */
    public static HashMap<String, Integer> getProgramListParams(int year, int pageNo, int pageSize) {
        HashMap<String, Integer> map = new HashMap<>();
        if (PARAMS_INVALID_PARAM_INT != year) {
            map.put(PARAMS_YEAR, year);
        }
        if (PARAMS_INVALID_PARAM_INT != pageNo) {
            map.put(PARAMS_PAGENUM, pageNo);
        }
        if (PARAMS_INVALID_PARAM_INT != pageSize) {
            map.put(PARAMS_PAGESIZE, pageSize);
        }
        return map;
    }

    /**
     * 相关推荐接口参数
     * @param num
     * @param ispurchase
     * @param ondemand
     * @return
     */
    public static HashMap<String, Integer> getRecommendListParams(int num, int ispurchase, int ondemand) {
        HashMap<String, Integer> map = new HashMap<>();
        if (PARAMS_INVALID_PARAM_INT != num) {
            map.put(PARAMS_COUNT, num);
        }
        if (PARAMS_INVALID_PARAM_INT != ispurchase) {
            map.put(PARAMS_ISPURCHASE, ispurchase);
        }
        if (PARAMS_INVALID_PARAM_INT != ondemand) {
            map.put(PARAMS_ONDEMAND, ondemand);
        }
        return map;
    }

    /**
     * 风云榜接口参数
     * @param vip 是否只要vip内容
     * @param num 返回条目，默认20，最多60
     * @return
     */
    public static HashMap<String, Object> getRankListParams(int vip, int num) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PARAMS_VIP, vip);
        if (PARAMS_INVALID_PARAM_INT != num) {
            map.put(PARAMS_COUNT, num);
        }
        return map;
    }

    /**
     * VIP专区接口参数
     * @param vip 是否只要vip内容
     * @param num 返回条目，默认20，最多60
     * @return
     */
    public static HashMap<String, Object> getRankParams(int cid, int vip, int num) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PARAMS_CIDS, cid);
        map.put(PARAMS_VIP, vip);
        if (PARAMS_INVALID_PARAM_INT != num) {
            map.put(PARAMS_COUNT, num);
        }
        return map;
    }

    /**
     * 首字母推荐接口参数
     * @param key
     * @param count
     * @return
     */
    public static HashMap<String, Object> getSuggestParams(String key, int count) {
        HashMap<String, Object> map = new HashMap<>();
        if (!TextUtils.isEmpty(key)) {
            map.put(PARAMS_KEY, key);
        }
        if (PARAMS_INVALID_PARAM_INT != count) {
            map.put(PARAMS_COUNT, count);
        }
        return map;
    }
}
