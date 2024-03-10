/*-----------------------------------------------------------
Purpose:

1. This program creates a simple server application in Java that listens for incoming connections on a specific port and sends a predefined message to a connecting client.

Key Functionalities:

1. Server Socket Creation:

	1. ServerSocket s = new ServerSocket(2992);:
	2. Creates a ServerSocket object named s.
	3. Specifies port 2992 as the listening port for incoming connections.

2. Waiting for Connection:

	1. Socket s1 = s.accept();: Blocks the program's execution until a client connects 	to the specified port.
		1. When a client connects, a Socket object named s1 is created, representing 		the connection to that client.

3. Obtaining Output Stream:

	1. OutputStream s1out = s1.getOutputStream();: Gets an output stream from the 	socket, enabling data to be sent back to the client.
	2. DataOutputStream dos = new DataOutputStream(s1out);: Creates a DataOutputStream 	object named dos to facilitate writing data in a structured format (e.g., using 	writeUTF() for strings).

4. Sending Message:

	1. dos.writeUTF("Hi there");: Writes the string "Hi there" to the output stream, 	sending it to the connected client.

5. Closing Resources:

	1. dos.close();, s1out.close();, s1.close();: Closes the data output stream, output 	stream, and socket to release resources and terminate the connection.
		1. The server socket (s) remains open, allowing for additional connections 		in the future.
-----------------------------------------------------------*/
import java.net.*;
import java.io.*;
public class server1 {
public static void main(String args[]) throws IOException {
// Register service on port 2992
ServerSocket s =
new ServerSocket(2992);
Socket s1=s.accept(); 
//Wait and accept a connection
//Get a communication stream     	//associated 
//with the socket
OutputStream s1out = s1.getOutputStream();
DataOutputStream dos =
 new DataOutputStream (s1out);

// Send a string!
dos.writeUTF("Hi there");

// Close the connection, 
	//but not the server socket
dos.close();
s1out.close();
s1.close();
}
}
