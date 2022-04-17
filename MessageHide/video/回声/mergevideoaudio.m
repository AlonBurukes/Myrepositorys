videoPath='video.mp4';

% ����Ϊ�˻�ȡ������Ϣ�����ײ������Ƿֿ�д��
movObj=VideoReader(videoPath);  % ��ȡ��Ƶ��Ϣ
[AUDIO,Fs]=audioread('audio_hide.WAV');% ��ȡ��Ƶ��Ϣ

audioFrameLen=round(size(AUDIO,1)./movObj.NumFrames);% ��ȡÿһ֡��Ӧ��Ƶ����

% ����Computer Vision Toolbox�ں���������Ƶ����
videoFWriter=vision.VideoFileWriter('result.avi','FrameRate',movObj.FrameRate);
videoFWriter.AudioInputPort=true; % ����������д������Ϊtrue

for k=1:movObj.NumFrames
    frame=read(movObj,k);      %��ȡÿһ֡
    
    % ��Ƶ��Ƶһ֡һ֡д��
    videoFWriter(frame,AUDIO((k-1)*audioFrameLen+1:k*audioFrameLen,:));
end

release(videoFWriter);%�ص���Ƶ����