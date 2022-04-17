from flask import Flask, render_template, session, redirect, url_for, flash, request, jsonify
import os
from flask_sqlalchemy import SQLAlchemy
from flask_script import Manager, Shell
from forms import *
from flask_login import UserMixin, LoginManager, login_required, login_user, logout_user, current_user
import time, datetime
import json
import urllib
import findGet
basedir = os.path.abspath(os.path.dirname(__file__))
# 运行flask记得调配Configuration， prarameters记得加runserver（Pycharm）
app = Flask(__name__)
manager = Manager(app)
app.config['SECRET_KEY'] = 'hard to guess string'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///' + os.path.join(basedir, 'test.db')
app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = True
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True
app.config['SQLALCHEMY_COMMIT_TEARDOWN'] = True
db = SQLAlchemy(app)


def make_shell_context():
    return dict(app=app, db=db, Admin=Admin, )


manager.add_command("shell", Shell(make_context=make_shell_context))

login_manager = LoginManager()
login_manager.init_app(app)
login_manager.session_protection = 'basic'
login_manager.login_view = 'login'
login_manager.login_message = u"请先登录。"


class Admin(UserMixin, db.Model):
    __tablename__ = 'admin'
    admin_id = db.Column(db.String(6), primary_key=True)
    admin_name = db.Column(db.String(32))
    password = db.Column(db.String(24))

    def __init__(self, admin_id, admin_name, password):
        self.admin_id = admin_id
        self.admin_name = admin_name
        self.password = password

    def get_id(self):
        return self.admin_id

    def verify_password(self, password):
        if password == self.password:
            return True
        else:
            return False

    def __repr__(self):
        return '<Admin %r>' % self.admin_name


@login_manager.user_loader
def load_user(admin_id):
    return Admin.query.get(int(admin_id))


@app.route('/', methods=['GET', 'POST'])
def login():
    form = Login()
    if form.validate_on_submit():
        user = Admin.query.filter_by(admin_id=form.account.data, password=form.password.data).first()
        if user is None:
            # flash('账号或密码错误！')
            return redirect(url_for('index'))
        else:
            login_user(user)
            session['admin_id'] = user.admin_id
            session['name'] = user.admin_name
            return redirect(url_for('index'))
    return render_template('login.html', form=form)


@app.route('/logout')
@login_required
def logout():
    logout_user()
    flash('您已经登出！')
    return redirect(url_for('login'))


@app.route('/index')
def index():
    return render_template('index.html', name=session.get('name'))


@app.route('/user/<id>')
@login_required
def user_info(id):
    user = Admin.query.filter_by(admin_id=id).first()
    return render_template('user-info.html', user=user, name=session.get('name'))


@app.route('/change_password', methods=['GET', 'POST'])
@login_required
def change_password():
    form = ChangePasswordForm()
    if form.password2.data != form.password.data:
        flash(u'两次密码不一致！')
    if form.validate_on_submit():
        if current_user.verify_password(form.old_password.data):
            current_user.password = form.password.data
            db.session.add(current_user)
            db.session.commit()
            flash(u'已成功修改密码！')
            return redirect(url_for('index'))
        else:
            flash(u'原密码输入错误，修改失败！')
    return render_template("change-password.html", form=form)


@app.route('/change_info', methods=['GET', 'POST'])
@login_required
def change_info():
    form = EditInfoForm()
    if form.validate_on_submit():
        current_user.admin_name = form.name.data
        db.session.add(current_user)
        flash(u'已成功修改个人信息！')
        return redirect(url_for('user_info', id=current_user.admin_id))
    form.name.data = current_user.admin_name
    id = current_user.admin_id

    return render_template('change-info.html', form=form, id=id)


class Cinema(db.Model):
    __tablename__ = 'cinema'
    cinemaID = db.Column(db.String(), primary_key=True)
    cinemaName = db.Column(db.String(20))
    cinemaAddr = db.Column(db.String(50))

    def __repr__(self):
        return '<Cinema>' % self.cinemaName


