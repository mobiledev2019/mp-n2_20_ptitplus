import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

# Fetch the service account key JSON file contents
cred = credentials.Certificate('x.json')

# Initialize the app with a service account, granting admin privileges
db = firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://ptit-plus.firebaseio.com'
})

print(ref.get())