from flask import Blueprint

from Server.src.controllers import pubnub_Controller, getSensorData
from ..controllers import UserController, BaseController

# where i declare my blurprint and for server api
userController = UserController()
baseController = BaseController()

api_v1 = Blueprint("apiV1", __name__, url_prefix="/api/v1")
api_v1.route("/login",methods=["POST"])(userController.login)
api_v1.route("/register",methods=["POST"])(userController.register)

api_v1.route("/dbConnect",methods=["GET"])(baseController.testConnection)

api_v1.route("/sensorData",methods=["GET"])(getSensorData)