package com.lquan.redis.test;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestRedisAPI {
	
	public static void main(String[] args) throws Exception {
		// ����redis
		Jedis jedis = new Jedis("192.168.207.203", 6379);
		// �ж��Ƿ��Ѿ�����
			String pong =  jedis.ping();
			System.out.println("**"+pong);
		
		// ����key ��صĳ���
		//testKey( jedis);
			
		//���Բ���String ���͵�����
		//	testString(jedis);
		
	}
	
	// ����String�������ݵķ���
	/**
	 * 
	 * set/get/del/append/strlen
	 *Incr/decr/incrby/decrby,һ��Ҫ�����ֲ��ܽ��мӼ�
	 *getrange/setrange
	 *setex(set with expire)����ֵ/setnx(set if not exist)
	 *mset/mget/msetnx
	 * getset(��get��set)
	 * @param jedis
	 * @throws Exception
	 */
	public static void testString(Jedis redis) throws Exception {
		// ����Ԫ��  -c �η������ص���״̬�ϣ�
		String a = redis.set("setStringkey1", "stringkey1��");
		System.out.println(a);
		
		// ��ȡ��Ӧ��key
		String getStringKey = redis.get("setStringkey1");
		System.out.println("��ȡ��Ӧ��key: "+getStringKey);
		
		// ɾ������
		//redis.del("setStringkey1");
		System.out.println("ɾ�����ݲ�ѯ��Ӧ��ֵ��: "+ redis.get("setStringkey1"));
		
		// ƴ��
		redis.set("setStringkey2", "Stringkey2");
		System.out.println("ƴ��ǰ��Ӧ��ֵ�� "+ redis.get("setStringkey2"));
		redis.append("setStringkey2", "xxxx");
		System.out.println("ƴ�Ӻ��Ӧ��ֵ�� "+ redis.get("setStringkey2"));
		
		// ��ȡ�ַ����ĳ���
		Long l = redis.strlen("setStringkey2");
		System.out.println("��ȡ�ַ����ĳ���:"+l);
		
		// incr ������������
		redis.set("countincr","0");
		long count = redis.incr("countincr");
		System.out.println("incr ������������"+count);
		
		// decr �����ݼ�
		long decrcount = redis.decr("countincr");
		System.out.println("decrcount ���������ݼ���"+decrcount);
		
		// incrby ָ������
		Long incrBycount = redis.incrBy("countincr", 5);
		System.out.println("ncrby ָ�����ӣ�"+incrBycount);
		
		//decrby ָ������
		Long decrBycount = redis.decrBy("countincr", 2);
		System.out.println("decrby ָ�����٣�"+decrBycount);
		
		redis.set("getrange", "getrangexyz123");
		// getrange ����
		String getrange = redis.getrange("getrange", 2, 3); 
		System.out.println("getrange ����"+getrange);
		
		// setrang ָ����λ�ñ��滻��
		Long setrang = redis.setrange("getrange", 8, "lquan");
		System.out.println("ָ����λ�ñ��滻��"+ redis.getrange("getrange", 0, -1) );
		
		//  setex ������Чʱ��
		redis.setex("getrange", 15, "����ʱ��Ϊ15s");
		System.out.println("�鿴getrange����Чʱ��:"+ redis.ttl("getrange") +"   key��"+redis.get("getrange") );
	//	Thread.sleep(10000);
		System.out.println("10���鿴getrange����Чʱ��:"+ redis.ttl("getrange") +"   key��"+redis.get("getrange") );
	//	Thread.sleep(6000);
		System.out.println("16���鿴getrange����Чʱ��:"+ redis.ttl("getrange") +"   key��"+redis.get("getrange") );
	
		// setnx ������ֻ���ڶ�Ӧ��keyֵ�����ڵ�����²�����ӽ���
		redis.set("getrangexx", "getrangexx");
		Long aa = redis.setnx("getrangexx", "xxxxx");
		System.out.println("״̬��"+aa+"��ѯֵ"+redis.get("getrangexx"));
		System.out.println("������ǰ"+redis.get("getrangexxlquan"));
		Long aaa = redis.setnx("getrangexxlquan", "getrangexxlquan");
		System.out.println("״̬��"+aaa+"��ѯֵ"+redis.get("getrangexxlquan"));
		
		 // ��ֵ����
		redis.mset(new String[]{"kx1","vx1","kx2","vx2"});
		
		//��ȡ��ֵ
		List<String> mgetList = redis.mget(new String[]{"kx1","kx2"});
		System.out.println(mgetList);
		
		// ����
		redis.msetnx(new String[]{"kx1","vx3","kx2","vx4"});
		System.out.println("���������ֵ��"+redis.mget(new String[]{"kx1","kx2"}));
		redis.msetnx(new String[]{"kxy1","vx3","kxy2","vx4"});
		System.out.println("�����������ֵ��"+redis.mget(new String[]{"kxy1","kxy2"}));
		
		// getset���� ��Ҫ����setǰ��ȡ֮ǰ��ֵ������key�������
		System.out.println(" getset����ֵ��"+redis.getSet("kxyx1","kxy2"));
		System.out.println(" getset����ֵ��"+redis.getSet("kxyx1","kxy2"));
		
		
		/**
		 * set/get/del/append/strlen
		 *Incr/decr/incrby/decrby,һ��Ҫ�����ֲ��ܽ��мӼ�
		 *getrange/setrange
		 *setex(set with expire)����ֵ/setnx(set if not exist)
		 *mset/mget/msetnx
		 * getset(��get��set)
		 */
		
	}
	
	// ����key��صĳ���
	public static void testKey(Jedis jedis) throws Exception {
		// ������е�key keys *
				Set<String> keys  = jedis.keys("*");
				for(String key:keys){
					System.out.println("key:"+key+" <->value:"+jedis.get(key));
				}
		// exists key ����	
				if(jedis.exists("k3")){
						System.out.println("����k3");
				}else{
						System.out.println("������k3");
				}
		// move key db �ƶ�ָ����key��db��
			jedis.move("k1", 1);
				
		// expire key time ��ָ����key����ʱ�� ��ttl key����Чʱ��
			System.out.println("ִ��ǰ��"+jedis.ttl("k3") +"��ȡ��ǰ��value   "+jedis.get("k3"));
			jedis.expire("k3",10 );//�趨10��
			Thread.sleep(1000*5); //�趨5��˯��
			System.out.println("ִ���У�"+jedis.ttl("k3") +"��ȡ��ǰ��value   "+jedis.get("k3"));
			Thread.sleep(1000*7); //�趨5��˯��	
			System.out.println("���ں�"+jedis.ttl("k3") +"��ȡ��ǰ��value   "+jedis.get("k3"));
			
			// ��ȡkey������
			System.out.println(jedis.type("k2"));
	}
	
	//

}
