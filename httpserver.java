import java.io.*;
import java.net.*;

public class httpserver {
  public static void main(String args[]) {
		 try {
		      int port = Integer.parseInt(args[0]);       
		      ServerSocket ss = new ServerSocket(port);   
		      for(;;) {                                   
		        Socket client = ss.accept();              
		        ClientThread t = new ClientThread(client);
		        t.start();                                
		      }                                           
		    } 
		    catch (Exception e) {
		      System.err.println(e.getMessage());
		      System.err.println("no url;");
		    }
		  }

		  static class ClientThread extends Thread {
		    Socket client;
		    ClientThread(Socket client) { this.client = client; }
		    public void run() {
		      try {
		        BufferedReader in = 
		          new BufferedReader(new InputStreamReader(client.getInputStream()));
		        PrintWriter out =
		          new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
		        out.print("HTTP file");
		        String line;
		        while((line = in.readLine()) != null) {
		          if (line.length() == 0) break;
		          out.println(line);
		        }
		        
		        out.close();
		        in.close();
		        client.close();
		      }
		      catch (IOException e) { /* Ignore exceptions */ }
		    }
		  }
		}
