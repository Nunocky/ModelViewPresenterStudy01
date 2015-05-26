package org.nunocky.modelviewpresenterstudy01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static MainPresenter presenter;

    MainActivityFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            setContentView(new FrameLayout(this));
            fragment = new MainActivityFragment();
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment, "fragment").commit();
        } else {
            fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentByTag("fragment");
        }

        if (presenter == null) {
            presenter = new MainPresenter();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.takeView(null);
        if (isFinishing()) {
            presenter = null;
        }
    }

    public void updateView(final ArrayList<String> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment.onUpdateView(list);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            presenter.reload();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
