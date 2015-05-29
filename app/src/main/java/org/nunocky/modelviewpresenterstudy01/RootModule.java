package org.nunocky.modelviewpresenterstudy01;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {MainActivityFragment.class} // 複数クラスへの injectは {}で囲む
)
public class RootModule {
    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
