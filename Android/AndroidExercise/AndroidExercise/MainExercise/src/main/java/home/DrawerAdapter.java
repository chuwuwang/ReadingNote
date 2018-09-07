package home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nsz.android.R;

import java.util.List;

public class DrawerAdapter extends BaseAdapter {

    private Context context;
    private List<Drawer> list;

    DrawerAdapter(Context context, List<Drawer> list) {
        this.context = context;
        this.list = list;
    }

    public void selectItem(int position) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Drawer drawer = list.get(i);
            if (i == position) {
                drawer.state = 1;
            } else {
                drawer.state = 0;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.main_item_drawer, null);
            holder.state = convertView.findViewById(R.id.iv_state);
            holder.name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Drawer drawer = list.get(position);
        holder.name.setText(drawer.name);
        if (drawer.state == 1) {
            holder.state.setVisibility(View.VISIBLE);
        } else {
            holder.state.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private static class ViewHolder {
        View state;
        TextView name;
    }


}

