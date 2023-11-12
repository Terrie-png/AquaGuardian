from flask import Flask, request, json, jsonify, make_response

class EndpointHandler:
    def __init__(self,action):
        self.action = action
        
    def __call__(self, *args, **kwargs):
        response = self.action(*args, **kwargs)
        return make_response(response)

class FlaskServerWrapper:
    def __init__(self, app,**configs):
        self.app = app
        self.configs = configs
        
    def configs(self, **configs):
        for config,value in configs:
            self.app.config[config.upper()] = value
        
    def add_endpoint(self,endpoint=None,endpoint_name=None, handler=None, methods=['GET'], *args, **kwargs):
        self.app.add_url_rule(endpoint, endpoint_name, EndpointHandler(handler), methods=methods, *args, **kwargs)
    
    
    def run(self, **kwargs):
        self.app.run(**kwargs)
