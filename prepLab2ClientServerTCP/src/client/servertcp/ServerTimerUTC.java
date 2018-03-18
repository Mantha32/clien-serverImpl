/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.servertcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author fidimala
 */
public class ServerTimerUTC {
    public static void listenClient(int listenPort) throws IOException{
        ServerSocket serverSocket;
        Socket clientSocket;
        BufferedReader reader;
        PrintWriter writer;
        try {
            //Bind TCP on port
            serverSocket = new ServerSocket(listenPort);
            //Bolck until a lcient makes a connection request
            //logServerSocketAddress(serverSocket);
            clientSocket = serverSocket.accept();
            
            //We want to exchange CHARACTERS with the clients : 
            //we should spycify the encoding
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream());
            
            int numberOfIterations = 5;
            for(int i=0; i < numberOfIterations; i++){
                writer.println(String.format("{'time' : %s}", new Date()));
            }
            
        } catch (IOException e) {
        }
    }
}
