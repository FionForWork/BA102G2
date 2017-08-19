package com.product.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ProductIdentifierGenerator implements IdentifierGenerator {

    
    @Override
    public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
        String prefix="4";
        String pro_no=null;
        Connection connection=arg0.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT PRO_NO_SEQ.NEXTVAL as nextval FROM DUAL");
            resultSet.next();
            int nextval = resultSet.getInt("nextval");
            pro_no = prefix+String.format("%03", nextval);
            connection.close();
        } catch (SQLException e) {
            throw new HibernateException("Unable to generate Sequence");
        }
        return pro_no;
    }

}
