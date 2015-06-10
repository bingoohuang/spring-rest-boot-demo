package com.raiyee.easyhi.apis.merchant.dao;

import org.n3r.eql.eqler.annotations.Eqler;
import org.n3r.eql.eqler.annotations.Sql;

@Eqler
public interface MerchantLookupDao {
    // create table resource (mobile varchar(11), tenant_id varchar(20))
    // insert into resource values('18551855185', 't001');
    // insert into resource values('18551855186', '中文测试');
    @Sql("SELECT TENANT_ID FROM RESOURCE WHERE MOBILE = ##")
    String getTidByMobile(String mobileNo);
}
