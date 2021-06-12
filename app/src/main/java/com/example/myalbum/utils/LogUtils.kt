package com.example.myalbum.utils

import android.util.Log

object LogUtils {

    /* Different Log Levels available in the App */
    const val LOG_DEBUG = 1
    const val LOG_VERBOSE = 2
    const val LOG_INFO = 3
    const val LOG_WARNING = 4
    const val LOG_ERROR = 5
    const val NO_LOG = 6

    /* The current Log Level of the app*/
    //use BW_NO_LOG to disable logs
    private val BW_LOG_LEVEL = Configuration.LOG_STATUS

    /**
     * Debug log level.
     *
     * @param tag String message to be used as TAG
     * @param message String message to be logged.
     * @return void
     */
    fun d(tag: String, message: String) {
        if (BW_LOG_LEVEL <= LOG_DEBUG) {
            Log.d(tag, message)
        }
    }

    /**
     * Verbose log level.
     *
     * @param tag String message to be used as TAG
     * @param message String message to be logged.
     * @return void
     */
    fun v(tag: String, message: String) {
        if (BW_LOG_LEVEL <= LOG_VERBOSE) {
            Log.v(tag, message)
        }
    }

    /**
     * Information log level.
     *
     * @param tag String message to be used as TAG
     * @param message String message to be logged.
     * @return void
     */
    fun i(tag: String, message: String) {
        if (BW_LOG_LEVEL <= LOG_INFO) {
            Log.i(tag, message)
        }
    }

    /**
     * Warning log level.
     *
     * @param tag String message to be used as TAG
     * @param message String message to be logged.
     * @return void
     */
    fun w(tag: String, message: String) {
        if (BW_LOG_LEVEL <= LOG_WARNING) {
            Log.w(tag, message)
        }
    }

    /**
     * Error log level.
     *
     * @param tag String message to be used as TAG
     * @param message String message to be logged.
     * @return void
     */
    fun e(tag: String, message: String) {
        if (BW_LOG_LEVEL <= LOG_ERROR) {
            Log.e(tag, message)
        }
    }
}