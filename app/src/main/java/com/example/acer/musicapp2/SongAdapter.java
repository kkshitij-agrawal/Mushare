package com.example.acer.musicapp2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    ArrayList<SongInfo> songs;
    Context context;

    OnitemClickListener onitemClickListener;


    SongAdapter(Context context,ArrayList<SongInfo> songs){
        this.context=context;
        this.songs=songs;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myview= LayoutInflater.from(context).inflate(R.layout.row_song,viewGroup,false);

        return new SongHolder(myview);
    }


    public interface OnitemClickListener{
        void onItemClick(Button b, View v,SongInfo obj, int position);

    }

    public void setOnitemClickListener(OnitemClickListener onitemClickListener){
        this.onitemClickListener = onitemClickListener;
    }



    @Override
    public void onBindViewHolder(@NonNull final SongHolder songHolder, final int i) {
        final SongInfo c=songs.get(i);
        songHolder.songName.setText(c.songName);
        songHolder.artist.setText(c.artistName);
        songHolder.btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onitemClickListener!= null)
                {
                    onitemClickListener.onItemClick(songHolder.btnplay,v,c,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {

        TextView songName,artist;
        Button btnplay;

        public SongHolder(@NonNull View itemView) {
            super(itemView);

            songName = (TextView) itemView.findViewById(R.id.tvSongname);
            artist = (TextView) itemView.findViewById(R.id.tvArtist);
            btnplay = (Button) itemView.findViewById(R.id.playbtn);
        }
    }
}
