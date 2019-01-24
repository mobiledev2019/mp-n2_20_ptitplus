import requests, time, json, os
import cloudinary.uploader as uploader

def up(img):
    cloudname = 'bachvkhoa'
    preset_code = 'anhoiemup'
    print("IMG_UPLOADER listdir -> ")
    crr_dir = os.listdir('./')
    print(crr_dir)
    for f in crr_dir:
        if f[:-4] == '.jpg':
            print('img found -> {}'.format(f))
        else:
            print('{} is not a jpg file'.format(f))
    # rps = uploader.unsigned_upload(img, preset_code, cloud_name=cloudname)
    url = rps['secure_url']
    return url
