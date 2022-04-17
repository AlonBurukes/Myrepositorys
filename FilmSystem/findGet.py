import requests
import douban_spider
import time
import spider_single
import linkDatabase
import re

def FindGet(type):
    url="https://movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start=20&genres="+type
    urldatalist = []
    content = douban_spider.askUrl(url)
    findurl = re.compile('"url":"(?P<url>.*?)",', re.S)
    result = re.findall(findurl, content)
    for index in result:
        temp = index
        temp = temp.replace("\\", "")
        urldatalist.append(temp)
        if len(result) == 0:
            break
    datalist = []
    print(urldatalist)
    count = 0
    #删除原有的库
    linkDatabase.delete()

    for i in urldatalist:
        data = []  # 保存一部电影的信息
        count = count + 1
        content = douban_spider.askUrl(i)
        #print(i)
        data = spider_single.getsingleinfo(count, content)
        #print(data)
        datalist.append(data)
        if len(datalist) % 10 == 0:
            linkDatabase.addData2(datalist)
            datalist = []
