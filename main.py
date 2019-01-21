import flask, qldt_schedule_creator, os, json
from flask import Flask, request, jsonify
app = Flask(__name__)

@app.route('/api', methods=['GET'])
def api():
    msg = request.args.get('last user freeform input')
    result = qldt_schedule_creator.main(msg)
    j = {
        "messages":[
            {"text":result}
        ]
    }
    return jsonify(j)

@app.route('/test', methods=['GET'])
def test():
    test_1950 = {
    "messages": [
            {"text": "Welcome to the Chatfuel Rockets!"},
            {"text": "What are you up to?"}
        ]
    }
    return jsonify(test_1950)

@app.route('/', methods=['GET'])
def index():
    return 'hello ^^,'
port = int(os.environ.get('PORT', 5000))
app.run(host='0.0.0.0', port = port)