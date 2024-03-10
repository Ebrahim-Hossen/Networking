/*--------------------------------------------------------
The purpose of this program, url_connection.java, is to fetch and display the content from a website URL.
--------------------------------------------------------*/
import java.net.*;
import java.io.*;
public class url_connection {
  public static void main(String[] args) throws Exception {	  
    try{
      URL url = new URL("http://net-informations.com/");
      BufferedReader reader = new BufferedReader(
      new InputStreamReader(url.openStream()));
      String line;
      while ((line = reader.readLine()) != null)
          System.out.println(line);
      reader.close();
    }catch(Exception ex){
      System.out.println(ex);
    }}}
