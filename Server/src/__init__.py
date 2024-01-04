import os
from flask import Flask, request, jsonify
from firebase_admin import credentials, initialize_app, auth
from firebase_admin.exceptions import AuthError

app = Flask(__name__)

# Load Firebase Admin SDK credentials from the environment variable
cred_path = os.getenv("FIREBASE_CREDENTIALS_JSON")
if cred_path is None:
    raise ValueError("FIREBASE_CREDENTIALS_JSON environment variable is not set.")

cred = credentials.Certificate(cred_path)
firebase_app = initialize_app(cred)

@app.route("/login", methods=["POST"])
def login():
    try:
        # Ensure request has JSON data
        if request.is_json:
            # Get the ID token from the request data
            id_token = request.json.get("id_token")

            if not id_token:
                return jsonify({"error": "No ID token provided"}), 401

            # Verify the ID token
            decoded_token = auth.verify_id_token(id_token)
            user_id = decoded_token["uid"]

            # Check if the user exists in your database or take necessary actions
            # For simplicity, this example assumes the user exists
            # You can perform additional checks and database operations here

            return jsonify({"user_id": user_id, "authenticated": True}), 200

        else:
            return jsonify({"error": "Request does not contain JSON data"}), 400

    except AuthError as e:
        return jsonify({"error": str(e)}), 401

if __name__ == "__main__":
    app.run(debug=True)
