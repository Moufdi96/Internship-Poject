import socket
import threading
import time

class TCPThread (threading.Thread):
    
    def __init__(self):
        threading.Thread.__init__(self)
        self.hote = ''
        self.port = 1552
        self.cnxStatus = False
     
        
    def run(self):
        self.connexion_principale = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.connexion_principale.bind((self.hote, self.port))
        print("Le serveur écoute à présent sur le port {}".format(self.port))
        self.connexion_principale.listen(2)
        self.connexion_avec_client, infos_connexion = self.connexion_principale.accept()
        print(infos_connexion)
        self.cnxStatus = True

    def getConnextion(self):
        return self.connexion_avec_client, self.connexion_principale

    def getConnectionStatus(self):
        return self.cnxStatus
    
    def setStopThread(self, stopThread):
    	self.stopThread = stopThread


tcpThread = TCPThread()
tcpThread.start()
time.sleep(1)

msg_recu = b""

while(1):
    if (tcpThread.getConnectionStatus()):
        connexion_avec_client, connexion_principale = tcpThread.getConnextion()
        break
    
while msg_recu != b"fin\n":
   # msg_a_envoyer = input(">")
 # msg_a_envoyer = msg_a_envoyer.encode()
    #f = file.read()
    #s = f.encode()
   # connexion_avec_client.send(msg_a_envoyer)
    msg_recu = connexion_avec_client.recv(1024)
    # L'instruction ci-dessous peut lever une exception si le message
    # Réceptionné comporte des accents
    print(msg_recu.decode())
   
print("Fermeture de la connexion")
connexion_avec_client.close()
connexion_principale.close()


