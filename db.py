import firebase_admin, datetime
from firebase_admin import credentials
from firebase_admin import db
creds = credentials.Certificate('x.json')

class Firebase(object):
	""" 
	This class interact with firebase on the cloud
	"""
	def __init__(self):
		super(Firebase, self).__init__()
		firebase_admin.initialize_app(creds, {
			'databaseURL':'https://ptit-plus.firebaseio.com'
		})
		self.db = db
	def check_student_exist_in_db(self, student_id):
		student_id = student_id.upper()
		ref = self.db.reference('students')
		snapshot = ref.order_by_child('student_id').equal_to(student_id).get()
		if snapshot: return True
		return False

	def add_student_to_

def main():
	print("FIREBASE INITIALIZING...", end='')
	firebase = Firebase()
	print("FIREBASE INITIATED!")
	print(firebase.check_student_exist_in_db('b15dccn549'))

if __name__ == '__main__':
	diff = datetime.datetime.now()
	main()
	print("All tasks toke {} seconds to finish!".format(datetime.datetime.now() - diff))