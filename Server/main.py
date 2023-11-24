from src.functions import app, channel, pnb
from src.controllers import activePubnub
from src.routes import api_v1
import sys
import signal
import atexit

app.register_blueprint(api_v1)

def signal_handler(sig, frame):
    print("Ctrl+C pressed. Cleaning up...")
    sys.exit(0)

# Register the exit function
atexit.register(pnb.stop)

# Register the signal handler for Ctrl+C
signal.signal(signal.SIGINT, signal_handler)

# Start PubNub
activePubnub()
# where i start the server
if __name__ == '__main__':
    activePubnub()
    signal.signal(signal.SIGINT, signal_handler)
    app.run(host='0.0.0.0',debug=True)
    