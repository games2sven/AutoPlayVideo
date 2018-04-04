package com.personal.sven.autoplayvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.personal.sven.adapter.DiscoveryListAdapter;
import com.personal.sven.mvp.Persenter;
import com.personal.sven.player.JCVideoPlayer;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private Persenter persenter = new Persenter();
    int[] videoIndexs = {0,0,1,1,1,0,0,1,0,0,1,1,1,0,0};
    private DiscoveryListAdapter mDiscoveryListAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

    }


    private void initViews(){
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

        mDiscoveryListAdapter = new DiscoveryListAdapter(this, this, videoIndexs);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setItemAnimator(new AppRecycleItemAnimator());

        mRecyclerView.setAdapter(mDiscoveryListAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动 判断是否是视频，如果是视频就自动播放
                        persenter.autoPlayVideo(mRecyclerView);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://正在滚动
                        break;
                    default:
                        break;
                }
            }
        });

        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                //recyclerview滑动的时候关闭已经滑出界面的视频播放，并释放资源
                JCVideoPlayer jcVideoPlayer = (JCVideoPlayer)view.findViewById(R.id.videoplayer);
                if(jcVideoPlayer != null){
                    if(jcVideoPlayer.currentState== JCVideoPlayer.CURRENT_STATE_PLAYING)
                    {
                        JCVideoPlayer.releaseAllVideos();
                    }else if (jcVideoPlayer.currentState== JCVideoPlayer.CURRENT_STATE_PREPAREING)
                    {
                        JCVideoPlayer.releaseAllVideos();
                    }else if(jcVideoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING_BUFFERING_START){
                        JCVideoPlayer.releaseAllVideos();
                    }
                }
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //按下home键或者锁屏键退出时关闭正在播放的视频，释放资源
        if(MyApplication.instance.VideoPlaying!=null)
        {
            if(MyApplication.instance.VideoPlaying.currentState== JCVideoPlayer.CURRENT_STATE_PLAYING)
            {
//                MyApplication.getInstance().VideoPlaying.startButton.performClick();
                JCVideoPlayer.releaseAllVideos();
            }else if (MyApplication.instance.VideoPlaying.currentState== JCVideoPlayer.CURRENT_STATE_PREPAREING)
            {
                JCVideoPlayer.releaseAllVideos();
            }else if(MyApplication.instance.VideoPlaying.currentState== JCVideoPlayer.CURRENT_STATE_PLAYING_BUFFERING_START){
                JCVideoPlayer.releaseAllVideos();
            }
        }
    }

}
