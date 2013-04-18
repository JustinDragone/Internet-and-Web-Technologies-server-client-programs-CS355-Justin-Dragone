import java.io.*;
import java.net.*;
 
class udpserver {
  public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(4445);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];  
            while(true) {  	
            	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String message = new String( receivePacket.getData()); 
                System.out.println(message);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String capitalizedSentence = message.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
                serverSocket.close();
               }
      }
}
