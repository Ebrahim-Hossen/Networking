/*--------------------------------------------------------
Purpose:

1. This program creates a basic UDP (User Datagram Protocol) server application in Java that listens for incoming messages on a specified port, converts them to uppercase, and sends the modified messages back to the clients.

Key Functionalities:

1. Setting Up Server:

	1. Creates a DatagramSocket object on port 9876 to listen for UDP connections.
	2. Initializes byte arrays for receiving and sending data.

2. Waiting for Clients:

	1. Uses an infinite while loop to continuously handle incoming messages:
		1. Creates a DatagramPacket object for receiving data.
		2. Waits for a client's message using serverSocket.receive(receivePacket).

3. Processing Message:

	1. Extracts the received bytes from the packet and converts them to a string.
	2. Prints the received message to the console.
	3. Pauses for 3 seconds using Thread.sleep(3000) (simulating processing time).
	4. Converts the string to uppercase.
	5. Converts the uppercase string back to bytes for sending.

4. Sending Response:

	1. Retrieves the client's IP address and port from the received packet.
	2. Creates a DatagramPacket object for sending, containing the uppercase bytes, IP 	address, and port.
	3. Sends the response back to the client using serverSocket.send(sendPacket).
--------------------------------------------------------*/
import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		  System.out.println("Waiting...... " );                  serverSocket.receive(receivePacket);      
System.out.println("Data Received...... " );
                  String sentence = new String( receivePacket.getData());
                  System.out.println("RECEIVED: " + sentence);
		   Thread.sleep(3000)
;                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String capitalizedSentence = sentence.toUpperCase();
                  sendData = capitalizedSentence.getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
               }
      }
}
