package bjfu.cs.addsubview;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private ImageView ivsub;
    private ImageView ivadd;
    private TextView tvvalue;
    private Context context;

    public int getValue() {
        String s = tvvalue.getText().toString().trim();
        if(!TextUtils.isEmpty(s)){
            value = Integer.parseInt(s);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tvvalue.setText(value+"");
    }

    private int value = 1;

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        // 把布局文件实例化并且加载到该类中
        View.inflate(context,R.layout.add_sub_view,this);
        ivsub = findViewById(R.id.iv_sub);
        ivadd = findViewById(R.id.iv_add);
        tvvalue = findViewById(R.id.tv_value);

        int value = getValue();
        setValue(value);

        ivsub.setOnClickListener(this);
        ivadd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if(v.getId()== R.id.iv_sub) {
                if (value > 1)
                    value--;
            }
            else if(v.getId()==R.id.iv_add) {
                value++;
            }
        setValue(value);
        if(onNumberChangerListener!=null){
            onNumberChangerListener.onNumberChange(value);
        }
    }

    /**
     * 当数量发生变化时回调
     */
    public interface OnNumberChangerListener{
        public void onNumberChange(int value);
    }
    private OnNumberChangerListener onNumberChangerListener;

    public void setOnNumberChangerListener(OnNumberChangerListener onNumberChangerListener) {
        this.onNumberChangerListener = onNumberChangerListener;
    }
}
