import requests, time, json
import cloudinary.uploader as uploader

def up(img):
    cloudname = 'bachvkhoa'
    preset_code = 'anhoiemup'
    rps = uploader.unsigned_upload(img, preset_code, cloud_name=cloudname)
    url = rps['secure_url']
    return url
