package com.szl.test;

import com.szl.dao.UserMapper;
import com.szl.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

// 测试文件
public class MybatisTest {
    @Test
    public void testUpdateUser() throws IOException {
        //基于配置文件路径，创建字节输入流对象
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //创建SqlSessionFactory(SQL会话工厂) 工厂类对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is); // 固定代码

        //使用SqlSessionFactory工厂类,创建SqlSession对象
        //SQL会话 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true); // 打开会话

        //利用SqlSession对象，创建一个代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 根据id查询用户
        User user = userMapper.findUserById(6);
        // 修改用户对象中的信息
        user.setUsername("小白龙");
        user.setAddress("东海");

        int rowCount = userMapper.updateUser(user);

        if (rowCount > 0){
            sqlSession.commit();
            System.out.println("修改数据成功");
        }
    }

    @Test
    public void testAddUser() throws IOException {
        //基于配置文件路径，创建字节输入流对象
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //创建SqlSessionFactory(SQL会话工厂) 工厂类对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is); // 固定代码

        //使用SqlSessionFactory工厂类,创建SqlSession对象
        //SQL会话 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true); // 打开会话 true-把手动事务修改为：自动事务


        //利用SqlSession对象，创建一个代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User(null,"白龙马2",new Date(System.currentTimeMillis()),"男","东海龙宫");
        //使用代理对象，调用方法
        int i = userMapper.addUser(user);

        if (i > 0){
            //在mybatis中,默认的事务:手动事务
//          //sqlSession.commit();
            System.out.println("数据插入成功!");}

        //释放资源
        sqlSession.close();
    }

    @Test
    public void testFindUserById() throws IOException {
        //基于配置文件路径，创建字节输入流对象
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //创建SqlSessionFactory(SQL会话工厂) 工厂类对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is); // 固定代码

        //使用SqlSessionFactory工厂类,创建SqlSession对象
        //SQL会话 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开会话

        //利用SqlSession对象，创建一个代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //使用代理对象，调用方法
        User user = userMapper.findUserById(1);

        System.out.println(user);

        //释放资源
        sqlSession.close();
    }
    @Test
    public void testFindAllUser() throws IOException {
        //配置文件路径
        String resource = "mybatis-config.xml"; //*核心*配置文件, 没有读取*映射*配置文件

        //基于配置文件路径，创建字节输入流对象
        InputStream is = Resources.getResourceAsStream(resource);

        //创建SqlSessionFactory(SQL会话工厂) 工厂类对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is); // 固定代码

        //使用SqlSessionFactory工厂类,创建SqlSession对象
        //SQL会话 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开会话

        //利用SqlSession对象，创建一个代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /*
           Class[] interfaces = {UserMapper.class};

           Porxy.newProxyInstance(
                类加载器,
                interfaces,
                new InvocationHandler(){
                    //重写invoke方法
                    public Object  invoke( Object proxy , Method method , Object[] args ){
                          //读取相同目录下的UserMapper.xml

                          String methodName = method.getName(); //获取方法名

                          //xpath解析xml :    路径表达式  = //select[@id=findAllUser]

                          //解析出： select标签的resultType属性值
                          Class cls = Class.forName("com.itheima.pojo.User");

                          //解析出： select标签体内容（sql语句）  select id, username, birthday, sex, address from user
                          执行sql查询语句

                          //遍历查询结果
                            查询结果封装到User对象中
                    }
                }
           )
        * */
        //使用代理对象，调用方法查询用户数据
        List<User> userList = userMapper.findAllUser();

        //测试用户数据
        for (User user : userList) {
            System.out.println(user);
        }

        //释放资源
        sqlSession.close();
    }
}
