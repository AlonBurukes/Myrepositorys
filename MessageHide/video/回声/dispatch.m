clear; 
close all; 
clc;
path = 'audio_origin.WAV'
[x,FS]=audioread(path); 
x1=x(:,1); % ��ȡ�� 1 ����
x2=x(:,2); % ��ȡ�� 2 ����
audiowrite('key.WAV',x1,FS);
audiowrite('message.WAV',x2,FS); % ʵ�� 2 ��������
