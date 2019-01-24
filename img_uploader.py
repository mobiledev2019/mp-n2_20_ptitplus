import requests, time, json, os
import cloudinary.uploader as uploader

def up(img):
    cloudname = 'bachvkhoa'
    preset_code = 'anhoiemup'
    print("IMG_UPLOADER ls -> ")
    os.system('ls')
    rps = uploader.unsigned_upload(img, preset_code, cloud_name=cloudname)
    url = rps['secure_url']
    return url
