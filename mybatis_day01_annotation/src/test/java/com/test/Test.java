package com.test;

import com.test.dao.IUserDao;
import com.test.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        //读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建slqsessionfactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //利用sqlSessionFactory创建sqlSession
        SqlSession session = factory.openSession();
        //利用sqlSession创建dao的对象
        IUserDao dao = session.getMapper(IUserDao.class);
        //执行方法
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //关闭资源
        session.close();
        in.close();
    }
}
