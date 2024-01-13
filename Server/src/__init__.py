import os
from flask import Flask, request, jsonify, session
from firebase_admin import credentials, initialize_app, auth
from firebase_admin.exceptions import AuthError
from firebase_admin import auth

app = Flask(__name__)
app.secret_key = os.getenv("SECRET_KEY")
# Load Firebase Admin SDK credentials from the environment variable
cred_path = os.getenv("FIREBASE_CREDENTIALS_JSON")
if cred_path is None:
    raise ValueError("FIREBASE_CREDENTIALS_JSON environment variable is not set.")

cred = credentials.Certificate(cred_path)
firebase_app = initialize_app(cred)


    
    
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
    
    
@app.route("/logout", methods=["POST"])
def logout():
    try:
        # Clear the session or perform any other logout actions
        session.clear()

        return jsonify({"message": "User logged out successfully"}), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 500
    
    
    
    
@app.route('/get_user_token', methods=["POST"])
def get_user_token():
    try:
        # Get the ID token from the request data
        id_token = request.json.get("id_token")

        if not id_token:
            return jsonify({"error": "No ID token provided"}), 401

        # Verify the ID token using Firebase Admin SDK
        decoded_token = auth.verify_id_token(id_token)
        user_id = decoded_token["uid"]

        # Get the user's existing custom token (if any)
        user_custom_token = get_or_refresh_token(user_id)

        token_response = {'token': user_custom_token}

        return jsonify(token_response), 200

    except AuthError as e:
        return jsonify({"error": str(e)}), 401

def get_or_refresh_token(user_id):
    try:
        # Create or refresh a custom token for the user using the Firebase Admin SDK
        custom_token = auth.create_custom_token(user_id)

        return custom_token

    except AuthError as e:
        # Handle errors, e.g., token creation failure
        print(f"Error creating custom token: {e}")
        return None


if __name__ == "__main__":
    app.run(debug=True)
