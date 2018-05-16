package com.lvdousha.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisUtilTest {
	private static final Charset UTF_8 = Charset.forName("utf-8");
	
	public static void main(String[] args) {
		RedisUtilTest rut = new RedisUtilTest();
//		rut.testString();
//		rut.testMap();
//		rut.testSet();
		rut.testList();
	}

	public void testString() {
		System.err.println("测试String类型开始>>\r\n\t");

		String key = "Program Name";
		String value = "Redis For Windows";
		String value1 = "Input Redis For bytes";
		RedisUtils.setString(key, value);
		RedisUtils.setBytes(key.getBytes(UTF_8), value1.getBytes(UTF_8));

		System.out.println("从Redis中获取name:>>>\r\n\t");
		String val = RedisUtils.getString(key);
		System.out.println("输出:\r\n\t" + val);

		System.out.println("从Redis中获取name bytes:>>>>\r\n\t");
		byte[] bytes = RedisUtils.getBytes(key.getBytes(UTF_8));
		System.out.println("输出bytes:\r\n\t" + Arrays.toString(bytes));
		val = new String(bytes, UTF_8);
		System.out.println("转换后String:\r\n\t" + val);

		System.out.println("删除name的键:\r\n\t");
		RedisUtils.delString(key);
		val = RedisUtils.getString(key);
		System.out.println("再次获取:" + (val == null ? "该键已被删除.." : val));
	}

	public void testMap() {

		System.err.println("测试Redis For Map 开始:>>>>");

		// 简单的string map
		Map<String, String> strMap = new HashMap<String, String>();
		// 复杂点的map
		Map<byte[], byte[]> bytesMap = new HashMap<byte[], byte[]>();

		// 测试储存新的地址
		strMap.put("OS", "Windows 10");
		strMap.put("Language", "ch");
		strMap.put("Tool", "Redis For Windows");
		String skey = "String For Redis";
		RedisUtils.addMap(skey, strMap);

		// 从获取所有的值
		List<String> sList = RedisUtils.getMapVal(skey);
		System.out.println("所有结果值:" + sList);

		// 按照给出的field顺序给出值
		sList = RedisUtils.getMapVal(skey, "Tool", "OS", "Language", "dd");
		// 发现取出的值和输入的field的顺序一致
		System.out.println("输出值[Tool, OS, Language, dd]:\r\n\t" + sList);

		// 尝试在Redis中存储对象
		Person person = new Person("Johnny", 23, "男");
		// 序列化对象
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		ObjectInputStream bis = null;
		try {
			oos = new ObjectOutputStream(baos);
			// 创建对象
			oos.writeObject(person);
			// 获取序列化之后的字节码
			byte[] bytes = baos.toByteArray();
			bytesMap.put(person.getName().getBytes(UTF_8), bytes);
			RedisUtils.addMap(person.getName().getBytes(UTF_8), bytesMap);

			// 从Redis中读取对象
			List<byte[]> list = RedisUtils.getMapVal(person.getName().getBytes(UTF_8), person.getName().getBytes(UTF_8));
			if (list.size() == 1) {
				bytes = list.get(0);
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				bis = new ObjectInputStream(bais);
				Person p = (Person) bis.readObject();
				System.out.println("获取到对象:" + p);

				bais.close();
				bis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (baos != null) {
					baos.close();
				}
				if (null != oos) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 向已经存在的key中新增键值对
		RedisUtils.addMapVal(person.getName().getBytes(UTF_8), "AddTest".getBytes(UTF_8),
				"Test Redis Adding A Val For Exist Key".getBytes(UTF_8));
		// 获取刚插入的值
		System.out.println("获取刚插入的值:\r\n\t"
				+ new String(RedisUtils.getMapVal(person.getName().getBytes(UTF_8), "AddTest".getBytes(UTF_8)).get(0)));

		// 尝试向不存在的Key中插值
		RedisUtils.addMapVal("AddNewKey", "AddNewMapKey", "AddNewMapVal");
		// 能够获取到值，因此也说明在进行不存在key的插值时，会自动创建对象的键值对以保存。
		System.out.println("尝试获取刚插入的值:\r\n\t" + RedisUtils.getMapVal("AddNewKey", "AddNewMapKey"));
	}

	public void testSet() {
		System.err.println("测试Redis For Set 开始:>>>>>>>");
		// 向Redis添加元素
		RedisUtils.addSet("AddNewSet", "set1", "set2", "set3");
		// 获取set中的值
		System.out.println("Set集合的长度:\r\n\t" + RedisUtils.getSetLength("AddNewSet"));
		System.out.println("Set集合元素:\r\n\t" + RedisUtils.getSetVals("AddNewSet"));
		// 尝试移除元素
		RedisUtils.delSetVal("AddNewSet", "set2");
		System.out.println("Set集合的长度:\r\n\t" + RedisUtils.getSetLength("AddNewSet"));
		System.out.println("Set集合元素:\r\n\t" + RedisUtils.getSetVals("AddNewSet"));

		// 判断是否包含元素
		System.out.println("是否包含set2的值:" + RedisUtils.isSetContain("AddNewSet", "set2"));
		System.out.println("是否包含set2的值:" + RedisUtils.isSetContain("AddNewSet", "set3"));
	}

	public void testList() {
		System.err.println("测试Redis For List 开始:>>>>>>");
		// 向List中添加元素
		RedisUtils.addList("ValList", "List1", "List2", "List3");
		// 获取List中的值
		System.out.println("Redis For List中的值为:" + RedisUtils.getListAll("ValList"));
		// 弹出list的第一个元素
		System.out.println("弹出第一个元素:" + RedisUtils.popList("ValList"));
		System.out.println("Redis For List中的值为:" + RedisUtils.getListAll("ValList"));
	}
}

