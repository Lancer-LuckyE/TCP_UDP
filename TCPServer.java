import java.net.*;
import java.util.Random;
import java.io.*;
public class TCPServer {
	public static void main (String args[]) {
		int serverPort = 7896; // the server port
	   	try {
	        InetAddress addr = InetAddress.getLocalHost();
	    
	        // Get IP Address
	        byte[] ipAddr = addr.getAddress();
	    
	        // Get hostname
	        String hostname = addr.getHostName();
	        System.out.println("Server Name: " + hostname + "\nServer Port: " + serverPort);
	    } catch (UnknownHostException e) {
	    }

		try{
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Server is Ready");
			while(true) {
				System.out.println("listening to client sockets");
				Socket clientSocket = listenSocket.accept();
				System.out.println("connection found, creating a new connection thread");
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {System.out.println("IOException Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out, out2;
	Socket clientSocket;
	XO_game game = new XO_game();
	byte col, row;
	char result;
	Random generator = new Random();
	boolean isEnd = false;
	
	public Connection (Socket aClientSocket) {
    System.out.println("in new connection thread");
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			out2 = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
    System.out.println("server thread started");
		try {			                 // an echo server			
			while (!isEnd) {
				//to start the game
				String data = in.readUTF();	                  // read a line of data from the stream
				if (data == "Game Start") {
					data = game.toString();
					out.writeUTF(data);
					System.out.println("Reply: "+data);
				}
				//client move
				col = (byte)data.charAt(1);
				row = (byte)data.charAt(3);
				game.o_move(row, col);
				result = game.game_result();
				if (result != '-'){
					isEnd = true;
				}
				//bot move
				col = (byte) (generator.nextInt(3) + 1);
				row = (byte) (generator.nextInt(3) + 1);
				if(!(game.x_move(row, col))){
					col = (byte) (generator.nextInt(3) + 1);
					row = (byte) (generator.nextInt(3) + 1);
				}
				result = game.game_result();
				if (result != '-'){
					isEnd = true;
				}
				//return data
				data = game.toString();
				out.writeUTF(data);
				out2.writeUTF(Character.toString(result));
				System.out.println("Reply: "+data);
			}			
			
			
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}
