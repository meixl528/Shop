package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * @name        DBConnection
 * @description 使用mysql数据库驱动连接数据库
 * @author      meixl
 * @date        2017年6月9日上午11:50:15
 * @version
 */
public class DBConnection {
	
	static String driver = "com.mysql.jdbc.Driver";
	// localhost指本机，也可以用本地ip地址代替，3306为MySQL数据库的默认端口号，“user”为要连接的数据库名
	static String url = "jdbc:mysql://192.168.10.31:3306/springmybaitis";
	// 填入数据库的用户名跟密码
	static String username = "root";
	static String password = "123456";
	static String sql = "select * from tb_user";// 编写要执行的sql语句，此处为从user表中查询所有用户的信息
	
	static User user;
	public DBConnection(){
		if(user ==null){
			user = new User();
		}
	}
	
	//main测试
	public static void main(String[] args) {
		getConn();
	}
	
	public static List<User> queryUser(){
		Connection conn = getConn();
		List<User> list = new ArrayList<>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);// 执行sql语句并返回结果集
			while (rs.next())// 对结果集进行遍历输出
			{
				System.out.println("username:" + rs.getString(2) +"   phone:"+rs.getString("phone"));// 通过列的标号来获得数据
				//存储到user对象
				user.setUsername(rs.getString(2));
				user.setPhone(rs.getString("phone"));
				list.add(user);
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}// 创建sql执行对象
		return list;
		
	}
	
	/**
	 * 连接数据库
	 * @return
	 */
	public static Connection getConn(){
		Connection con = null;
		try {
			Class.forName(driver);// 加载驱动程序，此处运用隐式注册驱动程序的方法
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, username, password);// 创建连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
