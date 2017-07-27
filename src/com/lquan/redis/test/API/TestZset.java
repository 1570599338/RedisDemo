package com.lquan.redis.test.API;

import java.util.HashMap;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * 有序集合的测试工作
 * @author lquan
 *
 */
public class TestZset {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 链接redis
		Jedis redis = new Jedis("182.92.231.155", 6379);
		// 查看redis是否链接成功
		System.out.println("否链接成功与否："+redis.ping());
		
		//测试方法
		testSortset(redis);
	}
	

	/**
	 * sortSet测试结果
	 */
	@SuppressWarnings("serial")
	public static void testSortset(Jedis redis){
		//选择数据库
		redis.select(6);
		//zadd 添加数据
		redis.zadd("zka", 1, "v1");
		redis.zadd("zk2", new HashMap<Double, String>(){{this.put(1.0, "v1");this.put(2.0, "v2");this.put(3.0, "v3");this.put(4.0, "v4");}});
		
		// zrange
		System.out.println("zrange: "+redis.zrange("zka", 0,-1)+ " -- "+redis.zrange("zk2", 0, -1));
		
		//zrangebyScore 根绝score来查询数据
		Set<String> zrbs = redis.zrangeByScore("zk2", String.valueOf(2.0),  String.valueOf(3.0));
		for(String k:zrbs){
			System.out.print(" --"+k);
		}
		System.out.println();
	
		// zrem 删除指定的值
		Set<Tuple> zks = redis.zrangeWithScores("zk2", 0, -1);
		System.out.print("删除前数据");
		for(Tuple t :zks){
			System.out.print("  "+t.getScore()+"--"+t.getElement());
		}
		System.out.println();
		redis.zrem("zk2", "v3");
		
		Set<Tuple> zksx = redis.zrangeWithScores("zk2", 0, -1);
		System.out.print("删除后数据");
		for(Tuple t :zksx){
			System.out.print("  "+t.getScore()+"--"+t.getElement());
		}
		System.out.println();
		
		
		// zcard 查看值
		long len = redis.zcard("zk2");
		System.out.println("zcard:"+len);
		
		// zcount 查看范围的值
		long zcount = redis.zcount("zk2", "2", "4");
		System.out.println("zcount:"+zcount);
		
		// zrank 根据v获取下标值
		long zrank = redis.zrank("zk2", "v4");
		System.out.println("zrank:"+zrank);
		
		
		
		// zrevrank 逆序获取下标
		Set<Tuple> zks1 = redis.zrangeWithScores("zk2", 0, -1);
		System.out.print("正序的结果集：");
		for(Tuple t :zks1){
			System.out.print("  "+t.getScore()+"--"+t.getElement());
		}
		System.out.println();
		
		// zrevrange 逆序查询结果
		Set<String> ssSet = redis.zrevrange("zk2", 0, -1);
		System.out.println("逆序的结果集："+ssSet);
		long zrank2 = redis.zrevrank("zk2", "v1");
		System.out.println("逆序的下标值："+zrank2);
		
		
		// zrevrangebyscore 逆序查询结果
		Set<String> zrev = redis.zrevrangeByScore("zk2", 4, 2);
		System.out.println("zrevrangebyscore值："+zrev);
		
		
	}
  
	/*
		 zadd/zrange
		 zrangebyscore key 开始score 结束score
		 zrem key 某score下对应的value值，作用是删除元素
		 zcard/zcount key score区间/zrank key values值，作用是获得下标值/zscore key 对应值,获得分数
		 zrevrank key values值，作用是逆序获得下标值
		 zrevrange
		 zrevrangebyscore  key 结束score 开始score
	 */
}
