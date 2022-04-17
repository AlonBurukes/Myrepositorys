clear all;
close all;
clc;


atten = 0.7;%attenuation
D0 = 8;
D1 = 12;
FRAG = 512;
%msg = 'abcdefghijklmnopqrstuvwxyz0123456789'; 
msg = 'AESkeyis;123456789;ivis;123456;codeASCII';
%msg = 'test0123456789'; 
[bits, s] = simple_param_code(D0, D1, FRAG, atten, msg); 
simple_param_decode(D0, D1, FRAG, msg, bits);

%movefile('hide.wav', 'hide_no_noise.wav', 'f');
%passchannel(54, 'hide_no_noise.wav', 'hide.wav');%25
%simple_param_decode(D0, D1, FRAG, msg, bits);