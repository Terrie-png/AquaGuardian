from flask import Flask, request, json, jsonify, make_response
import os
from dotenv import load_dotenv
from chemflask import FlaskServerWrapper, EndpointHandler
from flask_mysqldb import MySQL



flask_app = Flask(__name__)
app = FlaskServerWrapper(flask_app)
# app.app.config.from_mapping(config)

def create_response(json_data, status_code=200):
    response = make_response(jsonify(json_data), 200)
    response.headers['Content-Type'] = 'application/json'
    return response

def index():
    users = [{"name": "John"}, {"name": "Jane"}]
    return create_response(users)




app.add_endpoint(endpoint="/",endpoint_name="index", handler=EndpointHandler(index), methods=['GET'])
if __name__ == '__main__':
    app.run(debug=True)