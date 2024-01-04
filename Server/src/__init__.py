import os
from firebase_admin import credentials, initialize_app

# Load Firebase Admin SDK credentials from the environment variable
cred_path = os.getenv("FIREBASE_CREDENTIALS_JSON")

# Initialize Firebase Admin SDK
cred = credentials.Certificate(cred_path)
firebase_app = initialize_app(cred)
