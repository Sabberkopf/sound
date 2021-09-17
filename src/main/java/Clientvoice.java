import javax.sound.sampled.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Clientvoice {
    private static final AudioFormat audioFormat = new AudioFormat(8000.0F,16,2,true,false);
    private static TargetDataLine audio_in;
    private static SourceDataLine audio_out;
    public static final int PORT = 80;

    public static void main(String[] args) {
        audio_in = intiAudio_In(audio_in);
        audio_out = intiAudio_Out(audio_out);
        initThread();

    }

    private static void initThread()  {
        try {
            Datagram d = new Datagram();
            d.server_ip = InetAddress.getByName("localhost");
            d.audio=audio_out;
            d.audio_out=audio_in;
            d.ds= new DatagramSocket(PORT);
            d.start();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }

    }

    private static TargetDataLine intiAudio_In(TargetDataLine audio_in)  {
        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class,audioFormat);
            audio_in = (TargetDataLine) AudioSystem.getLine(info);
            audio_in.open(audioFormat);
            audio_in.start();
            return audio_in;
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SourceDataLine intiAudio_Out(SourceDataLine audio_out)  {
        try {
            DataLine.Info info_out = new DataLine.Info(SourceDataLine.class,audioFormat);
            audio_out = (SourceDataLine) AudioSystem.getLine(info_out);
            audio_out.open(audioFormat);
            audio_out.start();
            return audio_out;
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }
}
