clear; 
close all; 
clc;
path = 'armorer.WAV'
[x1,FS1]=audioread('quit1_hide.WAV');
[x2,FS2]=audioread('hide2.WAV');
%x1=x(:,1);
%x2=x(:,2);
%audiowrite('quit1.WAV',x1,FS); % ʵ�� 1 ��������
%audiowrite('quit2.WAV',x2,FS); % ʵ�� 2 ��������
x12=x1+x2; % ��·��������������������ϲ�
%x12m=max(max(x12),abs(min(x12))), % �ҳ���ֵ
%y12=x12./x12m;
%Z=[x1;x2];
%audiowrite('quit11.WAV',Z,FS); % ʵ����·�����ϲ�
x3=[x1,x2]; % ��·�������������
audiowrite('armorer_hide.WAV',x3,FS1); % ʵ����·�������