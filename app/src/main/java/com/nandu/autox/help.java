package com.nandu.autox;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;

public class help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


          final Spinner spnhelp ;
          Button BtnView;
          Button Btnclose;
          Btnclose=findViewById(R.id.btnhelpclose);
          BtnView =findViewById(R.id.btnhelpView);
          spnhelp =findViewById(R.id.spnhelp);

        ArrayList<String> values = new ArrayList<String>();
        values.add("Login");
        values.add("Add Enquiry");
        values.add("Update Enquiry");
        values.add("Add Enquiry Followup");

        ArrayAdapter<String> ObjAdapter = new ArrayAdapter<String>
                (getBaseContext(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, values );
        spnhelp.setAdapter(ObjAdapter);

       final  VideoView myVideoView = (VideoView)findViewById(R.id.myvideoview);


        final String url = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/help/";

        //AutoXLogin.mp4

        final SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(help.this);
      //  final PlayerView playerView = (PlayerView)findViewById(R.id.myvideoview);

      //  playerView.setPlayer(player);

        BtnView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String item="";

            //   VideoView myVideoView = (VideoView)findViewById(R.id.myvideoview);

               if (spnhelp.getSelectedItem().toString().equals("Login")){
                   item ="http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/help/AutoXLogin.mp4";
               }else if(spnhelp.getSelectedItem().toString().equals("Add Enquiry")){
                   item ="http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/help/AddEnquiry.mp4";
               }else if(spnhelp.getSelectedItem().toString().equals("Update Enquiry")) {
                   item = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/help/UpdateEnquiry.mp4";
               }else if(spnhelp.getSelectedItem().toString().equals("Add Enquiry Followup")) {
                   item = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/help/AddEnquiryFP.mp4";
               }
               if (!item.equals("")) {
//                   myVideoView.setVideoURI(Uri.parse( item));
//                   myVideoView.setMediaController(new MediaController(help.this));
//                   myVideoView.requestFocus();
//                  // myVideoView.prepare();
//                   myVideoView.start();
                //   try {
//                       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item));
//                       startActivity(browserIntent);
//                   } catch (Exception e) {
//                       // TODO: handle exception
//                       Toast.makeText(help.this, "Error connecting", Toast.LENGTH_SHORT).show();
//                   }

//                   Uri mp4VideoUri = Uri.parse(item);
//
//                   // Produces DataSource instances through which media data is loaded.
//                   DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(help.this,
//                           Util.getUserAgent(help.this, "AutoX"));
//// This is the MediaSource representing the media to be played.
//                   MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
//                           .createMediaSource(mp4VideoUri);
//// Prepare the player with the source.
//                   player.prepare(videoSource);
//                   player.getPlayWhenReady();


                   VideoView vv = findViewById(R.id.myvideoview);
                   vv.setVideoPath(item);
                   vv.start();






               }else{

               }
           }
       });



        Btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
