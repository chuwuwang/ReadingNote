package com.nsz.android.home.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.nsz.android.R;

import java.util.List;

public class SortAdapter extends BaseAdapter implements SectionIndexer {

    private Context context;
    private List<SortModel> list;

    public SortAdapter(Context context, List<SortModel> list) {
        this.context = context;
        this.list = list;
    }

    public void updateListView(List<SortModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.list.size();
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
    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.main_item_contact, null);
            viewHolder.tvTitle = view.findViewById(R.id.tv_title);
            viewHolder.tvLetter = view.findViewById(R.id.tv_letter);
            viewHolder.letterParent = view.findViewById(R.id.letter_parent);
            viewHolder.titleLine = view.findViewById(R.id.title_line);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final SortModel item = list.get(position);

        int section = getSectionForPosition(position);

        // 如果当前位置等于该分类首字母的Char的位置, 则认为是第一次出现
        boolean first = position == getPositionForSection(section);
        if (first) {
            viewHolder.tvLetter.setText(item.sortLetters);
            viewHolder.letterParent.setVisibility(View.VISIBLE);
        } else {
            viewHolder.letterParent.setVisibility(View.GONE);
        }

        viewHolder.tvTitle.setText(item.name);

        if (list.size() - 1 > position) {
            int lastPosition = position + 1;
            int lastSection = getSectionForPosition(lastPosition);
            boolean lastFirst = lastPosition == getPositionForSection(lastSection);
            if (lastFirst) {
                viewHolder.titleLine.setVisibility(View.GONE);
            } else {
                viewHolder.titleLine.setVisibility(View.VISIBLE);
            }
        }

        return view;
    }

    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        View letterParent;
        View titleLine;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ASCII值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).sortLetters.charAt(0);
    }

    /**
     * 根据分类的首字母的Char ASCII值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).sortLetters;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 提取英文的首字母, 非英文字母用#代替。
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        boolean bool = sortStr.matches("[A-Z]");
        if (bool) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }


}