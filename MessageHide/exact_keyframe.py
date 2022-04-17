# -*- coding: utf-8 -*-

import cv2
import operator
import numpy as np
import matplotlib.pyplot as plt
import sys
from scipy.signal import argrelextrema
import os
print(sys.executable)
#Video path of the source file
videopath = sys.argv[1]
dir = os.getcwd()+'keyframe/'



def smooth(x, window_len=13, window='hanning'):
    print(len(x), window_len)
    if x.ndim != 1:
        raise ValueError("smooth only accepts 1 dimension arrays.")

    if x.size < window_len:
        raise ValueError("Input vector needs to be bigger than window size.")

    if window_len < 3:
        return x

    if not window in ['flat', 'hanning', 'hamming', 'bartlett', 'blackman']:
        raise ValueError("Window is on of 'flat', 'hanning', 'hamming', 'bartlett', 'blackman'")

    s = np.r_[2 * x[0] - x[window_len:1:-1],
              x, 2 * x[-1] - x[-1:-window_len:-1]]

    if window == 'flat':  # moving average
        w = np.ones(window_len, 'd')
    else:
        w = getattr(np, window)(window_len)
    y = np.convolve(w / w.sum(), s, mode='same')
    return y[window_len - 1:-window_len + 1]


class Frame:
    def __init__(self, id, frame, value):
        self.id = id
        self.frame = frame
        self.value = value

    def __lt__(self, other):
        if self.id == other.id:
            return self.id < other.id
        return self.id < other.id

    def __gt__(self, other):
        return other.__lt__(self)

    def __eq__(self, other):
        return self.id == other.id and self.id == other.id

    def __ne__(self, other):
        return not self.__eq__(other)


def rel_change(a, b):
   x = (b - a) / max(a, b)
   print(x)
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
    prev_frame = curr_frame
    i = i + 1
    ret, frame = cap.read()

cap.release()


print("Using Local Maxima")
diff_array = np.array(frame_diffs)
sm_diff_array = smooth(diff_array, len_window)
frame_indexes = np.asarray(argrelextrema(sm_diff_array, np.greater))[0]
for i in frame_indexes:
    name = "frame_" + str(frames[i - 1].id) + ".png"
    print(dir+name)
    #cv2.imwrite(dir + name, frames[i - 1].frame)


plt.figure(figsize=(40, 20))
plt.stem(sm_diff_array)
plt.savefig(dir + 'plot.png')
