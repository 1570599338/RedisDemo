package com.lquan.redis.test.API;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * 测试redis的List类型数据的ＡＰＩ
 * @author lquan
 *
 */
public class TestList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 链接redis
		Jedis jedis = new Jedis("182.92.231.155", 6379);
		// 判断是否已经链接
		String pong =  jedis.ping();
		System.out.println("**"+pong);
		// 测试list 方法 在redis中list类型的数据就是一个双链表
		testList(jedis);

	}
	
	/**
	 *  lpush/rpush/lrange
	 *  lpop/rpop
	 *  lindex，按照索引下标获得元素(从上到下)
	 *  llen
	 *  lrem key 删N个value
	 *  ltrim key 开始index 结束index，截取指定范围的值后再赋值给key
	 *  rpoplpush 源列表 目的列表    *移除列表的最后一个元素，并将该元素添加到另一个列表并返回
	 *  lset key index value
	 *  linsert key  before/after 值1 值2  在list某个已有值的前后再添加具体值
	 *   
	 * @param redis
	 */
	private static  void testList(Jedis redis){
		//  选择数据库
		redis.select(1);
		
		// lpush 方法
		redis.lpush("list1", new String[]{"list1.1。1","list1.2.2","list1.3.3","list1.4.4",} );
		List<String> llist = redis.lrange("list1", 0, -1);
		System.out.println("查看左push数据："+ llist);
		
		// rpush 方法 有链接的list
		redis.rpush("rlist", new String[]{"rlist1.1a","rlist1.2a","rlist1.3a","rlist1.4a",} );
		List<String> rlist = redis.lrange("rlist", 0, -1);
		System.out.println("查看右push数据："+ rlist);
		
		 // lpush/rpush/lrange
		
		
		//  删除第一个元素
		String  lpops = redis.lpop("list1");
		System.out.println(lpops+"****"+redis.rpop("list1"));
		//  lpop/rpop  出列
		
		String xx = redis.lindex("list1", 2);
		System.out.println(xx);
		//  lindex，按照索引下标获得元素(从上到下)
	}

}
