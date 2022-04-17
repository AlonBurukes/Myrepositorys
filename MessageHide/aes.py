from Crypto.Cipher import AES
import base64

def add_16(par):
    if type(par)==str:
        par = par.encode()
    while len(par) % 16 != 0:
        par += b'\x00'
    return par
def aesencrypt(text,aes):
    text = add_16(text)
    encrypt_text = aes.encrypt(text)
    print(encrypt_text)
    return encrypt_text

def aesdecrypt(text,aes):
    decrypt_text = aes.decrypt(text)
    print(decrypt_text)
    return decrypt_text

if __name__ == '__main__':
    format='utf-8'
    method=AES.MODE_CBC
    iv = '1234567812345678'
    key=open('key.txt','r')
    keytext=key.read()
    key.close()
    print(keytext,type(keytext))
    aes = AES.new(add_16(keytext),method,iv.encode())
    en_text = aesencrypt('asgfdhgdfsr',aes)
    secretmsgstr = ''
    for i in en_text:
      print(type(bin(i)),bin(i),chr(i))
      secretmsgstr = secretmsgstr+chr(i)
    print(secretmsgstr)      
    print('密文:',en_text)
    output = open('en_text.txt','wb')
    output.write(secretmsgstr)
    output.close()
    print(type(en_text))
    aes2 = AES.new(add_16(keytext),method,iv.encode())
    print('明文:',aesdecrypt(en_text,aes2))
