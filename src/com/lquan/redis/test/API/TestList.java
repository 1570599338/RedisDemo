package com.lquan.redis.test.API;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * ����redis��List�������ݵģ��У�
 * @author lquan
 *
 */
public class TestList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ����redis
		Jedis jedis = new Jedis("182.92.231.155", 6379);
		// �ж��Ƿ��Ѿ�����
			String pong =  jedis.ping();
			System.out.println("**"+pong);
			
			// ����list ����
			testList(jedis);

	}
	
	/**
	 *  lpush/rpush/lrange
	 *  lpop/rpop
	 *  lindex�����������±���Ԫ��(���ϵ���)
	 *  llen
	 *  lrem key ɾN��value
	 *  ltrim key ��ʼindex ����index����ȡָ����Χ��ֵ���ٸ�ֵ��key
	 *  rpoplpush Դ�б� Ŀ���б�    *�Ƴ��б�����һ��Ԫ�أ�������Ԫ����ӵ���һ���б�����
	 *  lset key index value
	 *  linsert key  before/after ֵ1 ֵ2  ��listĳ������ֵ��ǰ������Ӿ���ֵ
	 *   
	 * @param redis
	 */
	private static  void testList(Jedis redis){
		//  ѡ�����ݿ�
		redis.select(1);
		
		// lpush ����
		redis.lpush("list1", new String[]{"list1.1","list1.2","list1.3","list1.4",} );
		List<String> llist = redis.lrange("list1", 0, -1);
		System.out.println("�鿴��push���ݣ�"+ llist);
		
		
		
		
		 // lpush/rpush/lrange
		
		
	}

}
