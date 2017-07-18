package com.lquan.redis.test;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestRedisAPI {
	
	public static void main(String[] args) throws Exception {
		// 链接redis
		Jedis jedis = new Jedis("192.168.207.203", 6379);
		// 判断是否已经链接
			String pong =  jedis.ping();
			System.out.println("**"+pong);
		
		// 测试key 相关的程序
		//testKey( jedis);
			
		//测试操作String 类型的数据
		//	testString(jedis);
		
	}
	
	// 测试String类型数据的方法
	/**
	 * 
	 * set/get/del/append/strlen
	 *Incr/decr/incrby/decrby,一定要是数字才能进行加减
	 *getrange/setrange
	 *setex(set with expire)键秒值/setnx(set if not exist)
	 *mset/mget/msetnx
	 * getset(先get再set)
	 * @param jedis
	 * @throws Exception
	 */
	public static void testString(Jedis redis) throws Exception {
		// 设置元素  -c 次方法返回的是状态ＯＫ
		String a = redis.set("setStringkey1", "stringkey1１");
		System.out.println(a);
		
		// 获取对应的key
		String getStringKey = redis.get("setStringkey1");
		System.out.println("获取对应的key: "+getStringKey);
		
		// 删除数据
		//redis.del("setStringkey1");
		System.out.println("删除数据查询对应的值：: "+ redis.get("setStringkey1"));
		
		// 拼接
		redis.set("setStringkey2", "Stringkey2");
		System.out.println("拼接前对应的值： "+ redis.get("setStringkey2"));
		redis.append("setStringkey2", "xxxx");
		System.out.println("拼接后对应的值： "+ redis.get("setStringkey2"));
		
		// 获取字符串的长度
		Long l = redis.strlen("setStringkey2");
		System.out.println("获取字符串的长度:"+l);
		
		// incr 方法单步递增
		redis.set("countincr","0");
		long count = redis.incr("countincr");
		System.out.println("incr 方法单步递增"+count);
		
		// decr 单步递减
		long decrcount = redis.decr("countincr");
		System.out.println("decrcount 方法单步递减："+decrcount);
		
		// incrby 指定增加
		Long incrBycount = redis.incrBy("countincr", 5);
		System.out.println("ncrby 指定增加："+incrBycount);
		
		//decrby 指定较少
		Long decrBycount = redis.decrBy("countincr", 2);
		System.out.println("decrby 指定较少："+decrBycount);
		
		redis.set("getrange", "getrangexyz123");
		// getrange 方法
		String getrange = redis.getrange("getrange", 2, 3); 
		System.out.println("getrange 方法"+getrange);
		
		// setrang 指定的位置被替换掉
		Long setrang = redis.setrange("getrange", 8, "lquan");
		System.out.println("指定的位置被替换掉"+ redis.getrange("getrange", 0, -1) );
		
		//  setex 设置有效时间
		redis.setex("getrange", 15, "设置时间为15s");
		System.out.println("查看getrange的有效时长:"+ redis.ttl("getrange") +"   key："+redis.get("getrange") );
	//	Thread.sleep(10000);
		System.out.println("10秒后查看getrange的有效时长:"+ redis.ttl("getrange") +"   key："+redis.get("getrange") );
	//	Thread.sleep(6000);
		System.out.println("16秒后查看getrange的有效时长:"+ redis.ttl("getrange") +"   key："+redis.get("getrange") );
	
		// setnx 方法，只有在对应的key值不存在的情况下才能添加进入
		redis.set("getrangexx", "getrangexx");
		Long aa = redis.setnx("getrangexx", "xxxxx");
		System.out.println("状态："+aa+"查询值"+redis.get("getrangexx"));
		System.out.println("不存在前"+redis.get("getrangexxlquan"));
		Long aaa = redis.setnx("getrangexxlquan", "getrangexxlquan");
		System.out.println("状态："+aaa+"查询值"+redis.get("getrangexxlquan"));
		
		 // 多值设置
		redis.mset(new String[]{"kx1","vx1","kx2","vx2"});
		
		//获取多值
		List<String> mgetList = redis.mget(new String[]{"kx1","kx2"});
		System.out.println(mgetList);
		
		// 设置
		redis.msetnx(new String[]{"kx1","vx3","kx2","vx4"});
		System.out.println("存在情况下值："+redis.mget(new String[]{"kx1","kx2"}));
		redis.msetnx(new String[]{"kxy1","vx3","kxy2","vx4"});
		System.out.println("不存在情况下值："+redis.mget(new String[]{"kxy1","kxy2"}));
		
		// getset方法 主要是在set前获取之前的值，无论key存在与否
		System.out.println(" getset方法值："+redis.getSet("kxyx1","kxy2"));
		System.out.println(" getset方法值："+redis.getSet("kxyx1","kxy2"));
		
		
		/**
		 * set/get/del/append/strlen
		 *Incr/decr/incrby/decrby,一定要是数字才能进行加减
		 *getrange/setrange
		 *setex(set with expire)键秒值/setnx(set if not exist)
		 *mset/mget/msetnx
		 * getset(先get再set)
		 */
		
	}
	
	// 测试key相关的程序
	public static void testKey(Jedis jedis) throws Exception {
		// 获得所有的key keys *
				Set<String> keys  = jedis.keys("*");
				for(String key:keys){
					System.out.println("key:"+key+" <->value:"+jedis.get(key));
				}
		// exists key 方法	
				if(jedis.exists("k3")){
						System.out.println("存在k3");
				}else{
						System.out.println("不存在k3");
				}
		// move key db 移动指定的key到db中
			jedis.move("k1", 1);
				
		// expire key time 给指定的key设置时间 和ttl key的有效时长
			System.out.println("执行前："+jedis.ttl("k3") +"获取当前的value   "+jedis.get("k3"));
			jedis.expire("k3",10 );//设定10秒
			Thread.sleep(1000*5); //设定5秒睡眠
			System.out.println("执行中："+jedis.ttl("k3") +"获取当前的value   "+jedis.get("k3"));
			Thread.sleep(1000*7); //设定5秒睡眠	
			System.out.println("过期后："+jedis.ttl("k3") +"获取当前的value   "+jedis.get("k3"));
			
			// 获取key的类型
			System.out.println(jedis.type("k2"));
	}
	
	//

}
