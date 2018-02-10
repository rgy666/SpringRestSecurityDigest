package org.restful.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.restful.messenger.dao.MessageDao;
import org.restful.messenger.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller											//@Controller annotation indicates that a particular class serves the role of a controller
@RequestMapping("/api/messages")								//@RequestMapping annotation is used to map a URL to either an entire class or a particular handler method
@Consumes(MediaType.APPLICATION_JSON)								//Input is in JSON format
@Produces(MediaType.APPLICATION_JSON)								//The response is of type JSON
public class MessagaResource {

	private MessageDao messageDao = new MessageDao();
	
	    
	static final Logger logger = Logger.getLogger(MessagaResource.class);			//enabling logger for class MessageResource

	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody										//indicates that the return type should be written straight to the HTTP response body			
	public List<Message> getMessages()
	{
		logger.debug("No Error log4j");
		return messageDao.getAllMessages();
	}
	
	
	@RequestMapping(value = "/{messageId}", method = RequestMethod.GET)
	@ResponseBody	
	public Message getMessage(@PathVariable("messageId") Integer msgID)			//@PathVariable annotation on a method argument to bind it to the value of a URI template variable
	{
		return messageDao.getMessageById(msgID);
	}
	
	
	@RequestMapping(value="/", method = RequestMethod.POST)					//HTTP method to add a new data to the database
	@ResponseBody
	public Message saveNewMessage(Message message)
	{
		return messageDao.saveMessage(message);
		
	}
	
	
	@PUT											//HTTP method to modify a specific resource
	@Path("/{messageID}")
	public Message updateNewMessage(@PathVariable("messageID") int msgID, Message message)
	{
		message.setId(msgID);
		return messageDao.updateMessage(message);
		
	}
	
	
	@RequestMapping(value="/{messageId}", method = RequestMethod.DELETE)			//HTTp method to delete resource
	@ResponseBody
	public void deleteMessage(@PathVariable("messageId") int msgID)
	{
		System.out.println("removed");
		messageDao.removeMessage(msgID);
		System.out.println("specified id");
	}
	
}
