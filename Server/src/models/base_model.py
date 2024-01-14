# Importing necessary modules
from flask_sqlalchemy import SQLAlchemy
from flask import Flask, request, jsonify

# Initializing Flask app and SQLAlchemy
app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:kosys@localhost:3306/aquaguardian'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

# Define BaseModel for both User and SensorData
class BaseModel(db.Model):
    __abstract__ = True
    # Add common columns or methods if needed

class User(BaseModel):
    __tablename__ = "Users"
    u_id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(4096))
    email = db.Column(db.String(4096))
    password=db.Column(db.String(4096))


# Define SensorData model
class SensorData(BaseModel):
    __tablename__ = "SensorData"
    data_id = db.Column(db.Integer, primary_key=True)
    ph_analog_in = db.Column(db.String(4096))
    ph_voltage_in = db.Column(db.String(4096))
    tds_analog_in = db.Column(db.String(4096))
    tds_voltage_in = db.Column(db.String(4096))
    turbidity_analog_in = db.Column(db.String(4096))
    turbidity_voltage_in = db.Column(db.String(4096))
    timestamp = db.Column(db.String(4096))
    user_id = db.Column(db.Integer, db.ForeignKey('User.u_id'))
        # Add other columns as needed
    user = db.relationship('User', backref='sensor_data')

# ... Your routes, serialization, and other functions here ...

if __name__ == "__main__":
    # Run the Flask app
    app.run(debug=True)
