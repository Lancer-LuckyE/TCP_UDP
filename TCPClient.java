import java.net.*;
import java.util.Scanner;
import java.io.*;
public class TCPClient {
	public static void main (String args[]) {
		// arguments supply message and hostname
		// Check command line
		if (args.length < 1) {
			System.err.println("Usage : ");
			System.err.println("java TCPClient <Message> <server>");
			System.exit (1);
		}
		
		Socket s = null;
	    boolean isEnd = false;
		int col, row;
	    
	    
		try{
			int serverPort = 7896;
			System.out.println("starting a new client socket");
			s = new Socket(args[1], serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataInputStream in2 = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream(s.getOutputStream());
			System.out.println("Sending msg: " + args[0]);
			out.writeUTF(args[0]);      	// UTF is a string encoding see Sn. 4.4
			
			while (isEnd == false) {
				
				String data = in.readUTF();
				String result = in2.readUTF();
				System.out.println(data); 
				if (result != "-") {
					isEnd = true;
				}
				
				System.out.println("Please give your move. i.e x y.");
				Scanner scanner = new Scanner(System.in);
				col = scanner.nextInt();
				row = scanner.nextInt();
				scanner.close();
				out.writeUTF("(" + Integer.toString(col) + "," + Integer.toString(row) + ")"); 				
			}
			
			
			
			
			
      System.out.println("receiving response");
			String data = in.readUTF();	    // read a line of data from the stream
			System.out.println("Received: "+ data) ; 
			
			
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
  }
}
