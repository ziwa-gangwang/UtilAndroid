package com.cy.app;

import android.util.Log;

import static com.cy.security.UtilAES.TAG;

/**
 * Created by cy on 2017/12/31.
 */

public class TestLog {
    public static void showLog(String msg){
        StackTraceElement[] stackTraceElement = Thread.currentThread()
                .getStackTrace();
        int currentIndex = -1;
        for (int i = 0; i < stackTraceElement.length; i++) {
            if (stackTraceElement[i].getMethodName().compareTo("showLog") == 0)
            {
                currentIndex = i + 1;
                break;
            }
        }

        String fullClassName = stackTraceElement[currentIndex].getClassName();
        String className = fullClassName.substring(fullClassName
                .lastIndexOf(".") + 1);
        String methodName = stackTraceElement[currentIndex].getMethodName();
        String lineNumber = String
                .valueOf(stackTraceElement[currentIndex].getLineNumber());

        Log.w(TAG, msg);
        Log.w(TAG, "at " + fullClassName + "." + methodName + "(" + className + ".java:" + lineNumber + ")");
        Log.w(TAG, "at " + "(" + className + ".java:" + lineNumber + ")");
        Log.w(TAG, "(" + className + ".java:" + lineNumber + ")");
        Log.w("at " + fullClassName + "." + methodName + "(" + className + ".java:" + lineNumber + ")", msg);
        Log.w("(" + className + ".java:" + lineNumber + ")", msg);
        Log.w(methodName + "(" + className + ".java:" + lineNumber + ")", msg);

    }
}
