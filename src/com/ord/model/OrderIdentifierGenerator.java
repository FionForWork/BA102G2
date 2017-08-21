package com.ord.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OrderIdentifierGenerator implements IdentifierGenerator {

    
    @Override
    public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
        String ord_no=null;
        Connection connection=arg0.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ORD_NO_SEQ.NEXTVAL as nextval FROM DUAL");
            resultSet.next();
            ord_no =resultSet.getString(1);
            connection.close();
        } catch (SQLException e) {
            throw new HibernateException("Unable to generate Sequence");
        }
        return ord_no;
    }

}
