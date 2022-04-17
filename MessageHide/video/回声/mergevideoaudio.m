videoPath='video.mp4';

% 但是为了获取更多信息更容易操作还是分开写了
movObj=VideoReader(videoPath);  % 获取视频信息
[AUDIO,Fs]=audioread('audio_hide.WAV');% 获取音频信息

audioFrameLen=round(size(AUDIO,1)./movObj.NumFrames);% 获取每一帧对应音频长度

% 调用Computer Vision Toolbox内函数创建视频对象
videoFWriter=vision.VideoFileWriter('result.avi','FrameRate',movObj.FrameRate);
videoFWriter.AudioInputPort=true; % 把允许声音写入设置为true

for k=1:movObj.NumFrames
    frame=read(movObj,k);      %获取每一帧
    
    % 视频音频一帧一帧写入
    videoFWriter(frame,AUDIO((k-1)*audioFrameLen+1:k*audioFrameLen,:));
end

release(videoFWriter);%关掉视频对象