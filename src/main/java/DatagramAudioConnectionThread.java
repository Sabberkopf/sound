import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramAudioConnectionThread extends Thread {
    private DatagramSocket ds;
    private SourceDataLine audio;
    private TargetDataLine audio_out;
    private InetAddress server_ip;
    byte[] buf= new byte[512];

    @Override
    public void run() {
        DatagramPacket in =  new DatagramPacket(buf,buf.length);
        while (true){
            try {
                DatagramPacket data = new DatagramPacket(buf,buf.length,server_ip,81);
                audio_out.read(buf,0,buf.length);
                System.out.println("send");
                ds.send(data);
                ds.receive(in);
                buf = in.getData();
                audio.write(buf,0,buf.length);
                System.out.println("get");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDs(DatagramSocket ds) {
        this.ds = ds;
    }

    public void setAudio(SourceDataLine audio) {
        this.audio = audio;
    }

    public void setAudio_out(TargetDataLine audio_out) {
        this.audio_out = audio_out;
    }

    public void setServer_ip(InetAddress server_ip) {
        this.server_ip = server_ip;
    }
}
