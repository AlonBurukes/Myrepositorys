import hashlib
import random
import os
import numpy as np
from PIL import Image


PLS = []
img = Image.open(r"images/in1.png")
[row, col] = img.size

def bitstring_to_bytes(s):
    return int(s, 2).to_bytes((len(s) + 7) // 8, byteorder='big')

def DataListInBit(data):
    # dataBits = list(format(c, '08b') for c in bytearray(data.encode('latin-1')))
    dataBits = list(format(c, '08b') for c in data)
    return dataBits


def PLSgen(row, col, lenEncodedText):
    new = []
    for i in range(row * col):
        new.append(i)
    for i in range(len(new) - 1, 0, -1):
        j = random.randint(0, i + 1)
        new[i], new[j] = new[j], new[i]
    for i in range(lenEncodedText * 3):
        PLS.append(new[i])
    pixelLocaterSequence = np.array(PLS)
    np.savetxt("pls.txt", pixelLocaterSequence, delimiter="\t")


def LsbEncoding(encodedText):
    PLSgen(row, col, len(encodedText))
    dataBits = DataListInBit(encodedText)
    dr = 0
    for i in range(0, len(encodedText) * 3, 3):
        dc = 0
        for j in range(0, 3):
            rr = PLS[i + j] // col
            rc = PLS[i + j] % col
            rgb = img.getpixel((rr, rc))
            value = []
            idx = 0
            for k in rgb:
                if (k % 2 == 0 and dataBits[dr][dc] == '1'):
                    if (k == 0):
                        k += 1
                    else:
                        k -= 1
                if (k % 2 == 1 and dataBits[dr][dc] == '0'):
                    k -= 1
                value.append(k)
                idx += 1
                dc += 1
                if (dc >= 8):
                    break
            if (dc >= 8):
                value.append(rgb[2])
            newrgb = (value[0], value[1], value[2])
            img.putpixel((rr, rc), newrgb)
        dr += 1
    img.save("images/out1.png")


def LsbDecoding():
    pls = np.genfromtxt('pls.txt', delimiter='\t')
    decodedTextInBits = []
    stegoImage = Image.open(r"images/out1.png")
    for i in range(0, len(pls), 3):
        ithChar = ""
        for j in range(0, 3):
            rr = pls[i + j] // col
            rc = pls[i + j] % col
            rgb = stegoImage.getpixel((rr, rc))
            for k in rgb:
                if (k & 1):
                    ithChar += '1'
                else:
                    ithChar += '0'

        ithChar = ithChar[:-1]
        decodedTextInBits.append((ithChar))
    # decodedText = ''
    decodedText = bytes()
    for i in decodedTextInBits:
        decodedText+=bitstring_to_bytes(i)
        # decodedText += chr(int(i, 2))
    return decodedText
