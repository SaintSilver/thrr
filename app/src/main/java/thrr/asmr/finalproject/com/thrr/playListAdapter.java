package thrr.asmr.finalproject.com.thrr;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class playListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<playListVO> list;
    private LayoutInflater inflater;
    private playListAdapter adapter = this;
    private int[] whiteIcon = {
            R.drawable.rain125, R.drawable.bird5, R.drawable.noun_308781_cc5, R.drawable.noun_1095263_cc5, R.drawable.cicada5, R.drawable.noun_7380_cc5, R.drawable.winter5, R.drawable.noun_3411_cc5, R.drawable.faucet5, R.drawable.wave5,
            R.drawable.blanket5, R.drawable.book5, R.drawable.knife5, R.drawable.cream5, R.drawable.hiarbrush5, R.drawable.ice5, R.drawable.keyboard5, R.drawable.pen5, R.drawable.piano5, R.drawable.scissors5,
            R.drawable.egg5, R.drawable.hairdryer5, R.drawable.plasticbag5, R.drawable.pountainpen5, R.drawable.sand5, R.drawable.shampoo5, R.drawable.sli5, R.drawable.soap5, R.drawable.train5, R.drawable.zengarden5,
            R.drawable.chicken5, R.drawable.conflakes5, R.drawable.cracker5, R.drawable.dango5, R.drawable.hotdog5, R.drawable.jelly5, R.drawable.macaron5, R.drawable.noodle5, R.drawable.pizza5, R.drawable.shrimppuffing5,
            R.drawable.carving5, R.drawable.ear5, R.drawable.earblow5, R.drawable.hand5, R.drawable.heart5, R.drawable.lid5, R.drawable.scratch5, R.drawable.shaving5, R.drawable.tapping5, R.drawable.shoes5
    };

    int[] grayIcon = {R.drawable.rain7,R.drawable.bird7,R.drawable.bug7,R.drawable.leaves7,R.drawable.cicada7,R.drawable.fire7,R.drawable.snow7,R.drawable.valley7,R.drawable.waterdrop7,R.drawable.wave7,
            R.drawable.blanket27,R.drawable.book27,R.drawable.tapping7,R.drawable.cream27,R.drawable.hiarbrush27,R.drawable.ice27,R.drawable.keyboard227,R.drawable.pencil7,R.drawable.piano27,R.drawable.scissors7,
            R.drawable.egg7,R.drawable.hairdryer27,R.drawable.plasticbag7,R.drawable.pountainpen7,R.drawable.sand7,R.drawable.shampoo7,R.drawable.slime7,R.drawable.soap7,R.drawable.train7,R.drawable.zengarden27,
            R.drawable.chicken27,R.drawable.conflakes27,R.drawable.cracker27,R.drawable.dango27,R.drawable.hotdog27,R.drawable.jelly27,R.drawable.macaron27,R.drawable.noodle7,R.drawable.pizza7,R.drawable.shrimppuffing7,
            R.drawable.carving27,R.drawable.ear7,R.drawable.earblow7,R.drawable.hand27,R.drawable.heart7,R.drawable.lids7,R.drawable.scratch7,R.drawable.shaving7,R.drawable.tapping7,R.drawable.walking7,
    };


    public playListAdapter(Context applicationContext, int layout, ArrayList<playListVO> list) {
        this.context = applicationContext;
        this.layout = layout;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {return list.size();}

    @Override
    public Object getItem(int position) {return list.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.playingImage);
            viewHolder.seekBar = (SeekBar) convertView.findViewById(R.id.playingSeekBar);
            viewHolder.imageButton = (ImageButton) convertView.findViewById(R.id.playingStopBtn);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(list.get(position).getImageID());
        viewHolder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                list.get(position).getMediaPlayer().setVolume((float)progress/100, (float)progress/100);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).getMediaPlayer().stop();
                list.get(position).getMediaPlayer().release();
                list.get(position).setMediaPlayer(null);
                list.get(position).getButton().setEnabled(true);
                if(list.get(position).getActivity().equals("sleep")){
                    list.get(position).getButton().setBackgroundResource(whiteIcon[list.get(position).getIndex()]);
                }else if(list.get(position).getActivity().equals("focus")){
                    list.get(position).getButton().setBackgroundResource(grayIcon[list.get(position).getIndex()]);
                }
                list.remove(position);


                adapter.notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public class ViewHolder{
        ImageView imageView;
        ImageButton imageButton;
        SeekBar seekBar;
    }
}
