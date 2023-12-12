from ..functions import app, mysql

class BaseController:
    def __init__(self):
        self.mysql = mysql
        self.app = app
        
    # getting cursor
    def getConnection(self):
        cursor = self.mysql.connection.cursor()
        return cursor

    # to test connection
    def testConnection(self):
        cursor = self.getConnection()
        if cursor is None:
            return {"message":"Connection failed"}
        return {"message":"Connection established"}
    
    # commit query for insert update and delete
    def commitQuery(self,query,values):
        cursor = self.getConnection()
        cursor.execute(query, values)
        self.mysql.connection.commit()
        cursor.close()
        
    # getting multiple data together
    def executeQuery(self,query,values):
        cursor = self.getConnection()
        cursor.execute(query, values)
        result = cursor.fetchall()
        cursor.close()
        return result
    
    # getting one data
    def executeQueryOne(self,query,values):
        cursor = self.getConnection()
        cursor.execute(query, values)
        result = cursor.fetchone()
        cursor.close()
        return result
        