import os

def main():
    os.system("./bin/wkhtmltoimage http://google.com google.jpg")
    os.system('ls')
main()