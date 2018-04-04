package com.personal.sven.mvp;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

import com.personal.sven.autoplayvideo.MyApplication;
import com.personal.sven.autoplayvideo.R;
import com.personal.sven.player.JCVideoPlayer;
import com.personal.sven.player.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class Persenter {


    public void autoPlayVideo(RecyclerView view){
        int visibleCount = view.getChildCount();
        for(int i = 0; i<visibleCount;i++){
            if(view.getChildAt(i)!=null&&view.getChildAt(i).findViewById(R.id.videoplayer) != null){
                Rect rect = new Rect();
                view.getChildAt(i).findViewById(R.id.videoplayer).getLocalVisibleRect(rect);
                int itemheight = view.getChildAt(i).findViewById(R.id.videoplayer).getHeight();

                if(rect.top == 0 && rect.bottom == itemheight){
                    JCVideoPlayerStandard videoPlayerStandard1 = (JCVideoPlayerStandard) view.getChildAt(i).findViewById(R.id.videoplayer);
                    if (videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL || videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
                        videoPlayerStandard1.startButton.performClick();
                        MyApplication.instance.VideoPlaying = videoPlayerStandard1;
                    }
                    return;
                }
            }
        }
    }



}
