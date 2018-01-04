package thrr.asmr.finalproject.com.thrr;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3 ;
    private ListView lv;
    private TabHost tabHost1;

    private int[] musicID = {
            R.raw.rain, R.raw.bird, R.raw.bug, R.raw.leaves, R.raw.cicada, R.raw.fire, R.raw.snow, R.raw.valley, R.raw.waterdrops, R.raw.wave,
            R.raw.blanket, R.raw.book, R.raw.chopping, R.raw.cream, R.raw.hairbrushing, R.raw.ice, R.raw.keyboard, R.raw.pencil, R.raw.piano, R.raw.scissors,
            R.raw.egg, R.raw.hairdryer, R.raw.plasticbag, R.raw.pountainpen, R.raw.sand, R.raw.shampoo, R.raw.slime, R.raw.soap, R.raw.train, R.raw.zengarden,
            R.raw.chicken, R.raw.conflakes, R.raw.cracker, R.raw.dango, R.raw.hotdog, R.raw.jelly, R.raw.macaron, R.raw.noodle, R.raw.pizza, R.raw.shrimppuffing,
            R.raw.carving, R.raw.ear, R.raw.earblowing, R.raw.hand, R.raw.heartbeat, R.raw.lids, R.raw.scratch, R.raw.shaving, R.raw.tapping, R.raw.walking};

    private ArrayList<playListVO> list = new ArrayList<>();
    private Button[] btn_array = new Button[50];
    playListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"tab1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.tab1) ;
        ts1.setIndicator("자연") ;
        tabHost1.addTab(ts1) ;

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.tab2) ;
        ts2.setIndicator("사물") ;
        tabHost1.addTab(ts2) ;

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.tab3) ;
        ts3.setIndicator("사물2") ;
        tabHost1.addTab(ts3) ;

        TabHost.TabSpec ts4 = tabHost1.newTabSpec("Tab Spec 4") ;
        ts4.setContent(R.id.tab4) ;
        ts4.setIndicator("이팅") ;
        tabHost1.addTab(ts4);

        TabHost.TabSpec ts5 = tabHost1.newTabSpec("Tab Spec 5") ;
        ts5.setContent(R.id.tab5) ;
        ts5.setIndicator("기타") ;
        tabHost1.addTab(ts5) ;

        //listview
        lv = (ListView) findViewById(R.id.musiclistview);

        for(int i = 0 ; i < btn_array.length; i++){
            final int btn_id = getResources().getIdentifier("Button"+(i+1), "id", "thrr.asmr.finalproject.com.thrr"); //버튼 아이디 한번에.
            btn_array[i] = findViewById(btn_id);

            final int finalI = i;
            btn_array[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(list.size()){
                        case 0: //아직 선택한 소리가 없다
                            mediaPlayer1 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer1.setLooping(true);
                            mediaPlayer1.start();
                            list.add(new playListVO(R.drawable.day, btn_array[finalI], mediaPlayer1));
                            break;
                        case 1: //소리 하나 선택중
                            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer2.setLooping(true);
                            mediaPlayer2.start();
                            list.add(new playListVO(R.drawable.night, btn_array[finalI], mediaPlayer2));
                            break;
                        case 2: //소리 두개 선택중
                            mediaPlayer3 = MediaPlayer.create(getApplicationContext(), musicID[finalI]);
                            mediaPlayer3.setLooping(true);
                            mediaPlayer3.start();
                            list.add(new playListVO(R.drawable.test, btn_array[finalI], mediaPlayer3));
                            break;
                        case 3: //리스트가 꽉 참
                            Toast.makeText(getApplicationContext(),"3곡까지만 조합할 수 있습니다.", Toast.LENGTH_LONG).show();
                            break;
                    }
                    adapter.notifyDataSetChanged();
                }
            });


            adapter = new playListAdapter(getApplicationContext(), R.layout.musiclist_layout, list);
            ((ListView) findViewById(R.id.musiclistview)).setAdapter(adapter);
        }
    }
}
