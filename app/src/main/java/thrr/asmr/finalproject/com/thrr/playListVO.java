package thrr.asmr.finalproject.com.thrr;

import android.media.MediaPlayer;
import android.widget.Button;

public class playListVO {
    private int imageID;
    private Button button;
    private MediaPlayer mediaPlayer;

    public playListVO(){}

    public playListVO(int imageID, Button button, MediaPlayer mediaPlayer) {
        this.imageID = imageID;
        this.button = button;
        this.mediaPlayer = mediaPlayer;
    }

    public int getImageID() {return imageID;}

    public Button getButton() {return button;}

    public MediaPlayer getMediaPlayer(){return mediaPlayer;}

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
