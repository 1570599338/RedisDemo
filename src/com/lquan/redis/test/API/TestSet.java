package com.lquan.redis.test.API;

import redis.clients.jedis.Jedis;

/**
 * ����Set��������
 * @author lquan
 *
 */
public class TestSet {
	
	public static void main(String[] args) {
		// ����redis
		Jedis jedis = new Jedis("182.92.231.155", 6379);
		// �ж��Ƿ��Ѿ�����
		String pong =  jedis.ping();
		System.out.println("** "+pong);
		getSet(jedis);
	}
	
	/**
	 *  sadd/smembers/sismember
		 scard����ȡ���������Ԫ�ظ���
		 srem key value ɾ��������Ԫ��
		 srandmember key ĳ������(�����������)
		 spop key �����ջ
		 smove key1 key2 ��key1��ĳ��ֵ      �����ǽ�key1���ĳ��ֵ����key2
		
		����
		���sdiff
		������sinter
		������sunion
	 * @param redis
	 */
	public static void getSet(Jedis redis){
		redis.sadd("s0", new String[]{"redis1","redis2","redis3"});
		System.out.println("****"+redis.smembers("s0"));
		
		//scard ��������
	}
	

}
