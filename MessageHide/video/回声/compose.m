clear; 
close all; 
clc;
[x1,FS1]=audioread('key_hide.WAV');
[x2,FS2]=audioread('message_hide.WAV');
x3=[x1,x2]; % 两路单声道变量组合
audiowrite('audio_hide.WAV',x3,FS1); % 实现两路声道组合
