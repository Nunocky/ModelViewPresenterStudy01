package org.nunocky.modelviewpresenterstudy01;

import android.support.annotation.Nullable;

import java.util.ArrayList;

public class MainPresenter {
    RandomNumberModel model;

    @Nullable
    private MainActivityFragment mFragment;

    public MainPresenter() {
        model = new RandomNumberModel();
    }

    public void takeView(@Nullable MainActivityFragment fragment) {
        mFragment = fragment;
        updateView(model.getResult());
    }

    private void updateView(final ArrayList<String> list) {
        if (mFragment != null) {
            mFragment.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mFragment.updateView(list);
                }
            });
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