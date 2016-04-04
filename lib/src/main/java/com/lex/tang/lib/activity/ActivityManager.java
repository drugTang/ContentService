package com.lex.tang.lib.activity;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tang on 2016/4/2.
 */
public class ActivityManager {
    private static List<Activity> activities = new LinkedList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for(Activity activity : activities) {
            if(activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static boolean containClass(Class<?> cls) {
        Activity tempActivity = null;
        for(Activity activity : activities) {
            if(activity.getClass().equals(cls)) {
                tempActivity = activity;
            }
        }
        return activities.contains(tempActivity);
    }

    /**
     * 结束单个Activity
     * @param activity
     */
    public static void finishSingleActivity(Activity activity) {
        if(activity != null) {
            activity.finish();
            if(activities.contains(activity)) {
                activities.remove(activity);
            }
        }
    }

    /**
     * 通过类名结束单个Activity
     * @param cls
     */
    public static void finishSingleActivityByClass(Class<?> cls) {
        Activity tempActivity = null;
        for(Activity act : activities) {
            if(act.getClass().equals(cls)) {
                tempActivity = act;
            }
        }
        finishSingleActivity(tempActivity);
    }
}
