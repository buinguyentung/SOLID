package bpackage;

import apackage.Message;
import apackage.MessageBHandler;

public class Client {
	private Message message;
	private MessageBHandler messageBHandler;
	private MessageCHandler messageCHandler;

	public Client() {
		this.message = new Message();
		messageBHandler = new MessageBHandler();
		messageCHandler = new MessageCHandler();
	}

	public void writeAMessage() {
		// System.out.println(message.message_a); // Error. message_a is not visible

		// Access by public get method
		System.out.println(message.getMessageA());
	}

	public void writeBMessage() {
		// System.out.println(message.message_b); // Error. message_b is not visible

		// Access by public method of child class on the same package a
		System.out.println(messageBHandler.getMessageB());
	}

	public void writeCMessage() {
		// System.out.println(message.message_c); // Error. message_c is not visible

		// Access by public method of child class on different package b
		System.out.println(messageCHandler.getMessageC());
	}

	public void writeDMessage() {
		// Access directly
		System.out.println(message.message_d);
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.writeAMessage();
		client.writeBMessage();
		client.writeCMessage();
		client.writeDMessage();
	}
}
