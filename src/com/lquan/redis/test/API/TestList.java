package com.lquan.redis.test.API;

import java.util.List;


import redis.clients.jedis.BinaryClient.LIST_POSITION;
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
		Long  llenstr = redis.llen("list1");
		System.out.println(redis.lrange("list1", 0, -1)+"****"+llenstr);
		//  llen
		
		Long aa = redis.lrem("list1", 1, "list1.1。1");//指定删除多个value
		
		//redis.lpush("ltrimlist", new String[]{"x"});
		//redis.lpush("list1", new String[]{"list1.1。1","list1.2.2","list1.3.3","list1.4.4",} );
		// *  ltrim key 开始index 结束index，截取指定范围的值后再赋值给key
		redis.del("rlista");
		redis.lpush("rlista", new String[]{"a","b","b","c","c","c","d"} );
		System.out.println("删除前："+redis.lrange("rlista", 0, -1));
		redis.ltrim("rlista", 1, 6);
		System.out.println("删除后："+redis.lrange("rlista", 0, -1));
		
		
		//lrem key 删N个value
		redis.del("rlistb");
		redis.lpush("rlistb", new String[]{"a","b","b","c","c","c","d"} );
		System.out.println("删除前："+redis.lrange("rlistb", 0, -1));
		redis.lrem("rlistb", 2, "b");
		System.out.println("删除后："+redis.lrange("rlistb", 0, -1));
		
		
		// rpoplpush 源列表 目的列表    *移除列表的最后一个元素，并将该元素添加到另一个列表并返回
		redis.del("rlistc");
		redis.lpush("rlistc", new String[]{"a","b","c","d","e","f"} );
		redis.del("rlistd");
		redis.lpush("rlistd", new String[]{"1","2","3","4","5","6"} );
		redis.rpoplpush("rlistc", "rlistd");
		System.out.println("rpoplpush："+redis.lrange("rlistd", 0, -1));
		
		
		//  lset key index value 修改指定位置的值
		redis.del("rliste");
		redis.lpush("rliste", new String[]{"1","2","3","4","5","6"} );
		redis.lset("rliste", 1, "xx");
		System.out.println("rliste："+redis.lrange("rliste", 0, -1));
		
		
		// linsert key  before/after 值1 值2  在list某个已有值的前后再添加具体值
		redis.del("linsertx");
		redis.lpush("linsertx", new String[]{"a","b","c"} );
		redis.linsert("linsertx", LIST_POSITION.BEFORE, "b", "java");
		System.out.println("linsertx："+redis.lrange("linsertx", 0, -1));
		redis.linsert("linsertx", LIST_POSITION.AFTER, "b", "mysqls");
		System.out.println("linsertx："+redis.lrange("linsertx", 0, -1));
		/**
	*  lrem key 删N个value
	 *  ltrim key 开始index 结束index，截取指定范围的值后再赋值给key
	 *  rpoplpush 源列表 目的列表    *移除列表的最后一个元素，并将该元素添加到另一个列表并返回
	 *  lset key index value
	 *  linsert key  before/after 值1 值2  在list某个已有值的前后再添加具体值
		
		**/
	}

}
