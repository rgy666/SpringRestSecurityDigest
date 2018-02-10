package org.restful.messenger.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity												
@Table (name="messages")							//This class refers to the table with name "messages"
public class Message {

	@Id @GeneratedValue							//The id variable here is an autoincremented value and is the primary key for the table
	@Column (name="messageID")						//the column name from the database that corresponds to the variable id in this POJO class is messageID
	private int id;
	
	@Column (name="Message")						//the column name from the database that corresponds to the variable message this POJO class is Message
	private String message;
	
	@Column (name="Author")							//the column name from the database that corresponds to the variable author this POJO class is Author
	private String author;
	
	public Message(){}
	
	public Message(int id, String message, String author) {
		this.id = id;
		this.message = message;
		this.author = author;
	}
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
}
