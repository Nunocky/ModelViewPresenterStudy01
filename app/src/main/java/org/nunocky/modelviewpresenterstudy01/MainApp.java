package org.nunocky.modelviewpresenterstudy01;

import android.app.Application;

import dagger.ObjectGraph;
import mortar.MortarScope;
import mortar.dagger1support.ObjectGraphService;

public class MainApp extends Application {

    private MortarScope rootScope;

    @Override
    public Object getSystemService(String name) {
        if (rootScope == null) {
            rootScope = MortarScope.buildRootScope()
                    .withService(ObjectGraphService.SERVICE_NAME, ObjectGraph.create(new RootModule()))
                    .build("Root");
        }

        return rootScope.hasService(name) ? rootScope.getService(name) : super.getSystemService(name);
    }
}