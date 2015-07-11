package persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static Configuration config = new AnnotationConfiguration().configure();
	
	static{
		try{
			sessionFactory = config.buildSessionFactory();			
		} catch (Throwable ex){
			throw new ExceptionInInitializerError(ex);
		}
	}
	
		
	public static SessionFactory getSessionFactory(){
		//Alternatively, you could look up in JNDI here
		
		return sessionFactory;
	}
	
	public static void shutdown(){
		//close caches and connection pools
		sessionFactory.close();
	}
}
