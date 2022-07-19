import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner scan = new Scanner(System.in);
		ServerSocket serverSocket = new ServerSocket(4001);
		System.out.println("Waiting for connection");
		Socket  firstSocket= serverSocket.accept();
		System.out.println("Connected");
		Socket  secondSocket= serverSocket.accept();
		System.out.println("Connected");
		Socket current = firstSocket;
		Socket next = secondSocket;
		String msg = null;
		do {
			 msg = (new DataInputStream(current.getInputStream())).readUTF();
			 (new DataOutputStream(next.getOutputStream())).writeUTF(msg);
			 Socket temp = current;
			 current = next;
			 next = temp;
		}while(!msg.equalsIgnoreCase("exit"));
		
		
		
		
		
	
		
	}

}
