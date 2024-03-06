package com.may54ther.mvc.common.mybatis;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {

        if (sqlSessionFactory==null) {
            String resource = "mybatis/mybatis-config.xml";
            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sqlSessionFactory.openSession(false);
    }

    public static void commit(SqlSession sqlSession) {
        sqlSession.commit();
        sqlSession.close();
    }

    public static void rollback(SqlSession sqlSession) {
        sqlSession.rollback();
        sqlSession.close();
    }
}
