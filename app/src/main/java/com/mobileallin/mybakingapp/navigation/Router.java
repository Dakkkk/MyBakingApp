package com.mobileallin.mybakingapp.navigation;

import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by Dawid on 2017-11-08.
 */

public class Router {

    private WeakReference<INavigator> navigator;
    private HashMap<String, Bundle> argumentMap;

    public Router() {
        navigator = new WeakReference<>(command -> {
        });
        argumentMap = new HashMap<>();
    }

    public void attachToNavigator(INavigator navigator) {
        this.navigator = new WeakReference<>(navigator);
    }

    public void putCommand(Command command, String key, Bundle args) {
        Log.d(Router.class.getSimpleName(), "putCommand called");

        if (navigator.get() != null) {
            addArguments(key, args);
            navigator.get().handleCommand(command);
        }
        Log.d(Router.class.getSimpleName(), "putCommand called navigator null");

    }

    public Bundle getArguments(String key) {
        return key == null ? new Bundle() :
                argumentMap.containsKey(key) ? argumentMap.get(key) : new Bundle();
    }

    public Bundle removeArguments(String key) {
        return key == null ? new Bundle() :
                argumentMap.containsKey(key) ? argumentMap.remove(key) : new Bundle();
    }

    private void addArguments(String key, Bundle args) {
        if (key != null && args != null) {
            argumentMap.put(key, args);
        }
        Log.d(Router.class.getSimpleName(), "addArguments called key null");

    }
}
