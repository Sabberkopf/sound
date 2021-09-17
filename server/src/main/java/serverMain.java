import javax.sound.sampled.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class serverMain {
    public static void main(String[] args) throws LineUnavailableException, SocketException, UnknownHostException {
        AudioFormat audioFormat = new AudioFormat(8000.0F,16,2,true,false);
        SourceDataLine audio_out;
        TargetDataLine audio_in;
        DataLine.Info info_out = new DataLine.Info(SourceDataLine.class,audioFormat);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class,audioFormat);
        audio_out = (SourceDataLine) AudioSystem.getLine(info_out);
        audio_out.open(audioFormat);
        audio_out.start();
        audio_in = (TargetDataLine) AudioSystem.getLine(info);
        audio_in.open(audioFormat);
        audio_in.start();
        Datagram d = new Datagram();
        d.ds = new DatagramSocket(81);
        d.audio=audio_out;
        d.audio_out=audio_in;
        d.server_ip = InetAddress.getByName("localhost");
        d.start();
    }
}
