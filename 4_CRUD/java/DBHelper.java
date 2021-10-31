package com.example.crud;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(MainActivity context) {
        super(context, "Userdata.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Userdata(employee_code TEXT primary key,name TEXT,gender TEXT,department TEXT,salary REAL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Userdata");
    }
    public Boolean insertUserdata(String employee_code,String name,String
            gender,String deptartment,Double salary)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("employee_code",employee_code);
        cv.put("name",name);
        cv.put("gender",gender);
        cv.put("department",deptartment);
        cv.put("salary",salary);
        long result=db.insert("Userdata",null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Boolean updateUserdata(String employee_code,String name,Double
            salary)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("salary",salary);
        Cursor cursor=db.rawQuery("SELECT * FROM Userdata WHERE employee_code=?",new String[] {employee_code});
        if(cursor.getCount()>0) {
            long result = db.update("Userdata", cv, "employee_code=?", new
                    String[]{employee_code});
            if (result == -1)
                return false;
            else
                return true;
        }else
        {
            return false;
        }
    }
    public Boolean deleteUserdata(String employee_code)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Cursor cursor=db.rawQuery("SELECT * FROM Userdata WHERE employee_code=?",new String[] {employee_code});
        if(cursor.getCount()>0) {
            long result = db.delete("Userdata", "employee_code=?", new
                    String[]{employee_code});
            if (result == -1)
                return false;
            else
                return true;
        }else
        {
            return false;
        }
    }
    public Cursor getUserdata (String employee_code)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Userdata WHERE employee_code=?",new String[] {employee_code});
        return cursor;
    }
    public Cursor getUserdataDept (String department)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Userdata WHERE department=?",new String[] {department});
        return cursor;
    }
    public Boolean deleteAll()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("Userdata",null,null);
        return true;
    }
}