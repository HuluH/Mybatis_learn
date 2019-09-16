package com;


import com.test.dao.IUserDao;
import com.test.entry.User;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
        user.setId(7);
        user.setAddress("北京市西城区");
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
    public void findHalf() throws Exception {
        init();
        List<User> users = dao.findHalf("%李%");
        for (User user : users) {
            logger.info(user.toString());
        }
        destory();
    }

    @Test
    public void findCondition() throws Exception {
        init();
        User user = new User();
        user.setUsername("张三");
        user.setSex('F');
        List<User> users = dao.findCondition(user);
        if (users.size() == 0) {
            logger.info("查无此人");
        } else {
            for (User u : users) {
                System.out.println(u.toString());
            }
        }
        destory();
    }

    @Test
    public void findCOnditionList() throws Exception {
        init();
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(8);
        idList.add(12);
        List<User> users = dao.findConditionList(idList);
        if (users.size() == 0) {
            logger.info("查无此人");
        } else {
            for (User u : users) {
                System.out.println(u.toString());
            }
        }
        destory();
    }

    @Test
    public void insertList() throws Exception {
        init();
        List<User> users = new LinkedList<User>();
        User user1 = new User();
        user1.setUsername("波莉");
        user1.setSex('女');
        user1.setBirthday(new Date());
        user1.setAddress("伯明翰小希斯");
        users.add(user1);

        User user2 = new User();
        user2.setUsername("艾达");
        user2.setSex('女');
        user2.setBirthday(new Date());
        user2.setAddress("伯明翰小希斯");
        users.add(user2);

        User user3 = new User();
        user3.setUsername("格蕾丝");
        user3.setSex('女');
        user3.setBirthday(new Date());
        user3.setAddress("伦敦");
        users.add(user3);

        dao.insertList(users);

        session.commit();
        destory();
    }

//    public static void main(String[] args) {
//        User user = new User();
//        user.setAddress("111");
//        user.setBirthday(new Date());
//        user.setSex('n');
//        user.setUsername("111");
//
//        List<User> list = new LinkedList<User>();
//        list.add(user);
//
//        System.out.println(list);
//
//        user.setSex('f');
//
//        System.out.println(list);
//    }
}