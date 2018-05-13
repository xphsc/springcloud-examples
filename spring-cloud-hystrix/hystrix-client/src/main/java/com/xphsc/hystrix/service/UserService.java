package com.xphsc.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@Service
public class UserService {

    @Autowired
    protected RestTemplate restTemplate;



    @HystrixCommand(fallbackMethod = "queryStringFallBack")
    public String queryString(){
        return restTemplate.getForEntity("http://user-server/queryString",String.class).getBody();
    }

    public String queryStringFallBack(){
        return "queryString方法法故障，启用断路器";
    }


    @HystrixCollapser(batchMethod = "getFind",collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "100")
    })
    public String  find(){
        System.out.printf("========{}"+"find");
        return null;
    }

   /* @HystrixCommand
    public Observable<String> getFind(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        String result = restTemplate.getForObject("user-server/queryString",String.class);
                        subscriber.onNext(String);
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }*/

}