class Comment(db.Model):
    __tablename__ = 'comment'
    commentID = db.Column(db.String(), primary_key=True)
    content = db.Column(db.String(100))
    movieID = db.Column(db.String())

    def __repr__(self):
        return '<Comment>' % self.commentID


class Hall(db.Model):
    __tablename__ = 'hall'
    hallID = db.Column(db.String(), primary_key=True)
    hallName = db.Column(db.String(10))
    capacity = db.Column(db.String())
    cinemaID = db.Column(db.String())

    def __repr__(self):
        return '<Hall>' % self.hallName


class Movie(db.Model):
    __tablename__ = 'MOVIE'
    movieID = db.Column(db.String(), primary_key=True)
    actor = db.Column(db.String(100))
    brief = db.Column(db.String(255))
    time = db.Column(db.String(4))
    type = db.Column(db.String(20))
    grade = db.Column(db.String())
    OnTime = db.Column(db.String(10))
    box = db.Column(db.String(10))
    movieName = db.Column(db.String(50))
    MOVIEPIC = db.Column(db.String())
    DIRECTOR = db.Column(db.String(20))
    AUTHOR = db.Column(db.String(20))
    MOVIENAT = db.Column(db.String(10))
    MOVIELANGUAGE = db.Column(db.String(10))
    IMDB = db.Column(db.String(20))
    ANOTHER = db.Column(db.String(20))
    COMMENTNUM = db.Column(db.String(20))
    COMMENTNAME = db.Column(db.String(20))
    COMMENTDATE = db.Column(db.String(20))
    COMMENT = db.Column(db.String(20))
    VAILDCOMMENTNUM = db.Column(db.String(10))
    REVIEWNUM = db.Column(db.String(20))
    REVIEWNAME = db.Column(db.String(20))
    REVIEWDATE = db.Column(db.String(20))
    REVIEW = db.Column(db.String(20))
    REVIEWUP = db.Column(db.String(20))
    REVIEWDOWN = db.Column(db.String(20))
    REVIEWREPLY = db.Column(db.String(20))

    def __repr__(self):
        return '<Movie>' % self.movieName

class Movie2(db.Model):
    __tablename__ = 'MOVIE2'
    movieID = db.Column(db.String(), primary_key=True)
    actor = db.Column(db.String(100))
    brief = db.Column(db.String(255))
    time = db.Column(db.String(4))
    type = db.Column(db.String(20))
    grade = db.Column(db.String())
    OnTime = db.Column(db.String(10))
    box = db.Column(db.String(10))
    movieName = db.Column(db.String(50))
    MOVIEPIC = db.Column(db.String())
    DIRECTOR = db.Column(db.String(20))
    AUTHOR = db.Column(db.String(20))
    MOVIENAT = db.Column(db.String(10))
    MOVIELANGUAGE = db.Column(db.String(10))
    IMDB = db.Column(db.String(20))
    ANOTHER = db.Column(db.String(20))
    COMMENTNUM = db.Column(db.String(20))
    COMMENTNAME = db.Column(db.String(20))
    COMMENTDATE = db.Column(db.String(20))
    COMMENT = db.Column(db.String(20))
    VAILDCOMMENTNUM = db.Column(db.String(10))
    REVIEWNUM = db.Column(db.String(20))
    REVIEWNAME = db.Column(db.String(20))
    REVIEWDATE = db.Column(db.String(20))
    REVIEW = db.Column(db.String(20))
    REVIEWUP = db.Column(db.String(20))
    REVIEWDOWN = db.Column(db.String(20))
    REVIEWREPLY = db.Column(db.String(20))

    def __repr__(self):
        return '<Movie>' % self.movieName

