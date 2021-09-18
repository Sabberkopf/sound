package audio;

import javax.sound.sampled.*;

public class AudioLineFactory {
    private final AudioFormat audioFormat = new AudioFormat(8000.0F,16,2,true,false);

    public TargetDataLine intiAudio_In()  {
        try {
            TargetDataLine audio_in;
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

    public SourceDataLine intiAudio_Out()  {
        try {
            SourceDataLine audio_out;
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
