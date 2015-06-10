package com.raiyee.easyhi.apis.merchant.controller;

import com.github.bingoohuang.springrest.boot.exception.RestException;
import com.google.common.collect.Maps;
import com.raiyee.easyhi.apis.merchant.api.YunpianApi;
import com.raiyee.easyhi.apis.merchant.service.MerchantLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/demo")
public class DemoController {
    @Autowired
    MerchantLookupService merchantLookupService;

    @Autowired
    YunpianApi yunpianApi;

    @RequestMapping(value = "/get-tid-by-mobile/{mobileNo}", method = RequestMethod.GET)
    public String getTidByMobile(@PathVariable("mobileNo") String mobileNo) {
        String tid = merchantLookupService.getTidByMobile(mobileNo);
        if (tid != null) return tid;

        throw new RestException(400, "tenant id is not found");
    }

    @RequestMapping(value = "/send-sms/{mobileNo}/{text}")
    public void sendSms(@PathVariable("mobileNo") String mobileNo, @PathVariable("text") String text) {
        yunpianApi.asyncSend(text, mobileNo);
    }

    @RequestMapping(value = "/show/{status}", method = RequestMethod.GET)
    public Map show(@PathVariable("status") int status) {
        if (status == 200) {
            HashMap<String, Object> map = Maps.newHashMap();
            map.put("name", "bingoo");
            map.put("age", 123);
            map.put("young", true);
            return map;
        }

        throw new RestException(status, "error occured");
    }

}
