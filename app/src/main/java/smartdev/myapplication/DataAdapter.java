package smartdev.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Andi on 09.07.2016.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private Content_Fragment.MotiusData[] motiusData;
    public DataAdapter(Content_Fragment.MotiusData[] motiusData){
        this.motiusData=motiusData;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        return new DataAdapter.DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        Content_Fragment.MotiusData motius = motiusData[position];
        holder.title_txt_vw.setText(motius.title);
        holder.body_txt_vw.setText(motius.body);
    }

    @Override
    public int getItemCount() {
        return motiusData.length;
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder{
        public TextView title_txt_vw;
        public TextView body_txt_vw;

        public DataViewHolder(View itemView) {
            super(itemView);
            title_txt_vw=(TextView) itemView.findViewById(R.id.row_title);
            body_txt_vw=(TextView) itemView.findViewById(R.id.row_body);
        }
    }
}