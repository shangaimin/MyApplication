package liangpin.myapplication.database;

import android.provider.BaseColumns;

/**
 * Created by Admin on 2017/1/9.
 */

public class VersionInfo implements BaseColumns {
    public static final String TABLE_NAME = "push_info_table";
    public static final String TYPE = "type";
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String DATE = "date";
    public static final String URI = "uri";

    public static final String IS_READ = "is_read";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "(" + VersionInfo._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VersionInfo.TYPE + " INTEGER, "
            + VersionInfo.TITLE + " TEXT, "
            + VersionInfo.MESSAGE + " TEXT, "
            + VersionInfo.DATE + " TEXT, "
            + VersionInfo.URI + " TEXT, "
            + VersionInfo.IS_READ + " TEXT"+")";


}