class Session(db.Model):
    __tablename__ = 'session'
    sessionID = db.Column(db.String(5), primary_key=True)
    hallID = db.Column(db.String())
    movieID = db.Column(db.String())
    price = db.Column(db.String())
    Date = db.Column(db.String(10))

    def __repr__(self):
        return '<Session>' % self.sessionID


@app.route('/session', methods=['POST'])
def find_session():
    records = db.session.query(Session.sessionID, Movie.movieName, Cinema.cinemaName, Cinema.cinemaAddr, Hall.hallID,
                               Hall.hallName, Session.Date, Movie.time, Session.price). \
        filter(Movie.movieName == request.form.get('movieName')).filter(Cinema.cinemaID == Hall.cinemaID).filter(
        Session.hallID == Hall.hallID).filter(Session.movieID == Movie.movieID). \
        with_entities(Session.sessionID, Movie.movieName, Cinema.cinemaName, Cinema.cinemaAddr, Hall.hallID,
                      Hall.hallName, Session.Date, Movie.time, Session.price).all()  # with_entities啊啊啊啊卡了好久啊
    data = []
    for record in records:
        item = {'sessionID': record.sessionID, 'movieName': record.movieName, 'cinemaName': record.cinemaName,
                'cinemaAddr': record.cinemaAddr, 'hallID': record.hallID, 'hallName': record.hallName,
                'date': record.Date, 'time': record.time, 'price': record.price}
        data.append(item)
    print(data)
    return jsonify(data)


def find_parse(arg, url):
    result = urllib.parse.urlparse(url)
    query_dict = urllib.parse.parse_qs(result.query)
    return query_dict.get(arg)


@app.route('/detail', methods=['GET', 'POST'])
def find_detail():
    url = request.url
    movieID = find_parse('movieID', url)[0]
    movie = Movie.query.filter(Movie.movieID == movieID)
    detail = {'movieID': movie.movieID, 'actor': movie.actor, 'brief': movie.brief, 'time': movie.time,
              'type': movie.type, 'grade': movie.grade, 'OnTime': movie.OnTime, 'box': movie.box,
              'movieName': movie.movieName,
              'MOVIEPIC': movie.MOVIEPIC, 'DIRECTOR': movie.DIRECTOR, 'AUTHOR': movie.AUTHOR,
              'MOVIENAT': movie.MOVIENAT, 'MOVIELANGUAGE': movie.MOVIELANGUAGE, 'IMDB': movie.IMDB,
              'ANOTHER': movie.ANOTHER, 'COMMENTNUM': movie.COMMENTNUM, 'COMMENTNAME': movie.COMMENTNAME,
              'COMMENTDATE': movie.COMMENTDATE, 'COMMENT': movie.COMMENT, 'VAILDCOMMENTNUM': movie.VAILDCOMMENTNUM,
              'REVIEWNUM': movie.REVIEWNUM, 'REVIEWNAME': movie.REVIEWNAME, 'REVIEWDATE': movie.REVIEWDATE,
              'REVIEW': movie.REVIEW, 'REVIEWUP': movie.REVIEWUP, 'REVIEWDOWN': movie.REVIEWUP,
              'REVIEWREPLY': movie.REVIEWREPLY}
    print(detail)
    return render_template('comment-search.html', detail=detail)
@app.route('/startspider',methods = ['GET','POST'])
def startspider():
    form = MovieSearchForm()
    method = request.form.get('method')
    if(method == 'movie'):
        content = request.form.get('content')
        findGet.FindGet(content)
        return render_template('movie-search.html', form=form)
    else:
        return render_template('session-insert.html',form = form)
