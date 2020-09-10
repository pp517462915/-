package com.example.newsdemo.ui.newsChannal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.newsdemo.NewsDemoApplication;
import com.example.newsdemo.logic.room.entity.News;
import com.example.newsdemo.logic.room.entity.NewsChannal;
import com.example.newsdemo.ui.utils.NewsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddNewsChannalDialog extends DialogFragment {
    private final String TAG = "AddNewsChannalDialog";

    private List<NewsChannal> missingNewsChannalList;
    private NewsChannalFragment newsChannalFragment;

    public AddNewsChannalDialog(NewsChannalFragment newsChannalFragment, List<NewsChannal> nowNewsChannalList) {
        this.newsChannalFragment = newsChannalFragment;
        this.missingNewsChannalList = new ArrayList<>();

        Map typeOfRoomToInternet = NewsUtils.getInstance().getTypeOfRoomToInternet();
        for (Object channalNa : typeOfRoomToInternet.keySet()) {
            String channalName = (String)channalNa;
            boolean flag = true;
            for (NewsChannal newsChannal: nowNewsChannalList) {
                if (newsChannal.getChannalName().equals(channalName)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                NewsChannal newsChannal = new NewsChannal();
                newsChannal.setChannalName(channalName);
                this.missingNewsChannalList.add(newsChannal);
            }
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        List<String> list = new ArrayList<>();
        for (NewsChannal newsChannal:missingNewsChannalList) {
            list.add(newsChannal.getChannalName());
        }
        ListAdapter listAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, list);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择")
                .setAdapter(listAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newsChannalName = list.get(which);
                        newsChannalFragment.getViewModel().insertNewsChannal(newsChannalName);
                        newsChannalFragment.getViewModel().getNewsChannalData();
                    }
                });
        return builder.create();
    }
}
