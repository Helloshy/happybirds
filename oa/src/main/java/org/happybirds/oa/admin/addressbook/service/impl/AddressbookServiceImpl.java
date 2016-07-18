package org.happybirds.oa.admin.addressbook.service.impl;

import javax.annotation.Resource;

import org.happybirds.oa.admin.addressbook.dao.ContactDao;
import org.happybirds.oa.admin.addressbook.dao.ContactGroupDao;
import org.happybirds.oa.admin.addressbook.service.AddressbookService;


/**
 * 通讯录模块业务处理接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:33:20
 * @version 1.0
 */
public class AddressbookServiceImpl implements AddressbookService {
	/**
	 * 注入dao接口实体
	 */
	@Resource
	private ContactDao contactDao;
	@Resource
	private ContactGroupDao contactGroupDao;
}
