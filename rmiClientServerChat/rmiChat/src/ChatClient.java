import java.rmi.Naming;

public class ChatClient extends Thread{
	final private String seperator="-:-";
	int index_last_message=0;
	String clientName;
	ChatInterface client;

	public ChatClient(String clientName){
		try {
			this.clientName=clientName;
			client = (ChatInterface)Naming.lookup("//localhost/Chat");

		} catch (Exception e) {
		}
	}
	//Get new messages in the server every 0.3 second.
	@Override
	public void run() {
		try {
			while(true){
				Thread.sleep(300);
				String[] message_part=client.getNewMessages(index_last_message).split(seperator);

				if (message_part[0].equals("no_new_messages") || message_part[0].equals(clientName))
					continue;
				index_last_message++;
				System.out.println(message_part[0]+ ": "+ message_part[1]);
			}
		} catch (Exception e) {
		}
	}

	/*
	 * To Run the Application type
	 * java ChatClient clientName
	 */
	public static void main (String[] argv) {
		ChatClient hc=new ChatClient(argv[0]);
		hc.start();
		
		//Keep reading the user input and send it to the server.
		while(true){
			try {
				System.out.println(hc.clientName+ ": ");
				String msg=Console.readString();
				hc.index_last_message=hc.client.sendMsg(hc.clientName+hc.seperator+ msg);				
			} catch (Exception e) {
			}
		}
	}
}

