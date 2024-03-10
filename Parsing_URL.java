/*-----------------------------------------------------------------
The purpose of this program, URLDemo.java, is to demonstrate how to process and extract information from a Uniform Resource Locator (URL) in Java.
-----------------------------------------------------------------*/
import java.net.*;  
public class java_url{  
public static void main(String[] args)
{  
try{  
URL url=new URL("http://net-informations.com/");  
System.out.println("Protocol: "+url.getProtocol());  
System.out.println("Host Name: "+url.getHost());  
System.out.println("Port Number: "+url.getPort());  
System.out.println("File Name: "+url.getFile());  
  
}catch(Exception e)
{System.out.println(e);}
  }}  
