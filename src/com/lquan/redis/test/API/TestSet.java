package com.lquan.redis.test.API;

import redis.clients.jedis.Jedis;

/**
 * 测试Set类型数据
 * @author lquan
 *
 */
public class TestSet {
	
	public static void main(String[] args) {
		// 链接redis
		Jedis jedis = new Jedis("182.92.231.155", 6379);
		// 判断是否已经链接
		String pong =  jedis.ping();
		System.out.println("** "+pong);
		getSet(jedis);
	}
	
	/**
	 *  sadd/smembers/sismember
		 scard，获取集合里面的元素个数
		 srem key value 删除集合中元素
		 srandmember key 某个整数(随机出几个数)
		 spop key 随机出栈
		 smove key1 key2 在key1里某个值      作用是将key1里的某个值赋给key2
		
		集合
		差集：sdiff
		交集：sinter
		并集：sunion
	 * @param redis
	 */
	public static void getSet(Jedis redis){
		redis.sadd("s0", new String[]{"redis1","redis2","redis3"});
		System.out.println("****"+redis.smembers("s0"));
		
		//scard 获得样板的
	}
	

}
