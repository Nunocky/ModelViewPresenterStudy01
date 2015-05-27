package org.nunocky.modelviewpresenterstudy01;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

public class MainActivity extends AppCompatActivity {

    private MortarScope activityScope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String scopeName = getLocalClassName() + "-task-" + getTaskId();
        MortarScope parentScope = MortarScope.getScope(getApplication());
        activityScope = parentScope.findChild(scopeName);

        if (activityScope == null) {
            activityScope = parentScope.buildChild()
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .build(scopeName);
        }

        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            setContentView(new FrameLayout(this));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, new MainActivityFragment(), "fragment")
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if (isFinishing() && activityScope != null) {
            activityScope.destroy();
        }

        super.onDestroy();
    }


    @Override
    public Object getSystemService(@NonNull String name) {
        if (activityScope != null && activityScope.hasService(name))
            return activityScope.getService(name);

        return super.getSystemService(name);
    }

    // private String getScopeName() {
    //     return getClass().getSimpleName();
    // }
}
