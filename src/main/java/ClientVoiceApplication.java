import audio.AudioLineFactory;

import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientVoiceApplication {
    private final int port;
    private final AudioLineFactory audioLineFactory;

    public ClientVoiceApplication(int port, AudioLineFactory audioLineFactory) {
        this.port = port;
        this.audioLineFactory = audioLineFactory;
    }

    public void startConnection() {
        SourceDataLine audio_out = audioLineFactory.intiAudio_Out();
        TargetDataLine audio_in = audioLineFactory.intiAudio_In();
        try {
            DatagramAudioConnectionThread d = new DatagramAudioConnectionThread();
            d.setServer_ip(InetAddress.getByName("localhost"));
            d.setAudio(audio_out);
            d.setAudio_out(audio_in);
            d.setDs(new DatagramSocket(port));
            d.start();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }
}
