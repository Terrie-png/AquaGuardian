# from flask import Flask, request, json, jsonify, make_response
# import os
# from dotenv import load_dotenv
# from src.functions import FlaskServerWrapper, EndpointHandler
# from flask_mysqldb import MySQL
# from src.controllers.user_controller import UserController
# from src.models import User

# load_dotenv()
# app = Flask(__name__)


# app.config['MYSQL_HOST'] = os.getenv('MYSQL_HOST')
# app.config['MYSQL_USER'] = os.getenv('MYSQL_USER')
# app.config['MYSQL_PASSWORD'] = os.getenv('MYSQL_PASSWORD')
# app.config['MYSQL_DB'] = os.getenv('MYSQL_DB')


# @app.route("/")
# def index():
#     user = User(email="testing@gmail.com")
#     return user.login()
#     return "hellow world!"
# # def create_response(json_data, status_code=200):
# #     response = make_response(jsonify(json_data), 200)
# #     response.headers['Content-Type'] = 'application/json'
# #     return response

# # def index():
# #     users = [{"name": "John"}, {"name": "Jane"}]
# #     return create_response(users)

# # def login():
# #     username = request.form['username']
# #     password = request.form['password']
# #     data = UserController.login(username, password)
# #     return create_response(data)

# # def register():
# #     name = request.form['name']
# #     email = request.form['email']
# #     password = request.form['password']
# #     role = request.form['role']
# #     data = UserController.register(name, email, password, role)
# #     if data["error"]:
# #         return create_response(data, 400)
# #     return create_response(data)

# # app.add_endpoint(endpoint="/",endpoint_name="index", handler=EndpointHandler(index), methods=['GET'])
# # app.add_endpoint(endpoint="/api/v1/user/register",endpoint_name="register", handler=EndpointHandler(register), methods=['POST'])
# # app.add_endpoint(endpoint="/api/v1/user/login",endpoint_name="login", handler=EndpointHandler(login), methods=['POST'])



# # @app.route("/user/login",methods=["POST"])
# # def login():
# #     username = request.form['username']
# #     password = request.form['password']
# #     token = user.login(username, password)
# #     return jsonify(token,status=200,mimetype='application/json')



# if __name__ == '__main__':
#     app.run()