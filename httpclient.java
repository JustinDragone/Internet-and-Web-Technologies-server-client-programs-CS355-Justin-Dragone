import java.io.*;
import java.net.*;

public class httpclient {
  public static void main(String args[]) {
		try{ 
			if ((args.length != 1) && (args.length != 2))
					throw new IllegalArgumentException("Wrong number of arguments");
			OutputStream gethttp;
			if(args.length == 2) gethttp = new FileOutputStream(args[1]);
			else gethttp = System.out;
			URL url = new URL (args[0]);
			String protocol = url.getProtocol();
			if (!protocol.equals("http"))
					throw new IllegalArgumentException("Url must use http protocol");
			String host = url.getHost();
			int port = url.getPort();
			if (port == -1) port = 80; 
			String filename = url.getFile();
			Socket socket = new Socket(host, port);
			InputStream from_server = socket.getInputStream();
			PrintWriter httpclient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			httpclient.println("Get " + filename + "\n" );
			httpclient.flush();
			byte[] buffer = new byte[4096];
			int bytes_read;
			while((bytes_read = from_server.read(buffer)) != -1)
				gethttp.write(buffer, 0, bytes_read);
			socket.close();
			gethttp.close();
		}
		catch (Exception e) {
			System.err.println(e);
		}
	}
}
			
