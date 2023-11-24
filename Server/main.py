import threading
from src.functions import app, channel, pnb
from src.controllers import MySubscribeCallback
from src.routes import api_v1

app.register_blueprint(api_v1)

def active_pubnub():
    pnb.add_listener(MySubscribeCallback())
    pnb.subscribe().channels(channel).execute()


# where i start the server
if __name__ == '__main__':
    active_pubnub()
    app.run(host='0.0.0.0',debug=True, port=5000)
    
