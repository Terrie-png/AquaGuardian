from src.models.user import User
from .base_controller import BaseController
import json

class UserController(BaseController):
    def get_user(self, id):
        return None
    
    def create_user(self, name, email, password, role):
        user = User(name=name, email=email, password=password, role=role) 
        return None
    
    def update_user(self, id, name, email, password, role):
        return None
    
    def delete_user(self, id):
        return None
    
    def login(self, email, password):
        if email is None:
            return json.dumps({"error":"Email is required"})
        if password is None:
            return json.dumps({"error":"Password is required"})
        
        user = User.login(email, password)
        if user is None:
            return json.dumps({"error":"Invalid credentials"})
        return user.serialize()
    
    def register(self, name, email, password, role):
        json_data = json.dumps({"name":name,"email":email,"password":password,"role":role})
        return json_data