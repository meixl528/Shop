package Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssm.dto.User;

import net.sf.json.JSONArray;

/** 
 * @author 一定要记住，sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素的顺序。原来string集合中的元素顺序是没有改变的
 * @author 链接: <a href ="http://www.cnblogs.com/qdwyg2013/p/5631057.html">http://www.cnblogs.com/qdwyg2013/p/5631057.html</a>
 */
public class ObjectMapper {
	
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		List<User> list = new ArrayList<>();
		User user =null;
		for (int i = 1; i <5; i++) {
			user = new User("meix_"+i,"123","a"+(5-i),"上海+"+i);
			list.add(user);
		}
		String json =JSONArray.fromObject(list).toString();
		System.out.println("json = "+json);
		
		com.fasterxml.jackson.databind.ObjectMapper objectMapper= new com.fasterxml.jackson.databind.ObjectMapper();
		
		com.fasterxml.jackson.databind.type.CollectionType l=objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class);
		
		List<User> lu = objectMapper.readValue(json, l);
	
		/*for(User u:lu){
			System.out.println(u.toString());
		}*/
		
		/** <p>
	     * 一定要记住，sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素的顺序。原来string集合中的元素顺序是没有改变的
	     */
		lu.stream().sorted(Comparator.comparing(User::getTelephone)).filter((s) -> s.getTelephone().startsWith("a")).forEach(System.out::println);
		System.out.println("lu = "+lu); 
		
		
		List<Integer> sList = new ArrayList<>();
		sList.add(8);sList.add(9);
		sList.add(6);sList.add(7);sList.add(5);
		
		sList.stream().sorted().forEach((v) -> System.out.print(v +" "));
		System.out.println(); 
		System.out.println("sList = "+sList); 
		
		Map<String,Object> map = new HashMap<>();
		map.put("name", "111"); map.put("pass", 123);
		String o = objectMapper.writeValueAsString(map);
		System.out.println(o); 
		
		
	}

}
