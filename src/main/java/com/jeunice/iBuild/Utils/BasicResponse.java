package com.jeunice.iBuild.Utils;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class BasicResponse {

    private Integer status;
    private String message;

    private Object data;
    private Instant timeStamp;

    public static BasicResponse success(Object data){
        BasicResponse basicResponse = new BasicResponse();

        basicResponse.setMessage("success");
        basicResponse.setStatus(0);
        basicResponse.setTimeStamp(Instant.now());
        basicResponse.setData(data);

        return basicResponse;
    }

    public static BasicResponse ofSuccess(String message){
        BasicResponse basicResponse = new BasicResponse();

        basicResponse.setMessage(message);
        basicResponse.setStatus(0);
        basicResponse.setTimeStamp(Instant.now());
        basicResponse.setData(null);

        return basicResponse;
    }

    public static BasicResponse success(String message, Object data){
        BasicResponse basicResponse = new BasicResponse();

        basicResponse.setMessage(message);
        basicResponse.setStatus(0);
        basicResponse.setTimeStamp(Instant.now());
        basicResponse.setData(data);

        return basicResponse;
    }
public static BasicResponse failure(){
        BasicResponse basicResponse = new BasicResponse();

        basicResponse.setStatus(400);
        basicResponse.setMessage("bad request");
        basicResponse.setTimeStamp(Instant.now());
        basicResponse.setData(null);

        return basicResponse;
}
    public static BasicResponse failure(String message){
        BasicResponse basicResponse = new BasicResponse();

        basicResponse.setStatus(400);
        basicResponse.setMessage(message);
        basicResponse.setTimeStamp(Instant.now());
        basicResponse.setData(null);

        return basicResponse;
    }

}
