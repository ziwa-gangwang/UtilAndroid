package com.cy.File;
import android.content.Context;
import android.content.SharedPreferences;

import com.cy.app.UtilContext;

/**
 * SharedPreferences的一个工具类，调用setParam就能保存String, Integer, Boolean, Float, Long类型的参数
 * 同样调用getParam就能获取到保存在手机里面的数据
 * @author xiaanming
 *
 */
public class UtilSharedPreferences {
	/**
	 * 保存在手机里面的文件名
	 */
	private static final String FILE_NAME = "share_date";
	
	
	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * @param key
	 * @param object 
	 */
	public static void setParam(String key, Object object){
		setParam(FILE_NAME, key, object);
	}
	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * @param key
	 * @param object 
	 */
	public static void setParam(String fileName, String key, Object object){
		String type = object.getClass().getSimpleName();
		SharedPreferences sp = UtilContext.getContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		
		if("String".equals(type)){
			editor.putString(key, (String)object);
		}
		else if("Integer".equals(type)){
			editor.putInt(key, (Integer)object);
		}
		else if("Boolean".equals(type)){
			editor.putBoolean(key, (Boolean)object);
		}
		else if("Float".equals(type)){
			editor.putFloat(type, (Float)object);
		}
		else if("Long".equals(type)){
			editor.putLong(type, (Long)object);
		}
		
		editor.commit();
	}
	
	
	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object getParam(String fileName, String key, Object defaultObject){
		String type;
		if (defaultObject == null) {
			type = "String";
		}else {
			type = defaultObject.getClass().getSimpleName();
		}
		SharedPreferences sp = UtilContext.getContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
		
		if("String".equals(type)){
			return sp.getString(key, (String)defaultObject);
		}
		else if("Integer".equals(type)){
			return sp.getInt(key, (Integer)defaultObject);
		}
		else if("Boolean".equals(type)){
			return sp.getBoolean(key, (Boolean)defaultObject);
		}
		else if("Float".equals(type)){
			return sp.getFloat(type, (Float)defaultObject);
		}
		else if("Long".equals(type)){
			return sp.getLong(type, (Long)defaultObject);
		}
		
		return defaultObject;
	}
	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object getParam( String key, Object defaultObject){
		return getParam(FILE_NAME, key, defaultObject);
	}
}
