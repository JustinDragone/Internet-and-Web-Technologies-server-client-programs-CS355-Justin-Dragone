import java.net.*; 
import java.util.HashSet;
 
public class udpserver {
  private static HashSet<Integer> portSet = new HashSet<Integer>();
	
	public static void main(String args[]) throws Exception {
		int serverport = 4445;
		if (args.length < 1) {
			System.out.println("UDPServer " + "Port# = " + serverport);
			}
		
		else {
			serverport = Integer.valueOf(args[0]).intValue();
			System.out.println("UDPServer " + "Port# = " + serverport);
			}
		DatagramSocket udpServerSocket = new DatagramSocket(serverport);
		System.out.println("Server connected\n");
		
		while(true) {
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			udpServerSocket.receive(receivePacket);
			String clientMessage = (new String(receivePacket.getData())).trim();
			System.out.println("Client Connected - Socket Address: " + receivePacket.getSocketAddress());
			System.out.println("Client message: \"" + clientMessage + "\"");
			InetAddress clientIP = receivePacket.getAddress();
			System.out.println("Client IP Address & Hostname: " + clientIP + ", " + clientIP.getHostName() + "\n");
			int clientport = receivePacket.getPort();
			System.out.println("Adding "+clientport);
			portSet.add(clientport);
			String returnMessage = clientMessage.toUpperCase();
			System.out.println(returnMessage);
			byte[] sendData = new byte[1024];
			sendData = returnMessage.getBytes();
			
			for(Integer port : portSet) {
				System.out.println(port != clientport);
				if(port != clientport)
				{
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, port);
					System.out.println("Sending");
					udpServerSocket.send(sendPacket);
					}
				}
			}
		}
	}
