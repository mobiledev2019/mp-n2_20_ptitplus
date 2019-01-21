import flask, qldt_schedule_creator, os
from flask import Flask, request, json
app = Flask(__name__)

@app.route('/api', methods=['GET'])
def api():
    msg = request.args.get('last user freeform input')
    result = qldt_schedule_creator.main(msg)
    j = {
        "message":{
            "text":result
        }
    }
    return json.dumps(j)

@app.route('/', methods=['GET'])
def index():
    return 'hello ^^,'
port = int(os.environ.get('PORT', 5000))
app.run(host='0.0.0.0', port = port)