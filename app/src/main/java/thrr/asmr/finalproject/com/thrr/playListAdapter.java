package thrr.asmr.finalproject.com.thrr;

import android.content.Context;
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
