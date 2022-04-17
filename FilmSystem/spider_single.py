import re
import requests
def getsingleinfo(count,content):


    #存储一个页面的数据
    data = []
    # 寻找标题
    findTitle = re.compile(r'<title>(?P<title>.*?)</title>', re.S)
    title = re.findall(findTitle, content)
    if len(title)==0:
        title="无"
    else:
        title=title[0]
        title = title.replace("(豆瓣)", "")
        title = title.strip()
    # print(title)

    # 寻找图片
    findImg = re.compile(r'"image": "(?P<image>.*?)",', re.S)
    image = re.findall(findImg, content)
    if len(image)==0:
        image="无"
    else:
        image=image[0]
    # print(image)

    # 寻找导演
    findDirector = re.compile(r'"director":.*?"name": "(?P<director>.*?)"', re.S)
    director = re.findall(findDirector, content)
    if len(director)==0:
        director="无"
    else:
        director=director[0]
    # print(director)

    # 寻找编辑
    findAuthor = re.compile(r'"author":.*?"name": "(?P<author>.*?)"', re.S)
    author = re.findall(findAuthor, content)
    if len(author)==0:
        author="无"
    else:
        author=author[0]
    # print(author)

    # 寻找主演
    findActors = re.compile(
        r'<div id="info">.*?<span class="actor"><span class=\'pl\'>主演(?P<actors>.*?)</span></span><br/>', re.S)
    actors = re.findall(findActors, content)
    if len(actors)==0:
        actors="无"
    else:
        actors=actors[0]
        actors = re.findall('[\u4e00-\u9fa5]+', actors)
        n=len(actors)
        if n>=3:
            actors = actors[0] + ',' + actors[1] + ',' + actors[2]
        elif n==2:
            actors = actors[0] + ',' + actors[1]
        elif n==1:
            actors = actors[0]
        else:
            actors=" József Madaras"

    # print(actors)

    # 寻找简介
    findDesc = re.compile(r'"description": "(?P<desc>.*?)"', re.S)
    desc = re.findall(findDesc, content)
    if len(desc)==0:
        desc="无"
    else:
        desc=desc[0]
    # print(desc)

    # 寻找日期
    findDate = re.compile(r'"datePublished": "(?P<date>.*?)"', re.S)
    date = re.findall(findDate, content)
    if len(date)==0:
        date="无"
    else:
        date=date[0]
    # print(date)

    # 寻找类型
    findGener = re.compile(
        r'<div id="info">.*?<span class="pl">类型:</span> <span property="v:genre">(?P<gener>.*?)</span><br/>', re.S)
    gener = re.findall(findGener, content)
    if len(gener)==0:
        gener="无"
    else:
        gener=gener[0]
        gener = (re.findall('[\u4e00-\u9fa5]+', gener))
        tempgener = ""
        for i in gener:
            tempgener = tempgener + i + ","
        gener=tempgener
    # print(tempgener)

    # 寻找制片国家和地区
    findNation = re.compile(r'<span class="pl">制片国家/地区:(?P<nation>.*?)<br/>', re.S)
    nation = re.findall(findNation, content)
    if len(nation)==0:
        nation="无"
    else:
        nation = nation[0]
        nation = (re.findall('[\u4e00-\u9fa5]+', nation))
        tempnation = ""
        for i in nation:
            tempnation = tempnation + i + ","
        nation=tempnation
    # print(tempnation)

    # 寻找语言
    findLanguage = re.compile(r'<span class="pl">语言:(?P<language>.*?)<br/>', re.S)
    language = re.findall(findLanguage, content)
    if len(language)==0:
        language="无"
    else:
        language = language[0]
        language = (re.findall('[\u4e00-\u9fa5]+', language))
        templanguage = ""
        for i in language:
            templanguage = templanguage + i + ","
        language=templanguage
    # print(templanguage)

    # 寻找时长
    findRuntime = re.compile(r'<span class="pl">片长:</span>(?P<runtime>.*?)<br/>', re.S)
    runtime = re.findall(findRuntime, content)
    if len(runtime)==0:
        runtime="无"
    else:
        runtime = runtime[0]
        runtime = re.findall(r'\d+', runtime)
        if len(runtime)==0:
            runtime="无"
        else:
            runtime=runtime[0]
    # print(runtime)

    # 寻找IMDb
    findIMDb = re.compile(r'<span class="pl">IMDb:</span> (?P<IMDb>.*?)<br>', re.S)
    IMDb = re.findall(findIMDb, content)
    if len(IMDb)==0:
        IMDb="无"
    else:
        IMDb = re.findall(findIMDb, content)[0]
    # print(IMDb)

    # 寻找又名
    findAnothername = re.compile(r'<span class="pl">又名:</span> (?P<anothername>.*?)<br/>', re.S)
    anothername = re.findall(findAnothername, content)
    if len(anothername)==0:
        anothername="无"
    else:
        anothername=anothername[0]
        anothername = anothername.replace("/", ",")
    # print(anothername)

    # 寻找评价数
    findRatingCount = re.compile(r'"ratingCount": "(?P<ratingcount>.*?)"', re.S)
    ratingcount = (re.findall(findRatingCount, content))
    if len(ratingcount)==0:
        ratingcount="无"
    else:
        ratingcount=ratingcount[0]
    # print(ratingcount)

    # 寻找评分
    findRatingvalue = re.compile(r'"ratingValue": "(?P<ratingvalue>.*?)"', re.S)
    ratingvalue = (re.findall(findRatingvalue, content))
    if len(ratingvalue)==0:
        ratingvalue="无"
    else:
        ratingvalue=ratingvalue[0]
    # print(ratingvalue)

    # 寻找短评数
    findShortcommentnum = re.compile(r'<span>我要写短评</span>.*?全部 (?P<shortcommentnum>.*?)</a>', re.S)
    shortcommentnum = re.findall(findShortcommentnum, content)
    if len(shortcommentnum)==0:
        shortcommentnum="无"
    else:
        shortcommentnum=shortcommentnum[0]
        # print(shortcommentnum)

    # 寻找短评者的昵称
    findShortcomname = re.compile(r'<div class="tab-bd">.*?<span class="comment-info">.*?class="">(?P<comname>.*?)</a>',
                                  re.S)
    comname = re.findall(findShortcomname, content)
    if len(comname)==0:
        comname="无"
    else:
        comname=comname[0]
    # print(comname)

    # 寻找短评者的有用数
    findCommentvaild = re.compile(
        r'<div class="tab-bd">.*?<span class="votes vote-count">(?P<vaildcommentnum>.*?)</span>', re.S)
    vaildcommemtnum = re.findall(findCommentvaild, content)
    if len(vaildcommemtnum)==0:
        vaildcommemtnum="无"
    else:
        vaildcommemtnum=vaildcommemtnum[0]
    # print(vaildcommemtnum)

    # 寻找短评的时间
    findCommentDate = re.compile(r'<div class="tab-bd">.*?<span class="comment-time " title="(?P<commentdate>.*?)">',
                                 re.S)
    commentdate = re.findall(findCommentDate, content)
    if len(commentdate)==0:
        commentdate="无"
    else:
        commentdate=commentdate[0]
    # print(commentdate)

    # 寻找短评的内容
    findComment = re.compile(r'<div class="tab-bd">.*?<span class="short">(?P<comment>.*?)</span>', re.S)
    comment = re.findall(findComment, content)
    if len(comment)==0:
        comment="无"
    else:
        comment=comment[0]
    # print(comment)

    # 寻找影评数
    findReviewnum = re.compile(r'<span class="pl">\( <a href="reviews">全部 (?P<reviewnum>.*?) 条', re.S)
    reviewnum = re.findall(findReviewnum, content)
    if len(reviewnum)==0:
        reviewnum="无"
    else:
        reviewnum=reviewnum[0]
    # print(reviewnum)

    # 寻找影评昵称
    findReviewname = re.compile(r'<div class="review-list  ">.*?<a href=".*?class="name">(?P<reviewname>.*?)</a>', re.S)
    reviewname = re.findall(findReviewname, content)
    if len(reviewname)==0:
        reviewname="无"
    else:
        reviewname=reviewname[0]
    # print(reviewname)

    # 寻找影评时间
    findReviewtime = re.compile(
        r'<div class="review-list  ">.*?<span content=".*?" class="main-meta">(?P<reviewtime>.*?)</span>', re.S)
    reviewtime = re.findall(findReviewtime, content)
    if len(reviewtime)==0:
        reviewtime="无"
    else:
        reviewtime=reviewtime[0]
    # print(reviewtime)

    # 寻找影评内容
    findReviewcontent = re.compile(
        r'<div class="review-list  ">.*?<div class="short-content">(?P<reviewcontent>.*?)&nbsp;\(<a href="javascript:;"',
        re.S)
    reviewcontent = re.findall(findReviewcontent, content)
    if len(reviewcontent)==0:
        reviewcontent="无"
    else:
        reviewcontent=reviewcontent[0]
        reviewcontent = reviewcontent.replace('\r', '').replace('\n', '').replace(' ','')
        reviewcontent=reviewcontent.replace('<pclass="spoiler-tip">这篇影评可能有剧透</p>','')
    # print(reviewcontent)

    # 寻找认为有用的
    findReviewup = re.compile(
        r'<div class="review-list  ">.*?<div class="action">.*?<span id="r-useful_.*?">(?P<reviewup>.*?)</span>', re.S)
    reviewup = re.findall(findReviewup, content)
    if len(reviewup)==0:
        reviewup="无"
    else:
        reviewup = reviewup[0]
        reviewup = re.findall('\d+', reviewup)
        if len(reviewup)==0:
            reviewup="无"
        else:
            reviewup = reviewup[0]
    # print(reviewup)

    # 寻找认为没用的
    findReviewdown = re.compile(
        r'<div class="review-list  ">.*?<div class="action">.*?<span id="r-useless_count.*?">(?P<reviewdown>.*?)</span>',
        re.S)
    reviewdown = re.findall(findReviewdown, content)
    if len(reviewdown) == 0:
        reviewdown = "无"
    else:
        reviewdown = reviewdown[0]
        reviewdown = re.findall('\d+', reviewdown)
        if len(reviewdown) == 0:
            reviewdown = "无"
        else:
            reviewdown = reviewdown[0]
    # print(reviewdown)

    # 寻找回应数
    findReviewreply = re.compile(r'<div class="review-list  ">.*?<a href=".*?" class="reply ">(?P<reviewreply>.*?)</a>',
                                 re.S)
    reviewreply = re.findall(findReviewreply, content)
    if len(reviewreply)==0:
        reviewreply="无"
    else:
        reviewreply=reviewreply[0]
    # print(reviewreply)


    data.append(count)
    # (1)电影名称、电影海报图片、剧情简介、导演姓名、编剧姓名、主演、类型、制片国家、语言、上映时间、片长、IMDB链接、又名等信息
    data.append(title)
    data.append(image)
    data.append(desc)
    data.append(director)
    data.append(author)
    data.append(actors)
    data.append(gener)
    data.append(nation)
    data.append(language)
    data.append(date)
    data.append(runtime)
    data.append(IMDb)
    data.append(anothername)

    # (2)豆瓣评分、评价人数;
    data.append(ratingvalue)
    data.append(ratingcount)

    # (3)短评数、短评者昵称、短评时间、短评内容、认为该短评有用人数等;
    data.append(shortcommentnum)
    data.append(comname)
    data.append(commentdate)
    data.append(comment)
    data.append(vaildcommemtnum)

    # (4)影评数、影评者昵称、影评时间、影评内容、认为有用人数、认为无用人数、转发人数、回应人数等
    data.append(reviewnum)
    data.append(reviewname)
    data.append(reviewtime)
    data.append(reviewcontent)
    data.append(reviewup)
    data.append(reviewdown)
    data.append(reviewreply)
    return data
