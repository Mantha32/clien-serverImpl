/*
 *resource: http://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip
 */

package client.servertcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URLEncoder;

/**
 *
 * @author fidimala
 */
public class Client {
    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    private final String USER_AGENT = "Mozilla/5.0";
    private final String[] METHOD = {"GET","POST"}; 
    
    public static void sendHttpRequest(String request, int port, String method)throws IOException{
    Socket clientSocket;
    OutputStream os;
    InputStream is = null;
    URL url;
    
    //set up the url
    try {
        url = new URL(request);
    } catch (MalformedURLException ex) {
        LOG.log(Level.SEVERE, null, ex);   
        return;
    }
    
    //get the domain
    String hostname = url.getHost();
    LOG.log(Level.INFO,	hostname);
    //Establish connexion with the server
    try{
         clientSocket = new Socket(hostname,port);
         
        //Get stream to send and receive bytes
        os = clientSocket.getOutputStream();
        is = clientSocket.getInputStream();
        
        //make up the http 1.1 header follows HTTP protocol
        /*
        HEAD <URL> HTTP/1.1
        Host: <hostname>
        User-Agent: <information about the client>
        Accept: <format accepted by the client>
        Accept-Language: <language accepted by the client>
        Connection: <connection type, close or keep-alive>
        */
        
        StringBuilder httpRequest = new StringBuilder("HEAD ");
        httpRequest.append(url.getPath()).append(" HTTP/1.1");
        httpRequest.append("Host: ").append(hostname);
        httpRequest.append("User-Agent: Simple Http Client");
        httpRequest.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpRequest.append("Accept-Language: en-US");
        httpRequest.append("Connection: close");
        //httpRequest.append("\\r\\n\\r\\n");
        
        //Send the request well-formed
        os.write(httpRequest.toString().getBytes());
        
        //Get response using the BufferedReader. We seek performance
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        
        //use BufferedByte than string!!!
        String line;
        
        LOG.log(Level.INFO,	"Response sent by the server:	");
        //Read the bytes send by the server
        while ((line = reader.readLine()) != null) {
          LOG.log(Level.INFO, line);
        }
        
        os.close();
        is.close(); 
        
    }catch(IOException ex){
     LOG.log(Level.SEVERE, null, ex);    
    }
    }
}
