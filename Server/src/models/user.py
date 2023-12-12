from flask_bcrypt import Bcrypt
from .base_model import BaseModel
from flask import jsonify

class User(BaseModel):
    def __init__(self, id=None, name=None, email=None, password=None, role=None):
        super().__init__()
        self.id = id
        self.name = name
        self.email = email
        self.password = password
        self.role = role
        
    def serialize(self):
        return jsonify({
            'id': self.id,
            'name': self.name,
            'email': self.email,
            'role': self.role
        })
        
        
    def deserialize(self, data):

        if(type(data) is not dict):
            self.id = data[0]
            self.name = data[2]
            self.email = data[1]
            self.password = data[3]
            self.role = data[4]
            
        if(type(data) is dict):
            self.id = data['id']
            self.name = data['name']
            self.email = data['email']
            self.password = data['password']
            self.role = data['role']
            
    def check_password(self, password):
        bcrypt = Bcrypt()
        return bcrypt.check_password_hash(self.password, password)
            
    def __eq__(self, other):
        if isinstance(other, User):
            return self.id == other.id
        return False
    
    def __dir__(self):
        return {'id':self.id, 'name':self.name, 'email':self.email, 'password':self.password, 'role':self.role}