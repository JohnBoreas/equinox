'''
抓取碧蓝航线过场图
使用前安装python，导入requests包
'''
import requests
import re

'''
抓取所有中间页
'''
def spider_middle_list_url():
    response_url = spider_data('https://wiki.biligame.com/blhx/%E5%BD%B1%E7%94%BB%E7%9B%B8%E5%85%B3')
    content = response_url.text
    pattern_res = re.compile(r'class="thumb"([\s\S]*?)</div>')
    result_lists = pattern_res.findall(content)
    return result_lists

'''
抓取图片链接
'''
def spider_image_url(link):
    response_url = spider_data(link)
    content = response_url.text
    pattern_str = re.compile(r'class="fullImageLink"([\s\S]*?)</div>')
    result = pattern_str.findall(content)
    if len(result) > 0:
        pattern_image = re.compile(r'href="([\s\S]*?)"')
        image = pattern_image.findall(result[0])
        if len(image) > 0:
            return image[0]
    else:
        return None


def spider_data(link):
    return requests.get(link)


pic_route = 'E:/漫画图片壁纸/碧蓝航线/过场图/'
'''获取所有的中间页图片地址'''
result_list = spider_middle_list_url()
'''下载图片'''
for i in range(len(result_list)):
    pattern = re.compile(r'href="([\s\S]*?)"')
    url = pattern.findall(result_list[i])
    if len(url) > 0:
        url = 'https://wiki.biligame.com/' + url[0]
        pic_link = spider_image_url(url)
        # 获得图片二进制，速度比方法一快了4倍
        image_response = requests.get(pic_link).content
        with open(pic_route + 'blhx_' + str(i) + '.png', 'wb') as f:
            f.write(image_response)
            f.close()
        print('download pic ' + url)

print('download end')

