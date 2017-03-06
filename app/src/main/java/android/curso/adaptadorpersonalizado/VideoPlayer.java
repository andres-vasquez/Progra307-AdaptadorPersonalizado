package android.curso.adaptadorpersonalizado;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayer extends AppCompatActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener{

    private VideoView mVV;
    private boolean pausa = false;
    private int stopPosition;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        context=this;

        mVV = (VideoView) findViewById(R.id.myvideoview);
        MediaController mediaController = new MediaController(this);
        mVV.setMediaController(mediaController);

        mVV.setOnCompletionListener(this);
        mVV.setOnPreparedListener(this);

        Intent intent = getIntent();
        if(intent.hasExtra("videoUrl")){
            Uri videoUri=Uri.parse(intent.getStringExtra("videoUrl"));
            mVV.setVideoURI(videoUri);
            mVV.start();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer)
    {
        Intent resultado = new Intent();
        setResult(RESULT_OK, resultado);
        this.finish();
    }

    public void stopPlaying() {
        mVV.stopPlayback();
        Intent resultado = new Intent();
        setResult(RESULT_CANCELED, resultado);
        this.finish();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //mp.setLooping(true);
        mp.start();
    }

}