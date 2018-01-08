package thrr.asmr.finalproject.com.thrr;

import android.media.MediaPlayer;
import android.widget.Button;

public class playListVO {
    private int imageID;
    private Button button;
    private MediaPlayer mediaPlayer;
    private String activity;
    private int index;

    public playListVO(){}

    public playListVO(int imageID, Button button, MediaPlayer mediaPlayer, String activity, int index) {
        this.imageID = imageID;
        this.button = button;
        this.mediaPlayer = mediaPlayer;
        this.activity = activity;
        this.index = index;
    }

    public int getImageID() {return imageID;}

    public String getActivity() {return activity;}

    public int getIndex(){return index;}

    public Button getButton() {return button;}

    public MediaPlayer getMediaPlayer(){return mediaPlayer;}

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
