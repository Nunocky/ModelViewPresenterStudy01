package org.nunocky.modelviewpresenterstudy01;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Random;

public class RandomNumberModel {
    private static final String TAG = "RandomNumberModel";

    private ArrayList<String> mResult;
    private boolean mRunning = false;

    public RandomNumberModel() {
    }

    public ArrayList<String> getResult() {
        if (isRunning())
            return null;

        return mResult;
    }

    public void query(final Listener listener) {

        if (isRunning())
            return;

        if (mResult != null) {
            listener.callback(mResult);
            return;
        }

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                mRunning = true;
                if (mResult == null) {
                    mResult = new ArrayList<>();
                } else {
                    mResult.clear();
                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                Random rd = new Random();

                for (int i = 0; i < 10; i++) {
                    int n = rd.nextInt();
                    String s = Integer.toString(n);
                    mResult.add(s);

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ignored) {
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (listener != null) {
                    listener.callback(mResult);
                }
                mRunning = false;
            }
        };

        task.execute();
    }

    public void clear() {
        mResult = null;
    }

    public boolean isRunning() {
        return mRunning;
    }

    public interface Listener {
        void callback(ArrayList<String> list);
    }
}
