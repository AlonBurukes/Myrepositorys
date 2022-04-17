import LSB as lsb
import zlib
import os
from Hamming import *


def main():
    select = input("Enter E for Encoding D for Decoding :")
    if select == 'E':
        if os.path.exists("pls.txt"):
            os.remove("pls.txt")
        if os.path.exists("images/out1.png"):
            os.remove("images/out1.png")

        if os.path.exists("images/in1.png"):
            #secretMessage = input("Enter the secret message :")
            secretMessage ='''I wake up to the end of the silence that allows. For my legs to run around with my legs up to the ground. I'm searching to behold the lies that are told. When my back is to the one that was smiling when I turned. Without you you're the greatest. But once you turn they hate us. Everybody wants to be my enemy. Your words up on the floor as you're praying for my fall. And the faces in the halls and the names that I've been hearing. I stack it in my mind and I'm praying for the time. When I show you what it's supposed to be words spit in a mic. Tell you you're the greatest. But once you turn they know us. Everybody needs to be my enemy. Okay, I'm hoping that somebody pray for me. I'm hoping that somebody hope for me. I'm going where nobody'posed to be. Ready to go whenever, just let me know. The road is clear, so put the pedal into the gas. The enemy's on my trail, my friends unavailable. I'ma tell'em hasta luego. They wanna plot on my trot to the end. I've been outta shape, thinkin'out the box, I'm an idiot. I blasted off the planet trying to cause catastrophe. And it matters more because I had it not. Had I thought about wreaking havoc on an opposition. Kinda shocking they get static with precision, I'm automatic Quarterback, I ain't talkin'sacking. Pack it, pack it up, I don't care. Batter, batter up, who the baddest? It don't matter'cause we at ya throat. Everybody wants to be my enemy I'll tell be a saint, no way. I'll idiot be a saint.'''
            encodedMessge = zlib.compress(secretMessage.encode())
            encodedMessge = encode(encodedMessge)
            lsb.LsbEncoding(encodedMessge)

        else : print("Image is not Present")



    if select == 'D':
        if os.path.exists("pls.txt"):
            decodedText = lsb.LsbDecoding()
            finalMessage = zlib.decompress(decodedText)
            finalMessage = decode(finalMessage)
            print("Final message :", finalMessage)
        else :
            print("PLS file is not present !")

main()
