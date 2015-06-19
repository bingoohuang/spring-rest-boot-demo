package com.raiyee.easyhi.apis.merchant.controller;

import com.github.bingoohuang.springrest.boot.exception.RestException;
import com.google.common.collect.Maps;
import com.raiyee.easyhi.apis.merchant.api.UploadApi;
import com.raiyee.easyhi.apis.merchant.api.YunpianApi;
import com.raiyee.easyhi.apis.merchant.service.MerchantLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/v1/demo")
public class DemoController {
    @Autowired
    MerchantLookupService merchantLookupService;

    @Autowired
    YunpianApi yunpianApi;

    @Autowired
    UploadApi uploadApi;

    @RequestMapping(value = "/upload-image", method = POST)
    public void uploadImage(@RequestParam("tid") String tid,
                                  @RequestParam("imageFile") MultipartFile imageFile){

        String s = uploadApi.uploadImage(tid, imageFile, "a.b", "100X100,200X200");
        System.out.println(s);
    }

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
