package cn.jpush.api.utils;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.fastjson.JSON;

public class Jgpush {

		//一级端
		private static final String stairUserAppKey ="99a505e1a14b86a24cd0ad83";
		private static final String stairUserMasterSecret = "74b2867537e48f8f89f9c295";

	    private static boolean  apnsProduction = false;  //是否是生产环境,false为测试，true，为生产

	    public static void main(String[] args) throws Exception {
	    	//全推
	    	//sendMessage("测试全推");
	    	Map<String, Object> map = new HashMap<String,Object>() ;
	    	map.put("serialNumber", "23456");
		    map.put("type", "2");
	    	sendMessage_simple("动能社区",new String[]{"alias1"},map);
		}
	    
		/**
		 * 群推  (含自定义消息)
		 * msg         通知内容
		 * alias       接受消息人的别名
		 * type  	   1:一级端，2:二级端
		 * @author  
		 */
		public static boolean sendMessage_simple(String msg,String[] alias,Map<String,Object> extra){
			JPushClient jpushClient =  null;
			try {
				jpushClient = new JPushClient(stairUserMasterSecret,stairUserAppKey,apnsProduction, 1);
				PushPayload payload = buildPushObject_android_and_ios_group(msg,alias,extra);//单推
				PushResult result = jpushClient.sendPush(payload);
				System.out.println("单推成功："+result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true ;
		}
	    
	    /**
	     * all
	     */
	    public static PushPayload buildPushObject_android_and_ios(String msg) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.all())
	                .setNotification( //设置通知消息体(手机通知栏会显示消息)
	    		    				Notification.newBuilder()
	    		    					                    .addPlatformNotification(
		    		    					        		                IosNotification.newBuilder()
		    		    					        		                .setAlert(msg)
								                                            .setSound("default")
								                                            .build())
							                                            .addPlatformNotification(
	    		    					        		    		       AndroidNotification.newBuilder()
	    		    					        		    		      .setAlert(msg)
	    		    					        		    		      .build()
	    		    					        		     ).build()
                                )
	                .build();
	    }
	    
	    /**
	     * 
	     *    组推
	     */
	    public static PushPayload  buildPushObject_android_and_ios_group(String msg,String[] alias,Map<String,Object> extra){
	    	return PushPayload.newBuilder()
	    			.setPlatform(Platform.all()) //设备类型 andorid ios等
	    			.setAudience(Audience.alias(alias)) // 手机端在极光注册的别名
	    			.setNotification(
                                            //设置通知消息体(手机通知栏会显示消息)
	    		    					       Notification.newBuilder()
	    		    					                              .addPlatformNotification(
		    		    					        		                IosNotification.newBuilder()
		    		    					        		                .setAlert(msg)
								                                            .setSound("happy.caf")
								                                            .addExtras(extra)
								                                            .build())
							                                            .addPlatformNotification(
	    		    					        		    		       AndroidNotification.newBuilder()
	    		    					        		    		      .setAlert(msg).addExtras(extra)
	    		    					        		    		      .build()
	    		    					        		    		  ).build()
	    					)
	    			 .build();
	    }
	    /**
		 * 全推
		 * msg         通知内容
		 * extra       自定义消息
		 */
		public static boolean sendMessage_all(String msg,Map<String,Object> extra){
			JPushClient jpushClient =  null;
			try {
				jpushClient = new JPushClient(stairUserMasterSecret,stairUserAppKey,apnsProduction, 1);
				PushPayload payload = buildPushObject_android_and_ios(msg,extra);
				PushResult result = jpushClient.sendPush(payload);
				System.out.println("全推成功："+result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true ;
		}
		
	    /**
	     * 全推带自定义消息
	     */
	    public static PushPayload buildPushObject_android_and_ios(String msg,Map<String,Object> extra) {
	        if(null != extra && extra.size() > 0){
	        	return PushPayload.newBuilder()
	        			.setPlatform(Platform.android_ios())
	        			.setAudience(Audience.all())
	        			.setNotification( //设置通知消息体(手机通知栏会显示消息)
	        					Notification.newBuilder()
	        					.addPlatformNotification(
	        							IosNotification.newBuilder()
	        							.setAlert(msg)
	        							.setSound("default")
	        							
	        							.build())
	        					.addPlatformNotification(
	        							AndroidNotification.newBuilder()
	        							.setAlert(msg)
	        							.addExtras(extra)
	        							.build()
	        							).build()
	        					)
	        			.build();
	        }else{
	        	return PushPayload.newBuilder()
	        			.setPlatform(Platform.android_ios())
	        			.setAudience(Audience.all())
	        			.setNotification( //设置通知消息体(手机通知栏会显示消息)
	        					Notification.newBuilder()
	        					.addPlatformNotification(
	        							IosNotification.newBuilder()
	        							.setAlert(msg)
	        							.setSound("default")
	        							
	        							.build())
	        					.addPlatformNotification(
	        							AndroidNotification.newBuilder()
	        							.setAlert(msg)
	        							.build()
	        							).build()
	        					)
	        			.build();
	        }
	    }
}
