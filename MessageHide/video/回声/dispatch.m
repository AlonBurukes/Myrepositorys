clear; 
close all; 
clc;
path = 'audio_origin.WAV'
[x,FS]=audioread(path); 
x1=x(:,1); % 抽取第 1 声道
x2=x(:,2); % 抽取第 2 声道
audiowrite('key.WAV',x1,FS);
audiowrite('message.WAV',x2,FS); % 实现 2 声道分离
