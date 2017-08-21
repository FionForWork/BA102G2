package com.placeview.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PlaceViewIdentifierGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
        String view_no=null;
        Connection connection=arg0.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT VIEW_NO_SEQ.NEXTVAL as nextval FROM DUAL");
            resultSet.next();
            view_no =resultSet.getString(1);
            connection.close();
        } catch (SQLException e) {
            throw new HibernateException("Unable to generate Sequence");
        }
        return view_no;
    }

}
