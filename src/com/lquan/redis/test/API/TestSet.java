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
		 smove key1   在key1里某个值      作用是将key1里的某个值赋给key2
		
		集合
		差集：sdiff
		交集：sinter
		并集：sunion5
	 * @param redis
	 */
	public static void getSet(Jedis redis){
		redis.sadd("s0", new String[]{"redis1","redis2","redis3"});
		System.out.println("****"+redis.smembers("s0"));
		
		// sismember查看集合中是否有对应的数据
		System.out.println("sismember:"+redis.sismember("s0", "redis1"));
		
		//scard 查看集合中的个数
		long scard = redis.scard("s0");
		System.out.println("scard:"+scard );
		
		// srem 删除集合中的制定元素
		long sremlx =redis.srem("s0", new String[]{"redis1"});
		System.out.println("sreml:"+sremlx );
		
		// srandmember 
		System.out.println("srandmember:"+redis.srandmember("s0") );
		
		// spop 随机出栈
		redis.spop("s0");
		System.out.println(redis.spop("s0")+"  smembers:"+redis.smembers("s0") );
		
		// 重新添加数据
		redis.sadd("s1", new String[]{"redis1","redis2","redis3"});
		redis.sadd("s2", new String[]{"redis21","redis22","redis23"});
		
		// smove
		redis.smove("s1", "s2", "redis2");
		System.out.println("smembers:"+redis.smembers("s2"));
		
		
		redis.sadd("sx", new String[]{"a","b","c","d","e"});
		redis.sadd("sy", new String[]{"a","b","c","1","2"});
		// sdiff 差集
		System.out.println("差集："+redis.sdiff("sx","sy"));
		
		// sinter 交集
		System.out.println("交集："+redis.sinter("sx","sy"));
		
		// sunion 并集
		System.out.println("并集："+redis.sunion("sx","sy"));
		
		
		
	}
	

}
