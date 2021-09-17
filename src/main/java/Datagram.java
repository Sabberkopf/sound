import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Datagram extends Thread{
    public DatagramSocket ds;
    public SourceDataLine audio;
    public TargetDataLine audio_out;
    public InetAddress server_ip;
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
}
