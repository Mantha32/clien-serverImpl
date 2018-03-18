/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.servertcp;

import java.io.IOException;

/**
 *
 * @author fidimala
 */
public class ClientServerTCP {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String request = "http://macvendors.co/api/ec:1f:72:dc:50:37";
        Client.sendHttpRequest(request, 80, "GET ");
        //HttpClient.sendRequest(request, 0);
    }
    
}
