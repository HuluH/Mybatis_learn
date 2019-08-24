package com.test;

import com.test.dao.IUserDao;
import com.test.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws Exception {
        //读取配置文件
        //一般读取配置文件的两种方式 绝对路径和相对路径，但是都会存在弊端
        //开发中常用的读取配置文件的方法一般有两种：
        //使用类加载器，只能读取类路径的配置文件
        //使用ServletContext对对象的getRealPath()
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建slqsessionfactory，
        // 此处使用了构建者模式，builder就是那个构建者，
        // 构建者模式的优势：把对象的创建细节进行隐藏，使使用者只需调用方法即可拿到对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //利用sqlSessionFactory创建sqlSession，
        // 此处使用了工厂模式
        // 工厂模式的优势: 解耦
        SqlSession session = factory.openSession();
        //利用sqlSession创建dao的对象，
        // 此处使用了代理模式
        // 代理模式的优势：在不改变源码的基础上对方法进行增强
        IUserDao dao = session.getMapper(IUserDao.class);
        //执行方法
        List<User> users = dao.findAll();
        for (User user : users) {
            logger.info(user.toString());
        }
        //关闭资源
        session.close();
        in.close();
    }
}
