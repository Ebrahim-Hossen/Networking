// SimpleClient.java: A simple client program

/*------------------------------------------------
Connect to Server:
1. Socket s1 = new Socket("127.0.0.1", 2992);
2.Creates a new Socket object named s1.
3.The Socket constructor takes two arguments:
	1.Server IP address: "127.0.0.1" is the loopback address, referring to the local 	machine itself.
	2.Server port number: 2992 specifies the port on which the server is listening for 	connections.
4.This line attempts to establish a connection with the server running on the local 	machine (localhost) at port 2992.
--------------------------------------------------*/

/*--------------------------------------------------------
Receive Data from Server:

1. InputStream s1In = s1.getInputStream();
	1. Retrieves an InputStream object from the Socket (s1). This stream allows reading 	data sent by the server.
2. DataInputStream dis = new DataInputStream(s1In);
	1. Creates a DataInputStream object by wrapping the InputStream (s1In). This allows 		reading data in a more structured way, like reading UTF-8 encoded strings.
3. String st = new String(dis.readUTF());
	1. Reads a UTF-8 encoded string sent by the server using the dis.readUTF() method.
	2. Stores the received string in a variable named st.
4. System.out.println(st);
5. Prints the received string (st) to the console.
---------------------------------------------------------*/
import java.net.*;
import java.io.*;
public class client1 {
public static void main(String args[]) throws IOException {
//Open your connection to a server, at 	//port 2992
Socket s1 = new Socket("127.0.0.1",2992);
//Get an input file handle from the 	//socket and read the input
InputStream s1In = s1.getInputStream();
DataInputStream dis = new DataInputStream(s1In);
String st = new String (dis.readUTF());
System.out.println(st);
//When done, just close the connection 	//and exitdis.close();
s1In.close();
s1.close();
}}

/*----------------------------------------------------------------
Send Data to Server (Commented Out):

1. OutputStream s1out = s1.getOutputStream();
	1.Retrieves an OutputStream object from the Socket (s1). This stream allows writing 	data to the server.
2. DataOutputStream dos = new DataOutputStream(s1out);
	1. Creates a DataOutputStream object by wrapping the OutputStream (s1out). This 	allows writing data in a more structured way, like writing UTF-8 encoded strings.
3. // Send a string! (commented out)
	1. This line is commented out, but it demonstrates how to send a string using 	dos.writeUTF("Hi there").
4. // Close the connection, but not the server socket (commented out)
	1. These commented lines show how to close the DataOutputStream (dos) and 	OutputStream (s1out) after sending data. However, they wouldn't close the entire 	connection.
------------------------------------------------------------------*/
OutputStream s1out = s1.getOutputStream();
DataOutputStream dos = new DataOutputStream (s1out);
// Send a string!
dos.writeUTF("Hi there");
// Close the connection, but not the
	//server socket
dos.close();
s1out.close();
s1.close();
}
}
