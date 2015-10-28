package com.group2.dungeonraider.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.test.dungeonmainmenu.R;
import com.group2.dungeonraider.controller.MainActivity;
import com.group2.dungeonraider.utilities.Constants;

/**
 * Created by Ajinkya on 10/27/2015.
 */
public class AudioImpl extends Activity implements Audio {
//sdf
    private static final String LOG = AudioImpl.class.getSimpleName();

    @Override
    public boolean mute() {
        Log.d(LOG, "mute() -> Mute game.");
        return false;

    }

    @Override
    public boolean unmute() {
        return false;
    }

    @Override
    public boolean play(Context context, int resId) {
        //int volume;
        //SharedPreferences preferences = context.getSharedPreferences("VOLUME", MODE_PRIVATE);
        //volume = preferences.getInt("volume", 0);
        if(Constants.VOLUME_MODE == 1) {
            MediaPlayer.create(context, resId).start();
        }
        return true;
    }
}
