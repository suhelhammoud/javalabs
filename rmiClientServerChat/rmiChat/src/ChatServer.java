import java.rmi.Naming;
public class ChatServer {
	public static void main (String[] Argv) {
		try {
			Naming.rebind ("Chat", new Chat ());
			System.out.println ("Hello Server is ready.");

		}
		catch (Exception e) {
			System.out.println ("Hello server failed: " +e);

		}

	}

}
