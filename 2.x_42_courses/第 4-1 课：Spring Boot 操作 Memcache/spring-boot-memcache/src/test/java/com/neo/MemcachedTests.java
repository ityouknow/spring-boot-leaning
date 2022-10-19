package com.neo;

import com.neo.config.MemcachedBuilder;
import net.rubyeye.xmemcached.*;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedTests {

	@Autowired
	private MemcachedClient memcachedClient;

	@Test
	public void testGetSet() throws Exception {
		memcachedClient.set("hello", 0, "Hello,xmemcached");
		String value = memcachedClient.get("hello");
		System.out.println("hello=" + value);
		memcachedClient.delete("hello");
		value = memcachedClient.get("hello");
		System.out.println("hello=" + value);
	}

	@Test
	public void testMore() throws Exception {
		if (!memcachedClient.set("hello", 0, "world")) {
			System.err.println("set error");
		}
		if (!memcachedClient.add("hello", 0, "dennis")) {
			System.err.println("Add error,key is existed");
		}
		if (!memcachedClient.replace("hello", 0, "dennis")) {
			System.err.println("replace error");
		}
		memcachedClient.append("hello", " good");
		memcachedClient.prepend("hello", "hello ");
		String name = memcachedClient.get("hello", new StringTranscoder());
		System.out.println(name);
		memcachedClient.deleteWithNoReply("hello");
	}

	@Test
	public void testIncrDecr() throws Exception {
		memcachedClient.delete("Incr");
		memcachedClient.delete("Decr");
		System.out.println(memcachedClient.incr("Incr", 6, 12));
		System.out.println(memcachedClient.incr("Incr", 3));
		System.out.println(memcachedClient.incr("Incr", 2));
		System.out.println(memcachedClient.decr("Decr", 1, 6));
		System.out.println(memcachedClient.decr("Decr", 2));
	}

	@Test
	public void testCounter() throws Exception {
		Counter counter=memcachedClient.getCounter("counter1",10);
		System.out.println("counter="+counter.get());
		long c1 =counter.incrementAndGet();
		System.out.println("counter="+c1);
		long c2 =counter.decrementAndGet();
		System.out.println("counter="+c2);
		long c3 =counter.addAndGet(-6);
		System.out.println("counter="+c3);
	}


	@Test
	public void testTouch() throws Exception {
		memcachedClient.set("Touch", 2, "Touch Value");
		Thread.sleep(1000);
		//memcached 不支持
	//	memcachedClient.touch("Touch",6);
		Thread.sleep(2000);
		String value =memcachedClient.get("Touch",3000);
		System.out.println("Touch=" + value);
	}

	@Test
	public void testCas() throws Exception {
		memcachedClient.set("cas", 0, 100);
		GetsResponse<Integer> result = memcachedClient.gets("cas");
		System.out.println("result value "+result.getValue());

		long cas = result.getCas();
		//尝试将a的值更新为2
		if (!memcachedClient.cas("cas", 0, 200, cas)) {
			System.err.println("cas error");
		}
		System.out.println("cas value "+memcachedClient.get("cas"));

		memcachedClient.cas("cas", 0, new CASOperation<Integer>() {
			public int getMaxTries() {
				return 1;
			}

			public Integer getNewValue(long currentCAS, Integer currentValue) {
				return 300;
			}
		});
		System.out.println("cas value "+memcachedClient.get("cas"));

	}

	@Test
	public void testStat() throws Exception {
		Map<InetSocketAddress,Map<String,String>> result=memcachedClient.getStats();
		System.out.println("Stats=" + result.toString());
		Map<InetSocketAddress,Map<String,String>> items=memcachedClient.getStatsByItem("items");
		System.out.println("items=" + items.toString());
	}

}