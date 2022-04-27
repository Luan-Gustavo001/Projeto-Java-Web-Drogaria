package br.com.luan.drogaria.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();
	
	private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
	
	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	static SessionFactory criarFabricaDeSessoes() {
		try {
			// Create registry
            registry = new StandardServiceRegistryBuilder().configure().build();

            // Create MetadataSources
            MetadataSources sources = new MetadataSources(registry);

            // Create Metadata
            Metadata metadata = sources.getMetadataBuilder().build();

            // Create SessionFactory
            sessionFactory = metadata.getSessionFactoryBuilder().build();
			
            return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("A fábrica de sessões não pode ser criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}