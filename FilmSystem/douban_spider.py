import requests
import re

import douban_spider
import spider_single
import time
import linkDatabase
import picGet

def main():
    baseurl="https://movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=%E7%94%B5%E5%BD%B1&start="
    urldatalist=getDate(baseurl)
    getInfo(urldatalist)
    picGet.getPic()
    #linkDatabase.createDatabase()


def getDate(baseurl):
    urldatalist=[]
    count=0
    for i in range (0,100,20):
        url=baseurl+str(i)
        content=douban_spider.askUrl(url)
        time.sleep(2)
        findurl=re.compile('"url":"(?P<url>.*?)",',re.S)
        result=re.findall(findurl,content)

        for index in result:
            count=count+1
            temp=index
            temp=temp.replace("\\", "")
            urldatalist.append(temp)
            print(count,temp)
        if len(result)==0:
            break
    return urldatalist

#访问url，得到网页信息
def askUrl(url):
    headers = {
        "user-agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36"
    }
    proxie={
        'http':  'http//114.238.109.182:25127',
        'http':  'http//49.88.218.189:58427',
        'http': 'http//42.63.200.67:31409',
        'http': 'http//119.115.13.59:51923',
        'http': 'http//106.59.9.75:48026',
        'http': 'http//113.229.62.106:56916',
        'http': 'http//115.203.81.250:45338',
        'http': 'http//223.243.61.75:60622',
        'http': 'http//119.132.32.159:23833',
        'http': 'http//183.166.139.102:54747'
    }
    resp=requests.get(url,headers=headers)
    content=resp.text
    return content

#访问每个网页得到详细信息
def getInfo(urldatalist):
    datalist=[]
    banip=[]
    banip.append('https://movie.douban.com/subject/35478908/')
    banip.append('https://movie.douban.com/subject/35430794/')

    count=0
    for i in urldatalist:
        if i in banip:
            continue
        data=[]#保存一部电影的信息
        count=count+1
        content=askUrl(i)
        time.sleep(2)
        print(i)
        data=spider_single.getsingleinfo(count,content)
        print(data)
        datalist.append(data)
        if len(datalist)%10==0:
            linkDatabase.addData(datalist)
            datalist=[]





if __name__== '__main__':
    main()