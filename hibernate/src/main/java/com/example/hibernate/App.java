package com.example.hibernate;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App 
{
public static void main(String[] args) {
		
		Scanner src = new Scanner(System.in);
		
		int ch;
		
		do {
//			System.out.println("*");
			
			displaymenu();
			ch = Integer.parseInt(src.nextLine());
			
			switch (ch) {
			case 1:
				insertion(null, null);
				break;
			case 2:
				delete(0);
				break;
			case 3:
				update();
				break;
			case 4:
				getall();
				break;
			case 5:
				getbyid();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("invalid operation");
				break;
				
			}
		}
		while(ch>0);
	}

	private static void getbyid() {
		
		Scanner src = new Scanner(System.in);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		Session s = sf.openSession();
		
		System.out.println("Enter id: ");
		
		int id = src.nextInt();
		
		Transaction t = s.beginTransaction();
		
		employee d = s.get(employee.class, id);
		
		if(d!=null) {
			System.out.println("id: "+d.getId());
			
			System.out.println("name: "+d.getName());
			
			System.out.println("email: "+d.getEmail());
		}
		
	}

	public static void getall() {
		
		Scanner src = new Scanner(System.in);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		
		List<employee> li = s.createQuery("from devi",employee.class).list();
		
		t.commit();
		
		
		for(employee d:li) {
			
			System.out.println("Id: "+d.getId());
			
			System.out.println("Name: "+d.getName());
			
			System.out.println("Email: "+d.getEmail());
		}
		
	}

	public static void update() {
		// TODO Auto-generated method stub
		
	}

	public static void delete(int id) {
		Scanner src = new Scanner(System.in);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		Session s = sf.openSession();
		
		System.out.println("Enter id: ");
		
		int Id = src.nextInt();
	
		Transaction t = s.beginTransaction();
		
		employee d = s.get(employee.class, Id);
		
		s.delete(d);
		
		t.commit();
		
		System.out.println("successfully Deleted");
		
	}

	public static void insertion(String name, String email) {
		
		Scanner src = new Scanner(System.in);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		employee d = new employee();
		
		System.out.println("Enter name");
		String Name = src.nextLine();
		d.setName(Name);
		
		System.out.println("Enter email");
		String Email = src.nextLine();
		d.setEmail(Email);
		
		s.save(d);
		
		t.commit();
		
		System.out.println("successfully Inserted");
		
	}

	private static void displaymenu() {
		
		System.out.println("_");
		System.out.println("\t1.insertion");
		System.out.println("\t2.delete");
		System.out.println("\t3.update");
		System.out.println("\t4.getall");
		System.out.println("\t5.getbyid");
		System.out.println("\t6.exit");
		
		
	}

}
