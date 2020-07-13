from pyftpdlib.authorizers import DummyAuthorizer
from pyftpdlib.handlers import FTPHandler
from pyftpdlib.servers import FTPServer


#class FTPHandler2 (FTPHandler):
    
   # def ftp_PLAY(self,line):
    #    self.respond("Playing movement")
        
authorizer = DummyAuthorizer()
authorizer.add_user("nao", "pepper", "C:\\", perm="elradfmw")
#authorizer.add_anonymous("/home/moufdi_taha", perm="elradfmw")

handler = FTPHandler
handler.authorizer = authorizer

server = FTPServer(("10.42.0.15", 1040), handler)
server.serve_forever()
