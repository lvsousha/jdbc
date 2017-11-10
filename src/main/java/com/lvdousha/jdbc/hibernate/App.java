package com.lvdousha.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataBuilder;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.metamodel.SessionFactoryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import com.lvdousha.jdbc.hibernate.model.Employee;

public class App 
{
    public static void main( String[] args ){
    	App app = new App();
    	SessionFactory sf = app.createSessionFactory();
    	Session session = sf.openSession();
    	Employee e = new Employee();
    	e.setFirstName("firstName");
    	e.setLastName("lastName");
    	e.setPhone("phone");
    	e.setSalary(100);
    	session.save(e);
    	System.out.println("OUT");
    	session.close();
    	
    	
    }
    
    public void bootatrap(){
    	StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder();
    	// 通过资源查找加载某些属性（译者注：属性文件方式加载 ）
    	standardRegistryBuilder.loadProperties( "hibernate.properties");
    	// 从 cfg.xml 配置文件来配置注册信息
    	standardRegistryBuilder.configure( "hibernate.cfg.xml" );
    	// 应用随机设置
    	standardRegistryBuilder.applySetting( "myProp", "myValue" );
    	// 最后建立 StandardServiceRegistry
    	StandardServiceRegistry standardRegistry = standardRegistryBuilder.build();
    	
    	//这个对象包括程序域模型的解析结果并且这些结果映射到数据库中
    	MetadataSources sources = new MetadataSources(standardRegistry);
    	MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
    	// 使用 JPA 兼容的隐式的命名策略
//    	metadataBuilder.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE );
    	// 当没有显示指定时，用表的 schema 名
//    	metadataBuilder.applyImplicitSchemaName( "my_default_schema" );
    	Metadata metadata = metadataBuilder.build();
        
    	SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
		// 提供一个 SessionFactory-level 拦截器
//		sessionFactoryBuilder.applyInterceptor( new MySessionFactoryInterceptor());
		// 添加自定义观察器
//		sessionFactoryBuilder.addSessionFactoryObservers( new MySessionFactoryObserver());
		// 应用 CDI BeanManager（Bean 管理器）（JPA 事件监听器）
//		sessionFactoryBuilder.applyBeanManager( getBeanManagerFromSomewhere());
		
		SessionFactory sessionFactory = sessionFactoryBuilder.build();
		
		sessionFactory.close();
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