@app.route('/comment', methods=['GET', 'POST'])
def find_comment():
    url = request.url
    movieID = find_parse('movieID', url)[0]
    # comment = db.session.query(Comment.movieID,Comment.content,Movie.movieName).filter(Comment.movieID == movieID).filter(Movie.movieID==Comment.movieID)\
    # .with_entities(Comment.content,Comment.movieID,Movie.movieName).all()
    # data = []
    # counter = 1
    # for i in comment:
    # item={'commentID':counter,'movieName':i.movieName,'comment':i.content}
    #  counter+=1
    #   data.append(item)
    # print(data)

    movie = Movie.query.filter(Movie.movieID == movieID).first()
    detail = {'movieID': movie.movieID, 'actor': movie.actor, 'brief': movie.brief, 'time': movie.time,
              'type': movie.type, 'grade': movie.grade, 'OnTime': movie.OnTime, 'box': movie.box,
              'movieName': movie.movieName,
              'MOVIEPIC': movie.MOVIEPIC, 'DIRECTOR': movie.DIRECTOR, 'AUTHOR': movie.AUTHOR,
              'MOVIENAT': movie.MOVIENAT, 'MOVIELANGUAGE': movie.MOVIELANGUAGE, 'IMDB': movie.IMDB,
              'ANOTHER': movie.ANOTHER, 'COMMENTNUM': movie.COMMENTNUM, 'COMMENTNAME': movie.COMMENTNAME,
              'COMMENTDATE': movie.COMMENTDATE, 'COMMENT': movie.COMMENT, 'VAILDCOMMENTNUM': movie.VAILDCOMMENTNUM,
              'REVIEWNUM': movie.REVIEWNUM, 'REVIEWNAME': movie.REVIEWNAME, 'REVIEWDATE': movie.REVIEWDATE,
              'REVIEW': movie.REVIEW, 'REVIEWUP': movie.REVIEWUP, 'REVIEWDOWN': movie.REVIEWUP,
              'REVIEWREPLY': movie.REVIEWREPLY}
    print(detail)
    return render_template('comment-search.html', comments=detail)


@app.route('/movies', methods=['GET', 'POST'])
def find_movie():
    def find_name():
        return Movie.query.filter(Movie.movieName.like('%' + request.form.get('content') + '%')).all()

    def find_actor():
        return Movie.query.filter(Movie.actor.like('%' + request.form.get('content') + '%')).all()

    def find_type():
        return Movie.query.filter(Movie.type.contains(request.form.get('content'))).all()

    def find_ID():
        return Movie.query.filter(Movie.movieID.contains(request.form.get('content'))).all()

    def find_all():
        return Movie.query.filter().all()

    methods = {
        'movieName': find_name,
        'movie_actor': find_actor,
        'movie': find_type,
        'movieID': find_ID,
        'all': find_all
    }

    Movies = methods[request.form.get('method')]()
    data = []
    for movie in Movies:
        item = {'movieID': movie.movieID, 'movieName': movie.movieName, 'actor': movie.actor, 'brief': movie.brief,
                'type': movie.type, 'grade': movie.grade, 'time': movie.time, 'OnTime': movie.OnTime, 'box': movie.box}
        data.append(item)
        print(item)
    return jsonify(data)


@app.route('/movie/insert', methods=['GET', 'POSt'])
def insert_movie():
    form = MovieForm()
    movieID = request.form.get('movieID')
    movie = Movie.query.filter_by(movieID=request.form.get('movieID')).first()
    if movieID is None:
        return render_template('movie-insert.html', form=form)
    else:
        if movie is not None:
            flash(u'该电影已存在！')
        else:
            item = Movie()
            item.movieID = request.form.get('movieID')
            item.actor = request.form.get('actor')
            item.box = request.form.get('box')
            item.brief = request.form.get('brief')
            item.grade = request.form.get('grade')
            item.movieName = request.form.get('movieName')
            item.OnTime = request.form.get('OnTime')
            item.type = request.form.get('type')
            db.session.add(item)
            db.session.commit()
            flash(u'录入成功')
        return redirect(url_for('insert_movie'))
    return render_template('movie-insert.html', form=form)


