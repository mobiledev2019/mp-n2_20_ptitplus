import time, os

def heroku_generate_image(student_id):
    url = 'http://qldt.ptit.edu.vn/Default.aspx?page=thoikhoabieu&id='
    options = {
        'quality':100,
        'width':2048,
        'crop-h':580,
        'crop-w':1200,
        'crop-x':410,
        'crop-y':250,
        'user-style-sheet':'inject.css'
    }
    cmd = './bin/wkhtmltoimage '
    for key in options:
        if 'list' in str(type(options[key])):
            l = options[key]
            cmd += '--' + key + ' '
            for pair in l:
                cmd += pair[0] + ' ' + pair[1] + ' '
        else:
            cmd += '--' + str(key) + ' ' + str(options[key]) + ' '
    cmd += "'" + url + student_id + "' "
    timestamp = int(time.time()*10000000)
    timestamp = ''
    outfile =  student_id + '_' + str(timestamp) + '.jpg\''
    cmd += '\'' + outfile
    print("cmd -> [{}]".format(cmd))
    os.system(cmd)
    # os.system('rm {}'.format(outfile))
    # img_url = img_uploader.up(outfile[:-1])
    os.system('ls')
    return None

heroku_generate_image('B15DCCN549')