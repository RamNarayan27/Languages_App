package com.example.languagesapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }

            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        ListView listView=(ListView)findViewById(R.id.familiesList);
        final ArrayList<Words>family=new ArrayList<Words>();
        family.add(new Words("Father","әpә",R.drawable.family_father,R.raw.family_father));
        family.add(new Words("Mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        family.add(new Words("Son","angsi",R.drawable.family_son,R.raw.family_son));
        family.add(new Words("Daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new Words("Older Brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new Words("Younger Brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new Words("Older Sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new Words("Younger Sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family.add(new Words("Grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new Words("Grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));
        WordAdapter familyAdapter=new WordAdapter(this,family,R.color.category_family);
        listView.setAdapter(familyAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words word = family.get(position);
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
