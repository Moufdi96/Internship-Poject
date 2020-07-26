from pyftpdlib.authorizers import DummyAuthorizer
from pyftpdlib.handlers import FTPHandler
from pyftpdlib.servers import FTPServer

class MyHandler(FTPHandler):
    def on_login(self, username):
        print("logged in")

    def on_logout(self, username):
        print("logged out")

    def on_disconnect(self):
        print("logged out")

    def ftp_ACTIVATE_ANIMATION_MODE(self,line):
        print("animation mode on")
        self.respond("200 Animation mode is on")
        #self.respond("200 Animation mode is on")

    def ftp_DEACTIVATE_ANIMATION_MODE(self,line):
        print("animation mode off")
        self.respond("200 Animation mode is off")

    def ftp_SAVE_MOVEMENT(self,line):
        print("movement saved")
        self.respond("200 Movement saved")

    def ftp_PLAY_MOVEMENT(self,line):
        print("movement started")
        print("movement id :")
        print(line)
        self.respond("200 Movement started")

    def ftp_DELETE_MOVEMENT(self,line):
        print("Movement delteted")
        print("movement id :")
        print(line)
        self.respond("200 Movement deleted")

authorizer = DummyAuthorizer()
authorizer.add_user("nao", "pepper", "/", perm="elradfmw")
handler = MyHandler
handler.authorizer = authorizer
server = FTPServer(("134.245.109.74", 1041), handler)
        #if(FTPHandler.commandFromServer == "ACTIVATE_ANIMATION_MODE"):
         #   memProx.raiseEvent("NewAction","ACTIVATE_ANIMATION_MODE")

print("__________________")
server.serve_forever()
