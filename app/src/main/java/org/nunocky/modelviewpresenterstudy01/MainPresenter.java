package org.nunocky.modelviewpresenterstudy01;

import android.support.annotation.Nullable;

import java.util.ArrayList;

public class MainPresenter {
    private static final String TAG = "MainPresenter";

    RandomNumberModel model;

    @Nullable
    private MainActivity mMainActivity;

    public MainPresenter() {
    }

    public void takeView(@Nullable MainActivity mainActivity) {
        if (model == null) {
            model = new RandomNumberModel();
            startQuery();
        }

        mMainActivity = mainActivity;
        updateView(model.getResult());
    }

    private void updateView(ArrayList<String> list) {
        if (mMainActivity != null) {
            if (model != null) {
                mMainActivity.updateView(list);
            }
        }
    }

    public void reload() {
        if (model.isRunning()) {
            return;
        }

        model.clear();
        startQuery();
    }

    public void startQuery() {

        model.query(new RandomNumberModel.Listener() {
            @Override
            public void callback(final ArrayList<String> list) {
                updateView(list);
            }
        });
    }
}