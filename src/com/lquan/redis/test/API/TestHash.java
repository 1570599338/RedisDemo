package com.lquan.redis.test.API;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import sun.management.resources.agent;

/**
 * 获取Hash类型数据的实测
 * @author lquan
 *
 */
public class TestHash {

	/**
	 * 常用hash集合
	 * @param args
	 */
	public static void main(String[] args) {
		// 链接redis
		Jedis jedis = new Jedis("182.92.231.155", 6379);
		// 判断是否已经链接
		String pong =  jedis.ping();
		System.out.println("** "+pong);
		testHashFun(jedis);

	}
	
	/**
		 hset/hget/hmset/hmget/hgetall/hdel
		 hlen
		 hexists key 在key里面的某个值的key
		 hkeys/hvals
		 hincrby/hincrbyfloat
		 hsetnx
	 **/
	public static void testHashFun(Jedis redis) {
		// 切换数据库
		redis.select(4);
		//hset 添加数据sk1
		//redis.hset("shk1", "hk1", "shv1");
		// 遍历数据sk1
		String shk1 = redis.hget("shk1", "hk1");
		System.out.println("shk1:**"+shk1);
		
		// hmset 多值设置
		redis.hmset("shk2", new HashMap<String,String>(){{this.put("hk1", "shv1");this.put("hk2", "hv2");this.put("hk3", "hv3");this.put("hk4", "hv4");}});
		// hgetall 获取多值
		Map<String, String> hgetAlls = redis.hgetAll("shk2");
		System.out.println("hgetAll"+hgetAlls);
		// hdel 删除key
		redis.hdel("shk2", "hk1");
		Map<String, String> hgetAllsh = redis.hgetAll("shk2");
		System.out.println("删除后的值："+hgetAllsh);
		
		// hlen 查看数据长度
		System.out.println("长度："+redis.hlen("shk2"));
		
		// hexists 判断某个值是否存在
		boolean a = redis.hexists("shk2", "hk2");
		System.out.println("判断值是否存在："+a);
		
		// hkeys 获取hashset的key
		Set<String> key =  redis.hkeys("shk2");
		System.out.print("hkeys:");
		for(String k :key){
			System.out.print(" - "+k );
		}
		
		//hvals 获取集合的value
		List<String> v =  redis.hvals("shk2");
		System.out.println();
		System.out.println("hvals:" +v.toString());
		
		//hincrby
	/*	long age = redis.hincrBy("student", "age", 2);
		System.out.println("年龄："+age);
		redis.hincrByFloat("student", "score", 90.1);*/
		
		long aa = redis.hsetnx("student", "agex", "23");
		System.out.println(aa);
		
		// 
		
		/*hset/hget/hmset/hmget/hgetall/hdel
		 hlen
		 hexists key 在key里面的某个值的key
		 hkeys/hvals
		 hincrby/hincrbyfloat
		 hsetnx*/
		
	}
	
	
	
	
	
	
	

}
