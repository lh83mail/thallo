package io.github.lh83mail.thallo.common.web.response;


import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * Created at 2019/11/12 11:16
 * 接口返回数据统一格式,
 * {
 * errcode: 0,
 * errmsg: "成功",
 * data: "携带的数据",
 * total: 0,//分页查询时携带,总记录数
 * }
 *
 * @author FQ12138
 * @version 1.0
 */
public class ResponseHelper {
    private static final int CODE_OK = 0;
    private static final String CODE_OK_MSG = "OK";

    private static final int CODE_UNKNOWN_ERROR = -1;
    private static final String CODE_UNKNOWN_ERROR_MSG = "非预期错误";

    private ResponseHelper() {
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static <T> ResponseItem<T> ok() {
        return ok(null);
    }

    /**
     * 返回成功并携带其他数据
     *
     * @param data 数据
     * @return
     */
    public static <T> ResponseItem<T> ok(T data) {
        return ok(data, null);
    }

    /**
     * 返回成功并携带其他数据
     *
     * @param page 数据
     * @return
     */
    public static <T> ResponseList<T> okPage(Page<T> page) {
        ResponseList res = okList(page.getContent());
        res.setTotal(page.getTotalElements());
        return res;
    }


    public static <T> ResponseList<T> okList(Collection<T> content) {
        ResponseList res = new ResponseList<>();
        res.setCode(CODE_OK);
        res.setMsg(CODE_OK_MSG);
        res.setData(content);
        res.setTotal(content == null ? 0L : content.size());
        return res;
    }

    /**
     * 返回成功并携带其他数据
     *
     * @param data  数据
     * @param total 分页数据记录数
     * @return
     */
    public static <T> ResponseItem<T> ok(T data, Long total) {
        return response(CODE_OK, CODE_OK_MSG, data, total);
    }

    /**
     * 返回失败,并携带数据
     *
     * @param errcode 错误码
     * @param errmsg  错误消息
     * @param data    携带数据
     * @return
     */
    public static <T> ResponseItem<T> fail(int errcode, String errmsg, T data) {
        return response(errcode, errmsg, data, null);
    }

    /**
     * 返回失败, 不携带其他数据
     *
     * @param errcode 错误码
     * @param errmsg  错误消息
     * @return
     */
    public static <T> ResponseItem<T> fail(int errcode, String errmsg) {
        return fail(errcode, errmsg, null);
    }

    /**
     * 返回失败, 不携带其他数据
     *
     * @param errmsg 错误消息
     * @return
     */
    public static <T> ResponseItem<T> fail(String errmsg) {
        return fail(CODE_UNKNOWN_ERROR, errmsg, null);
    }

    /**
     * 返回失败, 不携带其他数据
     *
     * @param errcode 错误码
     * @return
     */
    public static <T> ResponseItem<T> fail(int errcode) {
        return fail(errcode, CODE_UNKNOWN_ERROR_MSG, null);
    }

    /**
     * 返回失败, 不携带其他数据
     *
     * @return
     */
    public static <T> ResponseItem<T> fail() {
        return fail(CODE_UNKNOWN_ERROR);
    }

    /**
     * response 返回
     *
     * @param errcode 错误码
     * @param errmsg  错误消息
     * @param data    返回携带的数据
     * @param total   分页总记录数
     * @return
     */
    public static <T> ResponseItem<T> response(int errcode, String errmsg, T data, Long total) {
        ResponseItem<T> res = new ResponseItem<>();

        res.setCode(errcode);
        res.setMsg(errmsg);
        res.setData(data);
        return res;
    }

}
