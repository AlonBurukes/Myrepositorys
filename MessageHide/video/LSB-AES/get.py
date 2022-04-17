import os
import wave
import argparse
from Crypto.Cipher import AES
parser = argparse.ArgumentParser()
parser.add_argument('-f', help='audiofile', dest='audiofile')
parser.add_argument('-k', help='key file', dest='key')
parser.add_argument('-o', help='output', dest='output')
args = parser.parse_args()
af = args.audiofile
key=args.key
output = args.output
arged = False
if af and key and output:
    arged = True
def help():
  print ('''usage: ExWave.py [-h] [-f AUDIOFILE] [-k KEY] [-m output]

optional arguments:
  -h, --help    show this help message and exit
  -f AUDIOFILE  Select Audio File''')
def add_16(par):
    if type(par)==str:
        par = par.encode()
    while len(par) % 16 != 0:
        par += b'\x00'
    return par
def aesencrypt(text,aes):
    text = add_16(text)
    encrypt_text = aes.encrypt(text)
    return encrypt_text

def aesdecrypt(text,aes):
    decrypt_text = aes.decrypt(text)
    #print(decrypt_text)
    return decrypt_text

def ex_msg(af,aes,output):
    if not arged:
      help()
    else:
        print ("Please wait...")
        waveaudio = wave.open(af, mode='rb')
        frame_bytes = bytearray(list(waveaudio.readframes(waveaudio.getnframes())))
        extracted = [frame_bytes[i] & 1 for i in range(len(frame_bytes))]
        string = "".join(chr(int("".join(map(str,extracted[i:i+8])),2)) for i in range(0,len(extracted),8)) 
        string = string.split("##")[0]
        bytelist = bytes(eval(string))
        print("the information inside is :",bytelist)
        print("the length of en_text%16 is ",len(bytelist))
        message_text=aesdecrypt(bytelist,aes)
        msg=''
        for i in message_text:
            msg=msg+chr(i)
        print("Your Secret Message is:"+msg)
        output = open(output,'wb')
        output.write(msg.encode())
        output.close()
        waveaudio.close()
        
method=AES.MODE_CBC
iv = ''
textfile = open(key,'r')
text=textfile.readlines()
keytext = text[0]
iv = text[1]
aes = AES.new(add_16(keytext),method,iv.encode())
ex_msg(af,aes,output)

