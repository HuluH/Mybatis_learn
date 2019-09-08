package com;

import com.test.dao.IAccountDao;
import com.test.dao.IUserDao;
import com.test.entry.Account;
import com.test.entry.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

public class one2ManyTest {
    private static final Logger logger = LoggerFactory.getLogger(one2ManyTest.class);
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
    public void findAllAccount() throws Exception {
        init();
        List<User> users = dao.findUserAndAccount();
        if (users.size() == 0) {
            logger.info("无满足条件的记录");
        } else {
            for (User user : users) {
                logger.info(user.toString());
            }
        }
        destory();
    }
}
