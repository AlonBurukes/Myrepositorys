clear all;
close all;
clc;


atten = 0.9;%attenuation
D0 = 50;
D1 = 200;
FRAG = 2048*4;
msg = 'abcdefghijklmnopqrstuvwxyz0123456789'; 
%msg = 'AESkeyis;ThisisnotaAESkey;ivis;1234567812345678';
%msg = 'test0123456789'; 
[bits, s] = simple_param_code(D0, D1, FRAG, atten, msg); 
simple_param_decode(D0, D1, FRAG, msg, bits);

%movefile('hide.wav', 'hide_no_noise.wav', 'f');
%passchannel(54, 'hide_no_noise.wav', 'hide.wav');%25
%simple_param_decode(D0, D1, FRAG, msg, bits);