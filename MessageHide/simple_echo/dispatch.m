clear; 
close all; 
clc;
path = 'armorer.WAV'
[x,FS]=audioread(path); % 将 WAV 文件转换成变量z
x1=x(:,1); % 抽取第 1 声道
x2=x(:,2); % 抽取第 2 声道
audiowrite('message.WAV',x1,FS); % 实现 1 声道分离
audiowrite('key.WAV',x2,FS); % 实现 2 声道分离
%如果合并位置不对前面补 
%声道长度不对后面补 
x12=x1+x2; % 两路单声道列向量矩阵变量合并
x12m=max(max(x12),abs(min(x12))), % 找出极值
y12=x12./x12m; % 归一化处理
%audiowrite('quit11.WAV',y12,FS); % 实现两路声道合并
%如果组合位置不对前面补 --声道长度不对后面补 
x3=[x1,x2]; % 两路单声道变量组合
%audiowrite('quit12.WAV',x3,FS); % 实现两路声道组合