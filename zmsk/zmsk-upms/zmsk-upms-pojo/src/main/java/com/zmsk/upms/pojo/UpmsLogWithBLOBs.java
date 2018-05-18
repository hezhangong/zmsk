package com.zmsk.upms.pojo;

public class UpmsLogWithBLOBs extends UpmsLog {
    private String parameter;

    private String result;

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}