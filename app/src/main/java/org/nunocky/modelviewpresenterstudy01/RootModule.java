package org.nunocky.modelviewpresenterstudy01;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = MainActivityFragment.class
)
public class RootModule {
    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
