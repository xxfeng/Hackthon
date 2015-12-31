package com.hackthon.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hackthon.domain.userdata;


/**
 *@author 作者 Email:
 *@version 创建时间：2013-12-15下午10:16:03
 *@类说明
 */
@Repository
public class UserDao {
	
	Logger logger=Logger.getLogger(UserDao.class);
	@Autowired
	MongoTemplate mongoTemplate;
	
	private static final String collectionName="user";
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	/**
	 * 增添用户
	 * @param user 用户实例
	 * @return boolean 添加是否成功
	 */
	public boolean insertUser(userdata user)
	{
		
		try {
			mongoTemplate.insert(user, collectionName);	
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("增加用户失败："+e.toString());
			return false;
		}
		
	}
	
	/**
	 * 根据条件查找用户
	 * @param queryMap 查询条件
	 * @return List<userdata> 用户列表
	 */
	public List<userdata> findUser(Map<String, Object> queryMap)
	{
		try {
			//设置查询条件	
			Query query=new Query();
			for(String key:queryMap.keySet())
			{
				query.addCriteria(Criteria.where(key).is(queryMap.get(key)));
						
			}
			return mongoTemplate.find(query, userdata.class, collectionName);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("查找用户失败："+e.toString());
			return null;
		}

	}

}
