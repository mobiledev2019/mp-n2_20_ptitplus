import flask, qldt_schedule_creator, os, json
from flask import Flask, request, jsonify
app = Flask(__name__)

@app.route('/api', methods=['GET'])
def api():
    msg = request.args.get('last user freeform input')
    rps_text, rps_url = qldt_schedule_creator.main(msg)
    j = {
        "messages":[
            {"text":rps_text},
            {'attachment':{'type':'image','payload':{'url':rps_url}}}
        ]
    }
    print(j)
    return jsonify(j)

@app.route('/test', methods=['GET'])
def test():
    test_1950 = {
        "messages": [
            {
            "attachment": {
                "type": "image",
                "payload": {
                "url": "https://res.cloudinary.com/bachvkhoa/image/upload/v1548167300/ptit/B15DCCN334_weekly_22-01-2019_xs6ler.jpg"
                }
            }
            }
        ]
    }
    return jsonify(test_1950)

@app.route('/', methods=['GET'])
def index():
    return 'hello ^^,'
port = int(os.environ.get('PORT', 5000))
app.run(host='0.0.0.0', port = port)