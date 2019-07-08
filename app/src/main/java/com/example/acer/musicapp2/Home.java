package com.example.acer.musicapp2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private ArrayList<SongInfo> songs=new ArrayList<SongInfo>();
    SeekBar seekBar;
    RecyclerView recyclerView;
    SongAdapter songAdapter;

    MediaPlayer mediaPlayer;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=view.findViewById(R.id.recyclerview);
        seekBar=view.findViewById(R.id.seekBar);

        SongInfo s=new SongInfo("Shape of you","Ed Sheeran","http://fullgaana.com/siteuploads/files/sfd24/11827/Shape%20Of%20You%20n%20Gulabi%20Aankhen%20(Mashup)%20-%20Sandesh%20Motwani%20n%20Dhvani%20Bhanushali%20320kbps-(FullGaana.Com).mp3");
        songs.add(s);

        songAdapter = new SongAdapter(getActivity(),songs);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);

        songAdapter.setOnitemClickListener(new SongAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(final Button b, View v, SongInfo obj, int position) {
                try {
                    if(b.getText().toString().equals("Stop"))
                    {
                        b.setText("Play");
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer  = null;
                    }
                    else {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(obj.getSongUrl());
                        mediaPlayer.prepare();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer np) {
                                np.start();
                                seekBar.setProgress(0);
                                seekBar.setMax(mediaPlayer.getDuration());
                                b.setText("Stop");
                            }
                        });
                    }
                }catch(IOException e){}
                }
        });


        return view;

    }

    public class myThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1000);
                if(mediaPlayer !=null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
