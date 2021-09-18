import audio.AudioLineFactory;

import javax.sound.sampled.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Clientvoice {
    public static final int PORT = 80;

    public static void main(String[] args) {
        AudioLineFactory audioLineFactory = new AudioLineFactory();
        ClientVoiceApplication clientVoiceApplication = new ClientVoiceApplication(PORT, audioLineFactory);
        clientVoiceApplication.startConnection();
    }
}
