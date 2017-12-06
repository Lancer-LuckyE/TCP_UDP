import java.net.*;
import java.io.*;
public class UDPServer{
    public static void main(String args[]){ 

	   int port = 9909;

	   try {
	        InetAddress addr = InetAddress.getLocalHost();
	    
	        // Get IP Address
	        byte[] ipAddr = addr.getAddress();
	    
	        // Get hostname
	        String hostname = addr.getHostName();
	        System.out.println("Server Name: " + hostname + "\nServer Port: " + port);
      } catch (UnknownHostException e) {
	    }


    	DatagramSocket aSocket = null;
      try{
            aSocket = new DatagramSocket(9909);	// create socket at agreed port
        System.out.println("Server is Ready");
        byte[] buffer = new byte[100];
        while(true){
          DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(request); 
          System.out.println("Received: " + new String(request.getData()));	    
              DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
              aSocket.send(reply);
              for (int i=0; i<buffer.length; i++) buffer[i] = 0;
          }
      }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
      }catch (IOException e) {System.out.println("IO: " + e.getMessage());
      }finally {if(aSocket != null) aSocket.close();}
    }
}
