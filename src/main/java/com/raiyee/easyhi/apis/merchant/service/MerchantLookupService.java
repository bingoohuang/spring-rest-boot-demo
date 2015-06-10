package com.raiyee.easyhi.apis.merchant.service;

import com.raiyee.easyhi.apis.merchant.dao.MerchantLookupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantLookupService {
    @Autowired
    MerchantLookupDao merchantLookupDao;

    public String getTidByMobile(String mobileNo) {
        return merchantLookupDao.getTidByMobile(mobileNo);
    }
}
