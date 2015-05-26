package org.nunocky.modelviewpresenterstudy01;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {

    ArrayAdapter<String> mArrayAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        setListAdapter(mArrayAdapter);
    }

    public void onUpdateView(final ArrayList<String> list) {
        if (mArrayAdapter == null) {
            return;
        }
        mArrayAdapter.clear();
        if (list != null) {
            mArrayAdapter.addAll(list);
            mArrayAdapter.notifyDataSetInvalidated();
        }
    }
}
