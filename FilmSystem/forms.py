from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, SelectField, PasswordField
from wtforms.validators import DataRequired, EqualTo, Length


class Login(FlaskForm):
    account = StringField(u'账号', validators=[DataRequired()])
    password = PasswordField(u'密码', validators=[DataRequired()])
    submit = SubmitField(u'登录')



class ChangePasswordForm(FlaskForm):
    old_password = PasswordField(u'原密码', validators=[DataRequired()])
    password = PasswordField(u'新密码', validators=[DataRequired(), EqualTo('password2', message=u'两次密码必须一致！')])
    password2 = PasswordField(u'确认新密码', validators=[DataRequired()])
    submit = SubmitField(u'确认修改')


class EditInfoForm(FlaskForm):
    name = StringField(u'用户名', validators=[Length(1, 32)])
    submit = SubmitField(u'提交')


class MovieForm(FlaskForm):
    movieID = StringField(validators=[DataRequired(),Length(5)])
    actor = StringField(validators=[DataRequired(),Length(1,100)])
    brief = StringField(validators=[DataRequired(),Length(1,255)])
    time = StringField(validators=[DataRequired(),Length(1,4)])
    type = StringField(validators=[DataRequired(),Length(20)])
    grade = StringField(validators=[DataRequired(),Length(2)])
    OnTime =StringField(validators=[DataRequired(),Length(10)])
    box = StringField(validators=[DataRequired(),Length(10)])
    movieName = StringField(validators=[DataRequired(),Length(50)])
    submit = SubmitField(u'提交')

class SessionForm(FlaskForm):
    sessionID = StringField(validators=[DataRequired()])
    hallID = StringField(validators=[DataRequired()])
    movieID = StringField(validators=[DataRequired()])
    price = StringField(validators=[DataRequired()])
    Date = StringField(validators=[DataRequired()])
    submit = SubmitField(u'提交')
class CommentSearchForm(FlaskForm):
    movieName = StringField(validators=[DataRequired()])
    submit = SubmitField(u'搜索')

class MovieSearchForm(FlaskForm):
    methods = [('movieID', '电影编号'), ('movieName', '电影名'), ('movie_actor', '演员'), ('movie', '类型'),('all','全部')]
    method = SelectField(choices=methods, validators=[DataRequired()], coerce=str)
    content = StringField(validators=[DataRequired()])
    submit = SubmitField('搜索')
    spider = SubmitField('爬取')

class SessionSearchFrom(FlaskForm):
    movieName = StringField(validators=[DataRequired()])
    sessionID = StringField(validators=[DataRequired()])
    hallID = StringField(validators=[DataRequired()])
    movieID = StringField(validators=[DataRequired()])
    price = StringField(validators=[DataRequired()])
    Date = StringField(validators=[DataRequired()])
    submit = submit = SubmitField(u'搜索')
