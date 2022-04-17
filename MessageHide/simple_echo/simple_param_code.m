function [bits,s] = simple_param_code(D0, D1, FRAG, ATTEN, msg)
%function [msg, bits, code1,code2, s, err] = simple_param_code(D0, D1, FRAG, ATTEN, msg)
%D0:delay 0
%D1:delay 1
%FRAG:fragment
%ATTEN:attenuation of echo


%return value
%bits:original bits
%s:the original  voice


bits = str2bit(msg);

%[s, fs] = audioread('test.wav');%audioread
[s, fs] = audioread('quit2.wav');%audioread 
s = s';    %������Ƶ
s_len = length(s);
t_len = length(bits) * FRAG;  %FRAGΪ��Ƶ�ֶγ���
%������Ƶ���Ȳ���ʱ���Զ��������峤��
if s_len < t_len
    multiple = floor(t_len / s_len);
    remainder = mod(t_len, s_len);
    tmp = [];
    for i = 1:multiple
        tmp = [tmp s];
    end;
    tmp = [tmp s(1:remainder)];
    s = tmp;
end;
    
%hide information���帺����Ƶ��Ϣ����
%bld = hide_simple(D0, D1, FRAG, bits, s, ATTEN);
[row, col] = size(s);
if(row > col)
    s = s';
end

%���峤��
len = length(s);
%�����������s0,s1Ϊԭʼ��Ƶ��������ƶ�d0��d1λ������d0,d1��������0��1�Ļ����ӳ�
s0 = ATTEN * [zeros(1, D0), s(1:len - D0)];%backward echo with delay 0
s1 = ATTEN * [zeros(1, D1), s(1:len - D1)];%backward echo with delay 1

i = 0;
N = floor(len / FRAG);    %����ֶ�����
lend = length(bits);          %���ĳ���
o = s0;
for i = 0 : N - 1
    if((i + 1) > lend)
        bit = 0;
    else
        bit = bits(i + 1);     %��Ƕ�������
    end
    if bit == 1
        st = i * FRAG + 1;   %ȡ����i���ֶδ���o(st:ed)
        ed = (i + 1) * FRAG;
        o(st : ed) = s1(st : ed);
    end
end
o = s + o;
if exist('hide.wav','file')
    delete('hide.wav');
end

%����������Ƶ�ļ�
audiowrite( 'hide2.wav',o, fs);
disp('hide2.wav is writen')