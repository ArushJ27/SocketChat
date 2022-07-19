import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	static boolean finished = false;
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner scan  = new Scanner(System.in);
		Socket commonSocket = new Socket(args[0], 4001);
		DataInputStream input  = new DataInputStream(commonSocket.getInputStream());
		DataOutputStream  out = new DataOutputStream(commonSocket.getOutputStream());
		String msg = null;
		
		Thread t = new Thread( new Runnable() {

			@Override
			public void run() {
				try {
					while(true) {
						String msgFromOtherClient = input.readUTF();
						System.out.println(msgFromOtherClient);
						if(msgFromOtherClient.equalsIgnoreCase("exit")) {
							finished = true;
							break;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t.start();
		
		do {
			System.out.print("Enter a message");
			msg = scan.nextLine();
			out.writeUTF(msg);
			
		}while(!msg.equalsIgnoreCase("Exit") || finished);
		
	}
	

}
