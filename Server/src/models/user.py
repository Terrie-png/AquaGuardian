from flask_bcrypt import Bcrypt
from .base_model import BaseModel
from flask import jsonify
from flask_sqlalchemy import SQLAlchemy


db = SQLAlchemy(app)

from .__init__ import db

class User(db.Model):
        __tablename__ = "User"
u_id = db.Column(db.Integer, primary_key=True)

user_id = db.Column(db.String(4096))
name = db.Column(db.String(4096))
email = db.Column(db.String(4096))
password = db.Column(db.String(4096))
authkey = db.Column(db.String(4096))
login = db.Column(db.Integer)
read_access = db.Column(db.Integer)
write_access = db.Column(db.Integer)

    
def __init__(self, id=None, name=None, email=None, password=None, role=None):
      
        self.u_id = u_id
        self.name = name
        self.email = email
        self.password = password
        self.authkey = authkey
        self.login = login
        self.read_access = read_access
        self.write_access = write_access
        
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