from concurrent.futures import thread
import os
from flask import Flask, request, jsonify, session
from firebase_admin import credentials, initialize_app, auth
from firebase_admin import auth
from flask_sqlalchemy import SQLAlchemy
import threading

from Server.src.controllers import pubnub_Controller

db = SQLAlchemy()


app = Flask(__name__)
app.secret_key = os.getenv("SECRET_KEY")
# Load Firebase Admin SDK credentials from the environment variable
cred_path = os.getenv("FIREBASE_CREDENTIALS_JSON")
if cred_path is None:
    raise ValueError("FIREBASE_CREDENTIALS_JSON environment variable is not set.")

cred = credentials.Certificate(cred_path)
firebase_app = initialize_app(cred)

# Registration endpoint
@app.route('/api/register', methods=['POST'])
def register_user():
    data = request.get_json()
    token = data.get('firebaseToken')
    model_number = data.get('modelNumber')

    if not token or not model_number:
        return jsonify({"error": "Missing required fields"}), 400

    try:
        # Verify Firebase ID token and get user's info
        decoded_token = auth.verify_id_token(token)
        uid = decoded_token['uid']
        email = decoded_token.get('email')
        name = decoded_token.get('name') or email

        # Check if user already exists
        cursor = db.cursor()
        cursor.execute('SELECT * FROM User WHERE firebaseUID = %s', (uid,))
        existing_user = cursor.fetchone()

        if existing_user:
            return jsonify({"error": "User already exists"}), 400

        # Insert new user into MySQL database
        cursor.execute('INSERT INTO User (name, email, firebaseUID) VALUES (%s, %s, %s)', (name, email, uid))
        db.commit()
        user_id = cursor.lastrowid

        # Insert the SensorData associated with the user
        cursor.execute('INSERT INTO SensorData (modelNumber, userID, batteryLevel) VALUES (%s, %s, %s)', (model_number, user_id, 100))
        db.commit()
        cursor.close()

     
        return jsonify({"message": "User registered successfully", "userId": user_id}), 201

    except Exception as e:
        return jsonify({"error": str(e)}), 400
    
    
    # Login endpoint
@app.route('/api/login', methods=['POST'])
def login_user():
    data = request.get_json()
    token = data.get('firebaseToken')

    if not token:
        return jsonify({"error": "Missing required fields"}), 400

    try:
        # Verify Firebase ID token and get user's info
        decoded_token = auth.verify_id_token(token)
        uid = decoded_token['uid']

        # Check if user exists
        cursor = db.cursor()
        cursor.execute('SELECT * FROM User WHERE firebaseUID = %s', (uid,))
        user = cursor.fetchone()

        if not user:
            return jsonify({"error": "User not found"}), 404

        cursor.close()

        return jsonify({"message": "User logged in successfully", "userId": user[0]}), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 400
    

    
    # Logout endpoint
@app.route('/api/logout', methods=['POST'])
def logout_user():
    data = request.get_json()
    token = data.get('firebaseToken')

    if not token:
        return jsonify({"error": "Missing required fields"}), 400

    try:
        # Verify Firebase ID token and get user's info
        decoded_token = auth.verify_id_token(token)
        uid = decoded_token['uid']

        # Perform the logout operation (if needed)
        # This might include invalidating session tokens, removing device tokens, etc.

        return jsonify({"message": "User logged out successfully"}), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 400
    
    
    
def background_task():
        background_task_thread = threading.Thread(target=pubnub_Controller.activePubnub)
        background_task_thread.start()


if __name__ == "__main__":
    app.run(debug=True)
    background_task()
