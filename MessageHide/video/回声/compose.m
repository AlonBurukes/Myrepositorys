clear; 
close all; 
clc;
[x1,FS1]=audioread('key_hide.WAV');
[x2,FS2]=audioread('message_hide.WAV');
x3=[x1,x2]; % ��·�������������
audiowrite('audio_hide.WAV',x3,FS1); % ʵ����·�������
