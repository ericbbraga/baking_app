package ericbraga.bakingapp.runner;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import ericbraga.bakingapp.app.MockApp;

public class TestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        return super.newApplication(cl, MockApp.class.getCanonicalName(), context);
    }
}
