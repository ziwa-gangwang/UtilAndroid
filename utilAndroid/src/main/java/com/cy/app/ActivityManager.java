package com.cy.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class ActivityManager {

	private static Stack<Activity> activityStack;
	private static ActivityManager instance;

	private ActivityManager(){}
	/**
	 * 单一实例
	 */
	public static ActivityManager getActivityManager(){
		if(instance==null){
			instance=new ActivityManager();
		}
		return instance;
	}
	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivityInStack(){
		Activity activity=activityStack.lastElement();
		return activity;
	}

	/**
	 * 获取当前task的stack的top Activity，含包名
	 */
	public static String currentActivity(){
		android.app.ActivityManager am = (android.app.ActivityManager) UtilContext.getContext().getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		return cn.toString();
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity(){
		Activity activity=activityStack.lastElement();
		finishActivity(activity);
	}
	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity){
		if(activity!=null){
			activityStack.remove(activity);
			activity.finish();
			activity=null;
		}
	}
	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls){
		for (Activity activity : activityStack) {
			if(activity.getClass().equals(cls) ){
				finishActivity(activity);
			}
		}
	}
	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity(){
		for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
            	activityStack.get(i).finish();
            }
	    }
		activityStack.clear();
	}
	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			android.app.ActivityManager activityMgr= (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {	}
	}

	/**
	 * @param context
	 * @return
	 */
	public boolean isAppOnForeground(Context context) {
		android.app.ActivityManager am = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String currentPackageName = cn.getPackageName();
		if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
			return true;
		}

		return false;
	}

	/**给定activity类名 是否是当前task的stack的top Activity
	 * @return
	 */
	public static boolean isCurrentActivityTop(String className){
		return currentActivity().contains(className);
	}
}