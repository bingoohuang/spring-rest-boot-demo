package com.raiyee.easyhi.apis.merchant.api;

import com.github.bingoohuang.springrestclient.annotations.FixedRequestParam;
import com.github.bingoohuang.springrestclient.annotations.SpringRestClientEnabled;
import com.github.bingoohuang.springrestclient.annotations.SuccInResponseJSONProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.Future;

@SpringRestClientEnabled(baseUrl = "http://yunpian.com/v1")
public interface YunpianApi {
    @RequestMapping("/sms/send.json")
    @FixedRequestParam(name = "apikey", value = "xxx")
    @SuccInResponseJSONProperty(key = "code", value = "0")
    Future<Void> asyncSend(@RequestParam("text") String text,
                           @RequestParam("mobile") String mobile);
}

