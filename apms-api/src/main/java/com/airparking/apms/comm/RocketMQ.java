package com.airparking.apms.comm;

import java.util.Properties;

import org.springframework.util.StringUtils;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

/**
 * Created by Ryan。Cao on 12/19/2016.
 */
public class RocketMQ {
  
  private static final Properties properties;
  
  static{
    properties = new Properties();
  }

  public ProducerBean producer() {
    ProducerBean producer = new ProducerBean();
    properties.setProperty("AccessKey", PropertiesUtils.get("producer.accessKey"));
    properties.setProperty("SecretKey", PropertiesUtils.get("producer.secretKey"));
    properties.setProperty("ProducerId", PropertiesUtils.get("producer.producerId"));
    properties.setProperty(PropertyKeyConst.ConsumeTimeout, "100000");

    if (!StringUtils.isEmpty(PropertiesUtils.get("producer.nameSrvAddr"))) {
      properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, PropertiesUtils.get("producer.nameSrvAddr"));
    }
    producer.setProperties(properties);
    producer.start();
    return producer;
  }
  
  

}