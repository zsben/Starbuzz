package bjfu.cs.zhouenjie.type.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.R;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterViewHolder> {

    private Context mContext;
    private List<String> mChapters = new ArrayList();

    public int positionChecked = 0;

    public void setPositionChecked(int pos){
        if(positionChecked!=pos) {
            positionChecked = pos;
            notifyDataSetChanged();
        }
    }

    interface OnItemClickListener{
        public void OnItemClick(int postion);
    }
    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public ChapterAdapter(Context context){
        mContext = context;
        mChapters.add("1");
        mChapters.add("2");
        mChapters.add("3");
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        holder.bind(String.valueOf(position+1));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnItemClick(position);
                setPositionChecked(position);
            }
        });
        if (position != positionChecked){
            holder.bg.setVisibility(View.GONE);
        } else {
            holder.bg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mChapters.size();
    }
}

class ChapterViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    View bg;


    public ChapterViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_chapter);
        bg = itemView.findViewById(R.id.bg);
    }
    public void bind(String name){
        textView.setText("分类"+name);
    }
}
