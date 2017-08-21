package com.album.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class AlbumGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		String alb_no = null;
		Connection conn = session.connection();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select ltrim(To_char(alb_sq.nextval,'0009')) as nextval from dual");
			rs.next();
			alb_no = rs.getString("nextval");
			conn.close();
		}catch(SQLException e){
			throw new HibernateException("Unable to generate Sequence");
		}
		return alb_no;
	}

}
