# -*- coding: utf-8 -*-
import cv2
import operator
import numpy as np
import matplotlib.pyplot as plt
import sys
import os

print(sys.executable)
#Video path of the source file
videopath = sys.argv[1]
th = 0.8
dir = os.getcwd()

class Frame:
    def __init__(self, id, frame, value):
        self.id = id
        self.frame = frame
        self.value = value


def rel_change(a, b):
   if max(a,b) == 0:
    return 0
   else: 
    x = (b - a) / max(a, b)
    return x


print("Video :" + videopath)
print("Frame Directory: " + dir)


cap = cv2.VideoCapture(str(videopath))


curr_frame = None
prev_frame = None

frame_diffs = []
frames = []
ret, frame = cap.read()
i = 1

while(ret):
    luv = cv2.cvtColor(frame, cv2.COLOR_BGR2LUV)
    curr_frame = luv
    if curr_frame is not None and prev_frame is not None:
        diff = cv2.absdiff(curr_frame, prev_frame)
        count = np.sum(diff)
        frame_diffs.append(count)
        frame = Frame(i, frame, count)
        frames.append(frame)
    print('the %d frame'%i)
    prev_frame = curr_frame
    i = i + 1
    ret, frame = cap.read()

cap.release()
keyframetext = open('keyframe.txt','w')
for i in range(1, len(frames)):
    if (rel_change(np.float(frames[i - 1].value), np.float(frames[i].value)) >= th):
        name = "frame_" + str(frames[i].id) + ".png"
        print(name)
        keyframetext.write(name+'\n')
        #cv2.imwrite(dir + "/" + name, frames[i].frame)
keyframetext.close()