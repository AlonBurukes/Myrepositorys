clear; 
close all; 
clc;
path = 'armorer.WAV'
[x1,FS1]=audioread('quit1_hide.WAV');
[x2,FS2]=audioread('hide2.WAV');
%x1=x(:,1);
%x2=x(:,2);
%audiowrite('quit1.WAV',x1,FS); % 实现 1 声道分离
%audiowrite('quit2.WAV',x2,FS); % 实现 2 声道分离
x12=x1+x2; % 两路单声道列向量矩阵变量合并
%x12m=max(max(x12),abs(min(x12))), % 找出极值
%y12=x12./x12m;
%Z=[x1;x2];
%audiowrite('quit11.WAV',Z,FS); % 实现两路声道合并
x3=[x1,x2]; % 两路单声道变量组合
audiowrite('armorer_hide.WAV',x3,FS1); % 实现两路声道组合