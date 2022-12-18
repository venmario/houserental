/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Mario
 */
public class player_thread_client extends Thread{
    public DatagramSocket din;
    public SourceDataLine audio_out;
    byte[] buffer = new byte[512];
    FormCall parent;
    
    public player_thread_client(FormCall _parent){
        parent = _parent;
    }
    @Override
    public void run(){
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        int i = 0;
        while(parent.calling)
        {
            try {
                din.receive(incoming);
                buffer = incoming.getData();
                System.out.println("send #"+i++);
                audio_out.write(buffer, 0, buffer.length);
            } catch (IOException ex) {
                Logger.getLogger(player_thread_client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        audio_out.close();
        audio_out.drain();
        System.out.println("stop");
    }
}
