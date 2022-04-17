import cv2
import os
import numpy as np
from PIL import Image
def video2frame(videos_path,frames_save_path,time_interval):
 
  vidcap = cv2.VideoCapture(videos_path)
  success, image = vidcap.read()
  count = 0
  while success:
    success, image = vidcap.read()
    count += 1
    if count % time_interval == 0:
      cv2.imencode('.png', image)[1].tofile(frames_save_path + "/frame%d.png" % count)
      print("the %d frame" % count)
  print(count)

 
 
def frame2video(im_dir,video_dir,fps):
 
    im_list = os.listdir(im_dir)
    im_list.sort(key=lambda x: int(x.replace("frame","").split('.')[0]))  
    img = Image.open(os.path.join(im_dir,im_list[0]))
    img_size = img.size 
    fourcc = cv2.VideoWriter_fourcc(*'XVID') 
    videoWriter = cv2.VideoWriter(video_dir, fourcc, fps, img_size)
    # count = 1
    for i in im_list:
        im_name = os.path.join(im_dir,i)
        print(im_name)
        frame = cv2.imdecode(np.fromfile(im_name, dtype=np.uint8), -1)
        videoWriter.write(frame)
    videoWriter.release()
    print('finish')
 
if __name__ == '__main__':
   videos_path = 'video_hide.mp4'
   frames_save_path = os.getcwd()+'\\frame\\'
   time_interval = 24
   frame2video(frames_save_path,'video_hide.avi', time_interval)