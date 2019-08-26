package com;


import com.test.dao.IUserDao;
import com.test.entry.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class testTest {

    private static final Logger logger = LoggerFactory.getLogger(testTest.class);
    private InputStream in;
    private SqlSession session;
    private IUserDao dao;

    private void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        dao = session.getMapper(IUserDao.class);
    }

    private void destory() throws Exception {
        session.close();
        in.close();
    }

    @Test
    public void findAll() throws Exception {
        init();
        List<User> users = dao.findAll();

        for (User user : users) {
            logger.info(user.toString());
        }
        destory();
    }

    @Test
    public void findOne() throws Exception {
        init();
        User user = dao.findOne(3);
        if (user == null) {
            logger.info("查无此人");
        } else {
            logger.info(user.toString());
        }
        destory();
    }

    @Test
    public void add() throws Exception {
        init();
        User user = new User();
        user.setUsername("孙琪");
        user.setBirthday(new Date());
        user.setSex('女');
        user.setAddress("北京市昌平区");
        dao.add(user);
        session.commit();
        System.out.println(user.getId());
        destory();
    }

    @Test
    public void update() throws Exception {
        init();
        User user = new User();
        user.setId(8);
        user.setUsername("李恒利");
        dao.update(user);
        session.commit();
        destory();
    }

    @Test
    public void delete() throws Exception {
        init();
        dao.delete(3);
        session.commit();
        destory();
    }

    @Test
    public void findHalf() throws Exception{
        init();
        List<User> users = dao.findHalf("%李%");
        for (User user : users) {
            logger.info(user.toString());
        }
        destory();
    }
}
