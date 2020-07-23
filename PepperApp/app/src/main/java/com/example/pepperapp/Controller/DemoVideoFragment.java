package com.example.pepperapp.Controller;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.pepperapp.R;

public class DemoVideoFragment extends Fragment {

    private View mView;
    private VideoView mVideoDemo;
    private int mIndexSelectedMvt;
    private MediaController mMediaController;
    private JsonParseMovementLIst mJsonParseMovementLIst;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.demo_video_fragment, container, false);
        this.mJsonParseMovementLIst = new JsonParseMovementLIst(getContext());
        this.mVideoDemo = (VideoView) mView.findViewById(R.id.demo_video_view);
        this.mMediaController = new MediaController(getContext());
        this.mMediaController.setAnchorView(mVideoDemo);
        this.mVideoDemo.setMediaController(mMediaController);
        String uri = "";
        if (mJsonParseMovementLIst.readJsonFile()) {
            this.mJsonParseMovementLIst.jsonToJavaObject();
             uri = this.mJsonParseMovementLIst.getMovementList().get(ListMovementFragment.getSelectedCategoryType()).get(mIndexSelectedMvt).getmURI();
        }
        this.mVideoDemo.setVideoURI(Uri.parse(uri));
        this.mVideoDemo.start();
        return mView;
    }
}
