clear; 
close all; 
clc;
path = 'armorer.WAV'
[x,FS]=audioread(path); % �� WAV �ļ�ת���ɱ���z
x1=x(:,1); % ��ȡ�� 1 ����
x2=x(:,2); % ��ȡ�� 2 ����
audiowrite('message.WAV',x1,FS); % ʵ�� 1 ��������
audiowrite('key.WAV',x2,FS); % ʵ�� 2 ��������
%����ϲ�λ�ò���ǰ�油 
%�������Ȳ��Ժ��油 
x12=x1+x2; % ��·��������������������ϲ�
x12m=max(max(x12),abs(min(x12))), % �ҳ���ֵ
y12=x12./x12m; % ��һ������
%audiowrite('quit11.WAV',y12,FS); % ʵ����·�����ϲ�
%������λ�ò���ǰ�油 --�������Ȳ��Ժ��油 
x3=[x1,x2]; % ��·�������������
%audiowrite('quit12.WAV',x3,FS); % ʵ����·�������