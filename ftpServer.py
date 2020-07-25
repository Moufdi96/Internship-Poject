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

    def ftp_SAVE(self,line):
        print("movement saved")
        self.respond("200 Movement saved")

    def ftp_PLAY_MOVEMENT(self,line):
        print("movement started")
        self.respond("200 Movement started")
        print("____________")
        print(line)

authorizer = DummyAuthorizer()
authorizer.add_user("nao", "pepper", "/", perm="elradfmw")
handler = MyHandler
handler.authorizer = authorizer
server = FTPServer(("134.245.109.74", 1040), handler)
        #if(FTPHandler.commandFromServer == "ACTIVATE_ANIMATION_MODE"):
         #   memProx.raiseEvent("NewAction","ACTIVATE_ANIMATION_MODE")

print("__________________")
server.serve_forever()
