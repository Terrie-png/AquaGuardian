from src.functions import app
from flask_migrate import Migrate

from src.routes import api_v1

app.register_blueprint(api_v1)


# where i start the server
if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)