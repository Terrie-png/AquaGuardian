from src.models.user import User
from .base_controller import BaseController
from flask import request 
from flask_bcrypt import Bcrypt
import json

class UserController(BaseController):
    
    def __init__(self):
        super().__init__()
    
    def get_user(self, id):
        return None
    
    def create_user(self, name, email, password, role):
        user = User(name=name, email=email, password=password, role=role) 
        return None
    
    def update_user(self, id, name, email, password, role):
        return None
    
    def delete_user(self, id):
        return None
    
    def login(self):
        # getting the request data
        data = request.form
        
        # check if request data is empty
        if data is None or data == {}:
            return json.dumps({"error":"All fields are required"})
        
        # check if email exist in request data
        if "email" not in data.keys():
            return json.dumps({"error":"Email is required"})
        
        # check if password exist in request data
        if "password" not in data.keys():
            return json.dumps({"error":"Password is required"})
        
        # declaring user object and email and password
        email = data['email']
        password = data['password']
        user = User(email=email, password=password)
        
        # querying selct from database
        query = "SELECT * FROM users WHERE email = %s"
        value = (user.email,)
        result = self.executeQueryOne(query, value)
        
        if result is None:
            return {"error":"User does not exist"}
        
        # declaring user object and put the result in it
        result_user = User()
        result_user.deserialize(result)
        
        # check if user is none or password is not correct
        if result_user is None:
            return json.dumps({"error":"Invalid credentials"})
        
        # check if email's password have the same password as the request data
        if not result_user.check_password(user.password):
            return json.dumps({"error":"Invalid credentials"})
        
        # return json formatted result
        return result_user.serialize()
        
    def register(self):
        # declaring encryption object
        bcrypt = Bcrypt(self.app)
        
        # getting request data
        data = request.form
        
        # validating request data
        if data is None or data == {}:
            return json.dumps({"error":"All fields are required"})
        if "email" not in data.keys():
            return json.dumps({"error":"Email is required"})
        email = data['email']
        
        # check if email exist
        query = "SELECT * FROM users WHERE email = %s"
        values = (email,)
        result = self.executeQueryOne(query, values)
        
        # if email exist return error
        if result is not None:
            print(result)
            return json.dumps({"error":"Email already exists"})
        
        # if password is not in request data return error
        if "password" not in data.keys():
            return json.dumps({"error":"Password is required"})
        
        # encrypt the password
        password = bcrypt.generate_password_hash(data['password']).decode('utf-8')
        
        # to define the name as is nullable
        if "name" not in data.keys() or data['name'] == "":
            name = ""
        else:
            name = data['name']
        
        # declaring user object
        user = User(name=name, email=email, password=password, role="user")
        
        # querying the database for insert
        query = "INSERT INTO users (name, email, password, role) VALUES (%s, %s, %s, %s)"
        values = (user.name, user.email, user.password, user.role,)
        self.commitQuery(query, values)
        
        # querying the database for select
        query = "SELECT * FROM users WHERE email = %s"
        values = (user.email,)
        result = self.executeQueryOne(query, values)
        
        # put the result in the user object
        user.deserialize(result)
        
        # return the result in json format
        return user.serialize()

    