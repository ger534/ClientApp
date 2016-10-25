package com.example.user.clientapp;

import android.content.Context;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by User on 11/10/2016.
 */

public class Connection implements Runnable {

    private final String Nickname;
    private final String IP;
    private final int Port;
    Socket socket;
    Thread t;

    private DataOutputStream OutData;
    private DataInputStream InData;
    private String Msg;
    public boolean conection; //= false;
    private Context MainActivity;
    //private SendJson sendJson;
    public Connection(String nickname, String pIP, int pPort, MainActivity mainActivity){
        IP = pIP;
        Port = pPort;
        Nickname = nickname;
        t = new Thread(this, "Thread 1");
        t.start();
        MainActivity = mainActivity;

    }


    public void setSocket(String localhost, int i) {


        try{
            socket = new Socket(localhost, i);
            System.out.println("pass");

            OutData = new DataOutputStream(socket.getOutputStream());
            InData = new DataInputStream(socket.getInputStream());


            Send(Nickname);


            //sendJson = new SendJson(socket);


            //Send("{\"Type\": \"Nereox\", \"Clan\": \"Nereox\", \"Player\": \"Victor\", \"Msg\": \"Hola, nuevo mensaje\"}");
            conection = true;
            System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA111111111111111");
            Msg = InData.readUTF();
            System.out.println(Msg);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            System.out.println("NOOOOOOOOOOOOOO");
        }
    }
    public boolean getConnected(){
        return socket.isConnected();
    }

    public void Send(String Msg){
        try{
            OutData.writeUTF(Msg + "\n");
            //OutData.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {


        setSocket(IP, Port);
    }
}

