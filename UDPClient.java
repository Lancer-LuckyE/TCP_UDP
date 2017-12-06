import java.net.*;
import java.io.*;
public class UDPClient{    
	public static void main(String args[]){
		// args give message contents and destination hostname		
		
		// Check command line
		if (args.length < 2) {
			System.err.println("Usage : ");
			System.err.println("java UDPClient <Message> <server>");
			System.exit (1);
		}    

		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket();
   			//byte[] m = args[0].getBytes();
      System.out.println("getting server IP address");
			InetAddress aHost = InetAddress.getByName(args[1]);
			int serverPort = 9909;
      System.out.println("create request");
			DatagramPacket request = new DatagramPacket(args[0].getBytes(),  args[0].length(), aHost, serverPort);			
      System.out.println("send request");
			aSocket.send(request);			                        			
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
      System.out.println("receive response");
			aSocket.receive(reply);			
			System.out.println("Received: " + new String(reply.getData()));			
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
    }catch (IOException e) {System.out.println("IO: " + e.getMessage());
    }finally {if(aSocket != null) aSocket.close();}
	}
}