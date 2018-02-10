package org.restful.messenger.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.restful.messenger.model.Message;
import org.restful.messenger.util.HibernateUtil;

public class MessageDao {
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	
	public Message getMessageById(Integer id)
	{
		Message message = null;
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
											//select message with the passed messageID from the database and return it
			message = (Message) session.createQuery("from Message m where m.id = :messageID").setParameter("messageID",id).uniqueResult();
			session.getTransaction().commit();
			
		}
		
		catch(Exception ex){
			if(session != null)
			{
				session.getTransaction().rollback();			//rollback to erase all data modifications made from the start of the transaction or to a savepoint.
			}
		}
		
		finally{
			if(session != null)
			{
				session.flush();
				session.close();
			}
			
		}
		return message;
	}
	
	
	public List<Message> getAllMessages()
	{
		List<Message> messages = null;
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			messages = session.createCriteria(Message.class,"message").list();		//create list by extraxting information from the Message class
			session.getTransaction().commit();
			
		}
		
		catch(Exception ex){
			if(session != null)
			{
				session.getTransaction().rollback();
			}
		}
		
		finally{
			if(session != null)
			{
				session.flush();
				session.close();
			}
			
		}
		return messages;
	}
	
	
	public Message saveMessage(Message message)
	{
		List<Message> messages = null;
		Session session = null;
		boolean hasErrors = false;
		
		try{		
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			messages = session.createCriteria(Message.class,"message").list();		//create list by extraxting information from the Message class
			session.getTransaction().commit();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(message);							//Save or Update operation is performed as per the transaction carried out
			session.getTransaction().commit();
			
			message.setId(messages.size()+1);						//set the messageID
			messages.add(message);								//add the message to the list
			
		}
		catch(Exception ex){
			if(session != null)
			{
				session.getTransaction().rollback();
			}
			hasErrors = true;
		}
		finally{
			if (session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return message;
	}
	
	public Message updateMessage(Message message)
	{
		List<Message> messages = null;
		Session session = null;
		boolean hasErrors = false;
		
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(message);
			session.getTransaction().commit();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			messages = session.createCriteria(Message.class,"message").list();
			session.getTransaction().commit();
			
			if(message.getId()<=0)									//verify if mentioned ID is valid
			{
				return null;
			}
			
			messages.add((int) message.getId(), message);
			
		}
		catch(Exception ex){
			if(session != null)
			{
				session.getTransaction().rollback();
			}
			hasErrors = true;
		}
		finally{
			if (session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return message;
	}
	
	
	public Message removeMessage(int id)
	{
		List<Message> messages = null;
		Message message = new Message();;
		Session session = null;
		boolean hasErrors = false;
		
		try{	
			session = sessionFactory.openSession();
			session.beginTransaction();
			message = (Message) session.load(Message.class,id);  
			session.delete(message);  								//delete message
			session.getTransaction().commit(); 
			
		}
		catch(Exception ex){
			if(session != null)
			{
				session.getTransaction().rollback();
			}
			hasErrors = true;
		}
		finally{
			if (session != null)
			{
				session.flush();
				session.close();
			}
		}
		
		return message;
	}
	

}
