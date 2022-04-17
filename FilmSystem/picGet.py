from urllib import request
import linkDatabase

def getPic():
    #得到图片url
    picList=linkDatabase.getPicUrl()
    #爬虫准备
    opener = request.build_opener()
    headers = ('User-Agent', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36')
    opener.addheaders = [headers]
    request.install_opener(opener)

    #爬取图片
    n=1
    for i in picList:
        request.urlretrieve(i, './pic/'+str(n)+'.jpg')