@app.route('/session/insert', methods=['GET', 'POSt'])
def insert_session():
    form = SessionForm()
    sessionID = request.form.get('sessionID')
    if sessionID is None:
        return render_template('session-insert.html', form=form)
    else:
        session = Session.query.filter_by(sessionID=sessionID).first()
        if session is not None:
            flash(u'该场次已存在！')
        else:
            item = Session()
            item.sessionID = request.form.get('sessionID')
            item.Date = request.form.get('Date')
            item.movieID = request.form.get('movieID')
            item.hallID = request.form.get('hallID')
            item.price = request.form.get('price')
            # print(item.movieID,item.Date,item.price,item.sessionID,item.hallID)
            db.session.add(item)
            db.session.commit()
            flash(u'录入成功')
            return redirect(url_for('insert_session'))
        return render_template('session-insert.html', form=form)


@app.route('/edit_session', methods=['GET', 'POST'])
def edit_session():
    form = SessionForm()
    print(request.form.get('sessionID'))
    sessionID = request.form.get(('sessionID'))
    if sessionID is None:
        return render_template('session-update.html', form=form)
    sessID = request.form.get('sessionID')
    sess = Session.query.filter_by(sessionID=request.form.get('sessionID')).first()
    sess.sessionID = sessID
    sess.hallID = request.form.get('hallID')
    sess.movieID = request.form.get('movieID')
    sess.Date = request.form.get('Date')
    sess.price = request.form.get('price')
    db.session.commit()
    flash(u'已成功修改场次信息！')

    return render_template('session-update.html',
                           form=form)  # movieID=form.movieID, hallID=form.hallID, Date=form.Date,price = form.price)


@app.route('/edit_movie', methods=['GET', 'POST'])
def edit_movie():
    form = MovieForm()
    movieID = request.form.get(('movieID'))
    if movieID is None:
        return render_template('movie-update.html', form=form)
    movie = Movie.query.filter_by(movieID=request.form.get('movieID')).first()
    movie.actor = request.form.get('actor')
    movie.brief = request.form.get('brief')
    movie.movieName = request.form.get('movieName')
    movie.type = request.form.get('type')
    movie.grade = request.form.get('grade')
    movie.box = request.form.get('box')
    movie.OnTime = request.form.get('OnTime')
    print(request.form.get('OnTime'))
    db.session.commit()
    flash(u'已成功修改电影信息！')

    return render_template('movie-update.html', form=form)


@app.route('/search_movie', methods=['POST', 'GET'])
def searchMovie():
    form = MovieSearchForm()
    return render_template('movie-search.html', form=form)


@app.route('/search_session', methods=['POST', 'GET'])
def searchSession():
    form = SessionSearchFrom()
    return render_template('session-search.html', form=form)


@app.route('/search_comment', methods=['POST', 'GET'])
def searchComment():
    form = CommentSearchForm()
    return render_template('comment-search.html', form=form)


@app.route('/insert_session', methods=['POST', 'GET'])
def insertSession():
    form = SessionForm()
    print(form.validate_on_submit())
    return render_template('session-insert.html', form=form)


@app.route('/findcomment')
def getcomment():
    return render_template('comment-cearch.html')


@app.route('/deleteMovie', methods=['POST'])
def deleteMovie():
    form = MovieSearchForm()
    url = request.url
    print(find_parse('movieID', url))
    item = json.loads(request.data)
    movie = Movie.query.filter_by(movieID=item['movieID']).first()
    sessions = Session.query.filter_by(movieID=item['movieID']).all()
    for i in sessions:
        db.session.delete(i)
    db.session.delete(movie)
    db.session.commit()
    return render_template('movie-search.html', form=form)


@app.route('/deleteSession', methods=['POST'])
def deleteSession():
    form = SessionSearchFrom()
    item = json.loads(request.data)
    session = Session.query.filter_by(sessionID=item['sessionID']).first()
    db.session.delete(session)
    db.session.commit()
    return render_template('session-search.html', form=form)


if __name__ == '__main__':
    manager.run()
