function out = dh_simple(d0, d1, fragment, in)
%out = dh_simple(d0, d1, fragment, in)
%decode using cepstrum
%d0:delay 0
%d1:delay 1  d1>d0
%fragment:fragment
%in:涉密载体

[row, col] = size(in);
if(row > col)
    in = in';
end;

i = 0;
out = [];
N = floor(length(in) / fragment);  %涉密载体分段数量
for i = 0 : N - 1
    st = i * fragment + 1;
    ed = (i + 1) * fragment;
    p = in(st : ed);               %取出涉密分段到p
    %p  = ifft(log(fft(p)).^2); 
    %p  = ifft(log(fft(p)));  
    p = MyCeps(p);                 %计算倒谱系数
    %p1 = MyCeps(p); 
    %p2  = ifft(log(fft(p )).^2); 
    %fprintf('size of p1 is: %d\n',size(p1,2));
    %fprintf('size of p2 is: %d\n',size(p2,2));
    %size(p):1*2048
    %p=p2;
    d11 = p(1 * d1 + 1);
    d01 = p(1 * d0 + 1);
    if d11 > d01
        out(i + 1) = 1;
    else
        out(i + 1) = 0;
    end;
end;
%fprintf('size of p is %d,%d\n',size(p));
%fprintf('size of p2 is %d,%d \n',size(p2));
%fprintf('fragment is %d\n',fragment);
%writefile('p.txt',p);
%writefile('p2.txt',p2);
%max(p-p1)

end

function writefile(strname,p)
fid = fopen(strname,'w');
fprintf(fid, '%d ', p(1,:));
fclose(fid);
end