package com.exallium.mvvmexampleapp.presentation;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

public class RxUtils {
    static void overrideAllSchedulers(TestScheduler testScheduler) {
        // Resets us to Zero
        RxJavaPlugins.reset();

        // Overwrite Initialization to avoid touching Android framework
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> testScheduler);

        // Overwrite Factory
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> testScheduler);
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> testScheduler);
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> testScheduler);
    }
}
