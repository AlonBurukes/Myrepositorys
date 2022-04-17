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

%载体长度
len = length(s);
%构造回声矩阵，s0,s1为原始音频整体向后移动d0或d1位，其中d0,d1代表密文0，1的回声延迟
s0 = atten * [zeros(1, d0), s(1:len - d0)];%backward echo with delay 0
s1 = atten * [zeros(1, d1), s(1:len - d1)];%backward echo with delay 1

i = 0;
N = floor(len / fragment);    %载体分段数量
lend = length(data);          %密文长度
o = s0;
for i = 0 : N - 1
    if((i + 1) > lend)
        bit = 0;
    else
        bit = data(i + 1);     %待嵌入的密文
    end
    if bit == 1
        st = i * fragment + 1;   %取出第i个分段存入o(st:ed)
        ed = (i + 1) * fragment;
        o(st : ed) = s1(st : ed);
    end
end
o = s + o;     %在原始载体s中重叠回声o