function o = hide_simple(d0, d1, fragment, data, s, atten)
%function o = hide_simple(d0, d1, fragment, data, s, atten)
%d0:delay 0
%d1:delay 1  d1>d0
%fragment:fragment
%data:the information
%s:source voice
%atten:attenuation of echo
[row, col] = size(s);
if(row > col)
    s = s';
end

%���峤��
len = length(s);
%�����������s0,s1Ϊԭʼ��Ƶ��������ƶ�d0��d1λ������d0,d1��������0��1�Ļ����ӳ�
s0 = atten * [zeros(1, d0), s(1:len - d0)];%backward echo with delay 0
s1 = atten * [zeros(1, d1), s(1:len - d1)];%backward echo with delay 1

i = 0;
N = floor(len / fragment);    %����ֶ�����
lend = length(data);          %���ĳ���
o = s0;
for i = 0 : N - 1
    if((i + 1) > lend)
        bit = 0;
    else
        bit = data(i + 1);     %��Ƕ�������
    end
    if bit == 1
        st = i * fragment + 1;   %ȡ����i���ֶδ���o(st:ed)
        ed = (i + 1) * fragment;
        o(st : ed) = s1(st : ed);
    end
end
o = s + o;     %��ԭʼ����s���ص�����o