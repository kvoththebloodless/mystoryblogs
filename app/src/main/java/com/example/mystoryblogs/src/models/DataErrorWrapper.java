package com.example.mystoryblogs.src.models;

public class DataErrorWrapper<T> {

    private boolean isErr;
    private String errMessage;
    private T result;

    public DataErrorWrapper(T object) {
        result = object;
    }

    public DataErrorWrapper(Throwable t)
    {
        setErr(true);
        setErrMessage(t.getMessage());
    }

    public boolean isErr() {
        return isErr;
    }

    public void setErr(boolean err) {
        isErr = err;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }


}
