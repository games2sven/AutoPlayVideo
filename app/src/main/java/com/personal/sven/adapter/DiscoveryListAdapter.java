package com.personal.sven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.personal.sven.autoplayvideo.R;
import com.personal.sven.player.JCVideoPlayer;
import com.personal.sven.player.JCVideoPlayerStandard;
import com.personal.sven.utils.UiUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * yangchao
 * 2016/8/30
 */
public class DiscoveryListAdapter extends RecyclerView.Adapter<DiscoveryListAdapter.MyHodler> {

    private static final int TYPE_PIC   = 1;//图片
    private static final int TYPE_VIDEO = 2;//视频

    private int [] data;
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public DiscoveryListAdapter(Context context, Object tag, int [] data) {
        this.data = data;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        if(data[position] == 0){
            return TYPE_PIC;
        }else{
            return TYPE_VIDEO;
        }
    }

    @Override
    public MyHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        MyHodler holder;
        if(viewType == TYPE_PIC){
            view = mLayoutInflater.inflate(R.layout.item_image, parent, false);
            holder = new ImageHolder(view);
        }else{
            view = mLayoutInflater.inflate(R.layout.item_video, parent, false);
            holder = new VideoHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHodler holder, int position) {

        holder.update();
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class MyHodler extends RecyclerView.ViewHolder{

        public MyHodler(View itemView) {
            super(itemView);

        }

        void update(){

        };
    }


    class VideoHolder extends MyHodler{
        JCVideoPlayerStandard mVideoPlayer;

        public VideoHolder(View itemView) {
            super(itemView);

            mVideoPlayer =  (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
            ViewGroup.LayoutParams para = mVideoPlayer.getLayoutParams();
            para.width = UiUtil.getScreenWidth(false);
            para.height = para.width* 9 / 16 ;
            mVideoPlayer.setLayoutParams(para);
        }

        @Override
        void update() {
            super.update();

            //https://wsqncdn.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4?ssig=9b168f928df2f7d47188bd979d826325&time_stamp=1530352758611
            mVideoPlayer.setUp(
                    "http://wsqncdn.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4?ssig=9b168f928df2f7d47188bd979d826325&time_stamp=1530352758611",
                    JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            Glide.with(itemView.getContext()).load(
                    "http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg").into(mVideoPlayer.thumbImageView);
        }
    }

    class ImageHolder extends MyHodler{

        private ImageView image_view;

        public ImageHolder(View itemView) {
            super(itemView);

            image_view =  (ImageView) itemView.findViewById(R.id.image_view);
        }

        @Override
        void update() {
            super.update();

            Glide.with(mContext).load("http://img04.tooopen.com/images/20131019/sy_43185978222.jpg").into(image_view);
        }
    }



}
