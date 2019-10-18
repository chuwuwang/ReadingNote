package com.nsz.android.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.core.utils.LogUtil;
import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.pinyinhelper.PinyinMapDict;
import com.nsz.android.R;
import com.nsz.android.home.contact.PinyinComparator;
import com.nsz.android.home.contact.SideBar;
import com.nsz.android.home.contact.SortAdapter;
import com.nsz.android.home.contact.SortModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonFragment extends BaseFragment {

    private SideBar sideBar;
    private TextView dialog;

    private ListView listView;
    private SortAdapter adapter;

    private List<SortModel> sourceData;

    private PinyinComparator pinyinComparator;

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_person;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDir();
        initView();
    }

    private void initView() {
        View view = getView();
        if (view == null) return;

        pinyinComparator = new PinyinComparator();

        sideBar = view.findViewById(R.id.side_bar);
        dialog = view.findViewById(R.id.tv_dialog);
        sideBar.setTextView(dialog);

        sideBar.setOnTouchingLetterChangedListener(
                s -> {
                    LogUtil.e(Constant.TAG, "select:" + s);
                    char c = s.charAt(0);
                    int position = adapter.getPositionForSection(c);
                    if (position != -1) {
                        listView.setSelection(position);
                    }
                }
        );

        listView = view.findViewById(R.id.list_view);
        listView.setOnItemClickListener(
                (parent, v, position, id) -> {
                    SortModel item = sourceData.get(position);
                    Toast.makeText(mContext, item.name + " " + item.sortLetters, Toast.LENGTH_SHORT).show();
                }
        );

        sourceData = filledData();

        Collections.sort(sourceData, pinyinComparator);

        adapter = new SortAdapter(mContext, sourceData);
        listView.setAdapter(adapter);

        EditText editText = view.findViewById(R.id.edit_contact);
        editText.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        int length = s.length();
                        if (length <= 0) {
                            adapter.updateListView(sourceData);
                        } else {
                            String filterStr = s.toString();
                            filterData(filterStr);
                        }
                    }
                }
        );
    }

    private void filterData(String filterStr) {

        filterStr = filterStr.toUpperCase();

        List<SortModel> list = new ArrayList<>();

        for (SortModel item : sourceData) {
            String pinyin = Pinyin.toPinyin(item.name, "");
            boolean bool = item.name.contains(filterStr) || pinyin.contains(filterStr);
            if (bool) {
                list.add(item);
            }
        }

        SortModel model = new SortModel();
        model.name = filterStr;
        model.sortLetters = "↑";
        list.add(model);

        Collections.sort(list, pinyinComparator);
        adapter.updateListView(list);
    }

    private void initDir() {
        PinyinMapDict dict = new PinyinMapDict() {

            @Override
            public Map<String, String[]> mapping() {
                HashMap<String, String[]> map = new HashMap<>();
                map.put("重庆", new String[] { "CHONG", "QING" });
                return map;
            }

        };
        Pinyin.Config config = Pinyin.newConfig().with(dict);
        Pinyin.init(config);
    }

    private List<SortModel> filledData() {
        List<String> temp = new ArrayList<>();
        temp.add("张三");
        temp.add("重庆");
        temp.add("王五");
        temp.add("倪十");
        temp.add("靳二");
        temp.add("Lee");
        temp.add("Chris");
        temp.add("Alon");
        temp.add("Hell");
        temp.add("Haa");
        temp.add("重量");
        temp.add("Bob");
        temp.add("Xie");
        temp.add("13189673636");
        temp.add("$~");
        temp.add("高进");
        temp.add("阮今天");
        temp.add("齐天大圣");
        temp.add("Jack");
        temp.add("夏先生");
        temp.add("王力宏");
        temp.add("阿郎");
        temp.add("阿妹");
        temp.add("02723617317");
        temp.add("那英");

        List<SortModel> list = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            String str = temp.get(i);
            SortModel model = new SortModel();
            model.name = (str);
            String pinyin = Pinyin.toPinyin(str, "");
            String sortString = pinyin.substring(0, 1).toUpperCase();
            boolean b1 = sortString.matches("[A-Z]");
            boolean b2 = sortString.matches("[0-9]");
            if (b1) {
                model.sortLetters = sortString.toUpperCase();
            } else if (b2) {
                model.sortLetters = "↑";
            } else {
                model.sortLetters = "#";
            }
            list.add(model);
        }
        return list;
    }


}