package user_service.rabbitMQ;

import org.json.JSONObject;

public class RabbitLog {

    String input;
    String inputTime;
    String output;
    String outputTime;
    String date;
    String httpRequestType;
    String request;
    boolean isError;

    public RabbitLog(String request, String httpRequestType, String date, String inputTime, String input){
        this.request = request;
        this.httpRequestType = httpRequestType;
        this.date = date;
        this.inputTime = inputTime;
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("input", input);
        json.put("inputTime", inputTime);
        json.put("output", output);
        json.put("outputTime", outputTime);
        json.put("date", date);
        json.put("httpRequestType", httpRequestType);
        json.put("request", request);
        json.put("isError", isError);
        return json;
    }


    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        this.isError = error;
    }

    public String getHttpRequestType() {
        return httpRequestType;
    }

    public void setHttpRequestType(String httpRequestType) {
        this.httpRequestType = httpRequestType;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutputTime() {
        return outputTime;
    }

    public void setOutputTime(String outputTime) {
        this.outputTime = outputTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
