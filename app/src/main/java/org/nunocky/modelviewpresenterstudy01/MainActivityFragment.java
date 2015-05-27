package org.nunocky.modelviewpresenterstudy01;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import mortar.dagger1support.ObjectGraphService;


// MEMO: ObjectGraphService.inject() はonCreateよりも後で呼ばないと(というかFragmentのビューの準備ができていないと)
//       コンフィギュレーションの変化時に落ちる

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {

    @Inject
    MainPresenter presenter;

    ArrayAdapter<String> mArrayAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ObjectGraphService.inject(getActivity(), this);

        mArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        setListAdapter(mArrayAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.takeView(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_reload) {
            presenter.reload();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateView(final ArrayList<String> list) {
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
