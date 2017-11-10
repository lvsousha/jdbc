package com.lvdousha.jdbc.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

public class App 
{
    public static void main( String[] args ){
    	
        
        
        
        
        
    }
    
    public SessionFactory createSessionFactory(){
    	Configuration cfr = new Configuration().configure();
    	StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().configure();  
    	ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();  
//    	Metadata metadata = new MetadataSources( serviceRegistry ).buildMetadata();
//    	metadata.getSessionFactoryBuilder().build();
    	SessionFactory sf = cfr.buildSessionFactory(serviceRegistry);
    	return sf;
    }
    
    public void createTableInDatabase(){
    	Configuration cfr = new Configuration().configure();
    	String createType = cfr.getProperty("hibernate.hbm2ddl.auto");
    	if(createType == null || createType.equals("")){
    		createType = "create";
    	}
    	if(createType.equals("create")){
    		SchemaExport export = new SchemaExport(cfr);  
            export.create(true, true);
    	}else if(createType.equals("update")){
    		SchemaUpdate update = new SchemaUpdate(cfr);
            update.execute(true, true);
    	}
    }
}
