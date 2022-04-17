import wave
import argparse
from Crypto.Cipher import AES

parser = argparse.ArgumentParser()
parser.add_argument('-f', help='Select Audio File', dest='audiofile')
parser.add_argument('-k', help='Enter your key file', dest='key')
parser.add_argument('-o', help='Your Output file path and name', dest='outputfile')
parser.add_argument('-m', help='Your message path and name', dest='file')
args = parser.parse_args()
af = args.audiofile
key = args.key
output = args.outputfile
messagefile = args.file
arged = False
if af and key and output:
    arged = True
def help():
  print ('''usage: HiddenWave.py [-h] [-f AUDIOFILE] [-k Key] [-o OUTPUTFILE] [-m MESSAGE]

optional arguments:
  -h, --help    show this help message and exit
  -f AUDIOFILE  Select Audio File
  -k Keyfile  Enter your keyfile
  -o OUTPUTFILE Your output file path and name
  -m Your message path and name''') 
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

def em_audio(af, string, output,aes):
    if not arged:
      help()
    else:
      waveaudio = wave.open(af, mode='rb')
      frame_bytes = bytearray(list(waveaudio.readframes(waveaudio.getnframes())))
      string = string + int((len(frame_bytes)-(len(string)*8*8))/8) *'#'
      bits = list(map(int, ''.join([bin(ord(i)).lstrip('0b').rjust(8,'0') for i in string])))
      for i, bit in enumerate(bits):
        frame_bytes[i] = (frame_bytes[i] & 254) | bit
      frame_modified = bytes(frame_bytes)
      with wave.open(output, 'wb') as fd:
        fd.setparams(waveaudio.getparams())
        fd.writeframes(frame_modified)
      waveaudio.close()
      return string
if __name__=='__main__':
    format='utf-8'
    method=AES.MODE_CBC
    iv = ''
    textfile = open(key,'r')
    text=textfile.readlines()
    keytext = text[0]
    iv = text[1]
    print('key is :',keytext)
    print('initia vector is :',iv)
    #print(keytext,type(keytext))
    message = open(messagefile,'r')
    aes = AES.new(add_16(keytext),method,iv.encode())
    
    en_text =aesencrypt(message.read(),aes)      
    print('en_text:',en_text)
    print('length of en_text is',len(en_text))
    secretmsgstr = []
    for i in en_text:
        secretmsgstr.append(int(i))
    em_audio(af, str(secretmsgstr), output,aes)
    aes2 = AES.new(add_16(keytext),method,iv.encode())
    print('message:',aesdecrypt(en_text,aes2))
    output = open('en_text.txt','wb')
    output.write(en_text)
    output.close()
    message.close()
