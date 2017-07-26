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
		
		// sismember�鿴�������Ƿ��ж�Ӧ������
		System.out.println("sismember:"+redis.sismember("s0", "redis1"));
		
		//scard �鿴�����еĸ���
		long scard = redis.scard("s0");
		System.out.println("scard:"+scard );
		
		// srem ɾ�������е��ƶ�Ԫ��
		long sremlx =redis.srem("s0", new String[]{"redis1"});
		System.out.println("sreml:"+sremlx );
		
		// srandmember 
		System.out.println("srandmember:"+redis.srandmember("s0") );
		
		// spop �����ջ
		redis.spop("s0");
		System.out.println(redis.spop("s0")+"  smembers:"+redis.smembers("s0") );
		
		// �����������
		redis.sadd("s1", new String[]{"redis1","redis2","redis3"});
		redis.sadd("s2", new String[]{"redis21","redis22","redis23"});
		
		// smove
		redis.smove("s1", "s2", "redis2");
		System.out.println("smembers:"+redis.smembers("s2"));
		
		
		redis.sadd("sx", new String[]{"a","b","c","d","e"});
		redis.sadd("sy", new String[]{"a","b","c","1","2"});
		// sdiff �
		System.out.println("���"+redis.sdiff("sx","sy"));
		
		// sinter ����
		System.out.println("������"+redis.sinter("sx","sy"));
		
		// sunion ����
		System.out.println("������"+redis.sunion("sx","sy"));
		
		
		
	}
	

}
