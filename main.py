import flask, qldt_schedule_creator
from flask import Flask, request
app = Flask(__name__)

@app.route('/api', methods=['GET'])
def api():
    msg = request.args.get('id')
    return qldt_schedule_creator.main(msg)

@app.route('/', methods=['GET'])
def index():
    return 'hello ^^,'

app.run()