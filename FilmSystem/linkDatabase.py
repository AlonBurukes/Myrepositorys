import re
import sqlite3


# (1)电影名称、电影海报图片、剧情简介、导演姓名、编剧姓名、主演、类型、制片国家、语言、上映时间、片长、IMDB链接、又名等信息
# (2)豆瓣评分、评价人数;
# (3)短评数、短评者昵称、短评时间、短评内容、认为该短评有用人数等;
# (4)影评数、影评者昵称、影评时间、影评内容、认为有用人数、认为无用人数、转发人数、回应人数等
def createDatabase():
    conn = sqlite3.connect('./test.db')
    c=conn.cursor()
    sql="""
    CREATE TABlE MOVIE2(
    movieID  TEXT PRIMARY KEY ,
    movieName  TEXT ,
    MOVIEPIC   TEXT ,
    brief   TEXT ,
    DIRECTOR   TEXT ,
    AUTHOR     TEXT ,
    actor     TEXT ,
    type   TEXT ,
    MOVIENAT   TEXT ,
    MOVIELANGUAGE   TEXT ,
    OnTime  TEXT ,
    time  TEXT ,
    IMDB       TEXT ,
    ANOTHER    TEXT ,
    
    grade  TEXT,
    box    TEXT,
    
    COMMENTNUM   TEXT,
    COMMENTNAME  TEXT,
    COMMENTDATE  TEXT,
    COMMENT      TEXT,
    VAILDCOMMENTNUM  TEXT,
    
    REVIEWNUM   TEXT,
    REVIEWNAME  TEXT,
    REVIEWDATE  TEXT,
    REVIEW      TEXT,
    REVIEWUP    TEXT,
    REVIEWDOWN  TEXT,
    REVIEWREPLY TEXT);
    """
    c.execute(sql)
    conn.commit()
    conn.close()

def addData(datalist):
    conn = sqlite3.connect('./test.db')
    c = conn.cursor()
    for i in datalist:
        for index in range(len(i)):
            i[index] = str(i[index])
        c.execute("""INSERT INTO MOVIE (movieID,movieName,MOVIEPIC,brief,DIRECTOR,AUTHOR,actor,type,MOVIENAT,MOVIELANGUAGE,OnTime,time,IMDB,
        ANOTHER,grade,box,COMMENTNUM,COMMENTNAME,COMMENTDATE,COMMENT,VAILDCOMMENTNUM,REVIEWNUM,REVIEWNAME,REVIEWDATE,
        REVIEW,REVIEWUP,REVIEWDOWN,REVIEWREPLY)
        VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
        """, (
        i[0], i[1], i[2], i[3], i[4], i[5], i[6], i[7], i[8], i[9], i[10], i[11], i[12], i[13], i[14], i[15], i[16],
        i[17], i[18], i[19], i[20], i[21], i[22], i[23], i[24], i[25], i[26], i[27]))
        conn.commit()
    conn.close()

def getPicUrl():
    conn = sqlite3.connect('./test.db')
    c = conn.cursor()
    cursor=c.execute("SELECT MOVIEPIC FROM MOVIE")
    picList=[]
    for i in cursor:
        temp=str(i)
        n=len(temp)
        temp=temp[2:n-3]
        picList.append(temp)
    conn.close()
    return picList

def addData2(datalist):
    conn = sqlite3.connect('./test.db')
    c = conn.cursor()
    for i in datalist:
        for index in range(len(i)):
            i[index] = str(i[index])
        c.execute("""INSERT INTO MOVIE2 (movieID,movieName,MOVIEPIC,brief,DIRECTOR,AUTHOR,actor,type,MOVIENAT,MOVIELANGUAGE,OnTime,time,IMDB,
        ANOTHER,grade,box,COMMENTNUM,COMMENTNAME,COMMENTDATE,COMMENT,VAILDCOMMENTNUM,REVIEWNUM,REVIEWNAME,REVIEWDATE,
        REVIEW,REVIEWUP,REVIEWDOWN,REVIEWREPLY)
        VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
            """, (
            i[0], i[1], i[2], i[3], i[4], i[5], i[6], i[7], i[8], i[9], i[10], i[11], i[12], i[13], i[14], i[15], i[16],
            i[17], i[18], i[19], i[20], i[21], i[22], i[23], i[24], i[25], i[26], i[27]))
        conn.commit()
    conn.close()

def delete():
    conn = sqlite3.connect('./test.db')
    c = conn.cursor()
    c.execute("DELETE FROM MOVIE2")
    conn.commit()
    conn.close()
