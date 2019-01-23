import flask, qldt_schedule_creator, os, json
from flask import Flask, request, jsonify
app = Flask(__name__)

@app.route('/api', methods=['GET'])
def api():
    msg = request.args.get('last user freeform input')
    rps_text, rps_url = qldt_schedule_creator.main(msg, debug = False)
    print("IMAGE URL -> {}".format(rps_url))
    j = {
        "messages":[
            {"text":rps_text}
        ]
    }
    # j = {
    #     "messages":[
    #         {"text":rps_text},
    #         {'attachment':{'type':'image','payload':{'url':rps_url}}}
    #     ]
    # }
    # print(j)
    return jsonify(j)



@app.route('/test', methods=['GET'])
def test():
    msg = request.args.get('id')
    rps_text, rps_url = qldt_schedule_creator.main(msg, debug = True)
    print("IMAGE URL -> {}".format(rps_url))
    # j = {
    #     "messages":[
    #         {"text":rps_text}
    #     ]
    # }
    j = {
        "messages":[
            {"text":rps_text},
            {'attachment':{'type':'image','payload':{'url':rps_url}}}
        ]
    }
    # print(j)
    return jsonify(j)


@app.route('/', methods=['GET'])
def index():
    return 'hello ^^,'
if __name__ == '__main__':
    port = int(os.environ.get('PORT', 5000))
    app.run(host='0.0.0.0', port = port)