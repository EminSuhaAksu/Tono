package org.o7plannig.tonov3;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static org.o7plannig.tonov3.MainActivity.ARTIST_TO_FRAG;
import static org.o7plannig.tonov3.MainActivity.PATH_TO_FRAG;
import static org.o7plannig.tonov3.MainActivity.SHOW_MINI_PLAYER;
import static org.o7plannig.tonov3.MainActivity.SONG_NAME_TO_FRAG;

public class NowPlayingFragmentBottom extends Fragment
{
    public static ImageView nextBtnMini, albumArt;
    public static TextView artistMini, songNameMini;
    public static FloatingActionButton playPauseBtnMini;
    View view;
    public NowPlayingFragmentBottom()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_now_playing_bottom, container, false);
        artistMini = view.findViewById(R.id.song_artist_miniPlayer);
        songNameMini = view.findViewById(R.id.song_name_miniPlayer);
        albumArt = view.findViewById(R.id.bottom_album_art);
        nextBtnMini = view.findViewById(R.id.skip_next_bottom);
        playPauseBtnMini = view.findViewById(R.id.play_pause_miniPlayer);

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (SHOW_MINI_PLAYER)
        {
            if (PATH_TO_FRAG != null)
            {
                byte[] art = getAlbumArt(PATH_TO_FRAG);
                if (art != null)
                {
                    Glide.with(getContext()).load(art).into(albumArt);

                }
                else
                {
                    Glide.with(getContext()).load(R.drawable.emptysong).into(albumArt);
                }
                songNameMini.setText(SONG_NAME_TO_FRAG);
                artistMini.setText(ARTIST_TO_FRAG);
            }

        }
    }

    public static byte[] getAlbumArt(String uri)
    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;

    }

}