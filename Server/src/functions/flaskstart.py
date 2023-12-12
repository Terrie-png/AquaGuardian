# not needed anymore as found a better way to structure
#  ref : https://medium.com/@arslanaut/minimal-flask-application-using-mvc-design-pattern-842845cef703



# from flask import Flask, request, json, jsonify, make_response

# class EndpointHandler:
#     def __init__(self,action):
#         self.action = action
        
#     def __call__(self, *args, **kwargs):
#         response = self.action(*args, **kwargs)
#         return make_response(response)

# class FlaskServerWrapper(Flask):
#     def __init__(self, name,**configs):
#         super().__init__(name)
#         self.configs = configs
        
#     def configs(self, **configs):
#         for config,value in configs:
#             self.config[config.upper()] = value
        
#     def add_endpoint(self,endpoint=None,endpoint_name=None, handler=None, methods=['GET'], *args, **kwargs):
#         super().add_url_rule(endpoint, endpoint_name, EndpointHandler(handler), methods=methods, *args, **kwargs)

#     def run(self, **kwargs):
#         super().run(**kwargs)
