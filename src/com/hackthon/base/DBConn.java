package com.hackthon.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	Connection _CONN = null;  
    
    //取得连接  
    private boolean GetConn(String sUser, String sPwd) {  
        if(_CONN!=null)return true;  
        try {             
            String sDriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
            //Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            // String sDBUrl ="jdbc:sqlserver://192.168.0.74;databaseName=wakeup";  
            String sDBUrl = "jdbc:sqlserver://211.144.121.114:1433;databaseName=TVDramaDemoOfBlog";  
  
            Class.forName(sDriverName);  
            _CONN = DriverManager.getConnection(sDBUrl, sUser, sPwd);  
  
        } catch (Exception ex) {  
            // ex.printStackTrace();  
            System.out.println(ex.toString());  
            return false;  
        }  
        return true;  
    }  
      
    private boolean GetConn()  
    {  
        return GetConn("xxfeng","xxfeng1990");  
    }  
      
    //关闭连接  
    private void CloseConn()  
    {  
        try {  
            _CONN.close();  
            _CONN = null;  
        } catch (Exception ex) {  
            System.out.println(ex.getMessage());  
            _CONN=null;   
        }  
    }  
   
      
    //测试连接  
    public boolean TestConn() {  
        if (!GetConn())  
            return false;  
  
        CloseConn();  
        return true;  
    }  
      
    public ResultSet GetResultSet(String sSQL,Object[] objParams)  
    {  
        GetConn();  
        ResultSet rs=null;  
        try  
        {  
            PreparedStatement ps = _CONN.prepareStatement(sSQL);  
            if(objParams!=null)  
            {  
                for(int i=0;i< objParams.length;i++)  
                {  
                    ps.setObject(i+1, objParams[i]);  
                }  
            }  
            rs=ps.executeQuery();  
        }catch(Exception ex)  
        {  
            System.out.println(ex.getMessage());  
            CloseConn();  
        }  
        finally  
        {  
            //CloseConn();            
        }  
        return rs;  
    }  
      
    public Object GetSingle(String sSQL,Object... objParams)  
    {  
        GetConn();  
        try  
        {  
            PreparedStatement ps = _CONN.prepareStatement(sSQL);  
            if(objParams!=null)  
            {  
                for(int i=0;i< objParams.length;i++)  
                {  
                    ps.setObject(i+1, objParams[i]);  
                }  
            }  
            ResultSet rs=ps.executeQuery();  
            if(rs.next())  
                return rs.getString(1);//索引从1开始  
        }catch(Exception ex)  
        {  
            System.out.println(ex.getMessage());  
        }  
        finally  
        {  
            CloseConn();              
        }  
        return null;  
    }  
  
    public int UpdateData(String sSQL,Object[] objParams)  
    {  
        GetConn();  
        int iResult=0;  
          
        try  
        {  
            PreparedStatement ps = _CONN.prepareStatement(sSQL);  
            if(objParams!=null)  
            {  
                for(int i=0;i< objParams.length;i++)  
                {  
                    ps.setObject(i+1, objParams[i]);  
                }  
            }  
            iResult = ps.executeUpdate(sSQL);  
        }catch(Exception ex)  
        {  
            System.out.println(ex.getMessage());  
            return -1;  
        }  
        finally  
        {  
            CloseConn();              
        }  
        return iResult;  
    }  
}

