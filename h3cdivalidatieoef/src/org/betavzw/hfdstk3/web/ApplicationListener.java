package org.betavzw.hfdstk3.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
	@Resource(name="jdbc/__default")
	private DataSource ds;
    public void contextDestroyed(ServletContextEvent arg0)  {
    	try(Connection conn = ds.getConnection();
    			Statement st = conn.createStatement()){
    		st.executeUpdate("DROP TABLE cursus");
    	}catch(SQLException e){
    		throw new RuntimeException(e);
    	}
    }

    public void contextInitialized(ServletContextEvent arg0)  {
    	try(Connection conn = ds.getConnection();
    			Statement st = conn.createStatement()){
    		st.executeUpdate("CREATE TABLE cursus (code char(4) primary key, naam varchar(50), duur int)");
    		st.executeUpdate("INSERT INTO cursus VALUES ('JAV1', 'Java Inleiding', 2), ('JAV2', 'Java Vervolmaking', 2)");
    	}catch(SQLException e){
    		throw new RuntimeException(e);
    	}
    }
	
}
