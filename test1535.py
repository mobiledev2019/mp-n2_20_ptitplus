import time
def heroku_generate_image(student_id):
    url = 'http://qldt.ptit.edu.vn?id='
    options = {
        'quality':100,
        'width':2048,
        'crop-h':580,
        'crop-w':1200,
        'crop-x':420,
        'crop-y':250,
        'cookie':[['ASP.NET_SessionId', 'ASP.NET_SessionId_value']]
    }
    cmd = './bin/wkhtmltoimage '
    for key in options:
        # print(type(options[key]))
        # continue
        if 'list' in str(type(options[key])):
            l = options[key]
            cmd += '--' + key + ' '
            for pair in l:
                cmd += pair[0] + ' ' + pair[1] + ' '
        else:
            cmd += '--' + str(key) + ' ' + str(options[key]) + ' '
    cmd += url + student_id + ' '
    timestamp = int(time.time()*10000000)
    cmd += student_id + '_' + str(timestamp) + '.jpg'
    return cmd


if __name__ == '__main__':
    print(heroku_generate_image('B15DCCN334'))