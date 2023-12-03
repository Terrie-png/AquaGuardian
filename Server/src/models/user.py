from flask_bcrypt import Bcrypt
from .base_model import BaseModel
from flask import jsonify
from flask_sqlalchemy import SQLAlchemy


db = SQLAlchemy()

class User(db.BaseModel):
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

    
def __init__(self, u_id, name, email, password,authkey, login, read_access, write_access ):
      
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
            


def delete_all():
    try:
        db.session.query(User).delete()
        db.session.commit()
        print("Delete all")
    except Exception as e:
        print("Failed " + str(e))
        db.session.rollback()

def get_user_row_if_exists(u_id):
    get_user_row = User.query.filter_by(u_id=u_id).first()
    if get_user_row is not None:
        return get_user_row
    else:
        print("This user does not exist")
        return False
    
  
def add_user_and_login(u_id,name,email,passwword):
    row = get_user_row_if_exists(u_id)
    if row is not False:
        row.login = 1
        db.session.commit()
    else:
        print("Adding user " + name)
        new_user = User(u_id, name, email, password, None, 1, 0, 0)
        db.session.add(new_user)
        db.session.commit()  
        
def user_logout(u_id):
    row = get_user_row_if_exists(u_id)
    if row is not False:
        row.login = 0
        db.session.commit()
        print("User " + row.name + " logged out")   
    
def add_auth_key(u_id, auth_key):
    row = get_user_row_if_exists(u_id)
    if row is not False:
        row.authkey = auth_key
        db.session.commit()
        print("User " + row.name + "authkey added")

def __eq__(self, other):
        if isinstance(other, User):
            return self.id == other.id
        return False
    
def __dir__(self):
        return {'id':self.id, 'name':self.name, 'email':self.email, 'password':self.password, 'role':self.role}