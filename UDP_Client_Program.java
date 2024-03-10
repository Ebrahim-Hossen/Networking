/*---------------------------------------------------------------
Purpose:

1. This program creates a basic UDP (User Datagram Protocol) client application in Java that communicates with a UDP server.

2. It sends a user-provided message to a specified server and receives a response back.

Key Functionalities:

1. Setting Up Client:

	1. Creates a BufferedReader to read user input from the console.
	2. Creates a DatagramSocket to establish a UDP connection.
	3. Resolves the IP address of the server using InetAddress.getByName("localhost").

2. Sending Data:

	1. Reads a sentence from the user.
	2. Converts the sentence to bytes.
	3. Creates a DatagramPacket for sending, containing the bytes, IP address, and port 	9876.
	4. Sends the packet using clientSocket.send(sendPacket).

3. Receiving Response:

	1. Creates an empty DatagramPacket for receiving data.
	2. Waits for the server's response using clientSocket.receive(receivePacket).
	3. Extracts the received bytes from the packet.
	4. Converts the bytes back to a string.

4. Displaying Response:

	1. Prints the received string from the server to the console.

5. Closing Connection:

	1. Closes the DatagramSocket to release resources.
---------------------------------------------------------------*/
import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
      BufferedReader inFromUser =
      new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");
      byte[] sendData = new byte[1024];
byte[] receiveData = new byte[1024];
      String sentence = inFromUser.readLine();

      sendData = sentence.getBytes();

DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      clientSocket.send(sendPacket);
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      System.out.println("Waiting...... " );
      clientSocket.receive(receivePacket);
      System.out.println("Data Received...... " );
      String modifiedSentence = new String(receivePacket.getData());
      System.out.println("FROM SERVER:" + modifiedSentence);
      clientSocket.close();
   }
}
