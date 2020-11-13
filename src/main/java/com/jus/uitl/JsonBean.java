package com.jus.uitl;

import java.io.Serializable;

/**
 * @Auther: JusChui
 * @Date: 2020/11/11 14:25
 * @Description:
 */
public class JsonBean implements Serializable {
    private static final long serialVersionUID = 2267751680865696851L;

    //请求码
    private String rtCode = "-9999";
    //返回消息
    private String rtMsg = null;
    //返回数据
    private Object data = "";

    public String getRtCode() {
        return rtCode;
    }

    public void setRtCode(String rtCode) {
        this.rtCode = rtCode;
    }

    public String getRtMsg() {
        return rtMsg;
    }

    public void setRtMsg(String rtMsg) {
        this.rtMsg = rtMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                ", rtCode='" + rtCode + '\'' +
                ", rtMsg='" + rtMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
