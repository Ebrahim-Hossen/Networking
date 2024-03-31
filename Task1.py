"""-------------------------------------------------------
Instruction: 
1. Firstly, check the JRE(Java Runtime Environment) in your system
and install the server program (SocketTest.exe).

2. After installation, you set the IP adress and port number and click to start listening.

3. Run this Task1.py program.

4. After run the program, you can see this program are connected to
the server and send a message to server. After that, you send message from server (<BTT?>,<END>,others) 
and show the instruction that you provided.
        1.For, <BTT?> case: Reply to the server the value of Battery voltage and current and this program
        show the performance of battery voltage and current.

        2.For, <END> case: Reply to the server with acknowledgement and stop the session.

        3.For, others case: Reply to the server Unknown Request.
-------------------------------------------------------"""


import socket #Used for socket programming to connect to the server.
import sys    #used for system-specific operations

#TSI-SP-084 Issue 1.0

#Section 2.4.2 Default TCP PORT
TCP_IP_PORT = 8007
#Section 2.4.5 Default UDP PORT
UDP_PORT = 10080
#Sign ID
SIGN_ID = "8"

ADN = "12344556"
CTD = "45678"


"""-------------------------------------------------------
Name: GetServerIP
This function listens to a given UDP Port passed as parameter
Once it receives a message, it will return the IP address of the sender

:param UDPPortNo: port no to listen to
:return: IP Address of the server
-------------------------------------------------------"""
def GetServerIP (UDPPortNo):
    serverAddress = '127.0.0.1'
     
    return serverAddress

def GetSignId():          #Returns a constant sign ID ("577-0402")
    signId = '577-0402'
    return signId


def GetStatus():         #Returns a constant status value ("82").
    status = '82'
    return status
"""-------------------------------------------------------
Name: GetBattLvl
This function is supposed to read the battery voltage and return its value as a string. 
In this program, I used a constant value ("15.52").


:return: battery voltage
-------------------------------------------------------"""

def GetBattLvl():
    battLvl = '15.52'
    return battLvl

"""-------------------------------------------------------
Name: GetCurrLvl
This function is supposed to read the electrical current level and return its value as a string. 
In this program, I used a constant value ("25.2").


:return: electrical current level
-------------------------------------------------------"""

def GetCurrLvl():
    currLvl = '25.2'
    return currLvl

def SendDataToServer(szasSrv, data):
    szasSrv.sendall(data.encode())


host = GetServerIP(UDP_PORT)  # Get TCP/IP Server Address
port = TCP_IP_PORT  # The port used by the server

szasSrv = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  #Creates a TCP socket object.
szasSrv.connect((host, port))       #Connects the socket to the server at the specified address and port.

firstMsg = GetSignId() + ";" + GetStatus()   #Creates a first message string with sign ID and status

print(firstMsg)

szasSrv.sendall(firstMsg.encode())  #Sends the first message to the server.

secondMsg = ADN + ";" + CTD + ";" + "82"   #Creates a second message include ADN and CTD.

print("Sending message:", secondMsg)
SendDataToServer(szasSrv, secondMsg)

session = True

"""-------------------------------------------------------
Name: While loop(Continuous Monitoring Loop)
In this loop continously receives data from the server, removes extra characters, and decodes it and finally
Handle the Server Requests and reply to the server request.


:return: reply the server request
-------------------------------------------------------"""
while session:
    data = szasSrv.recv(1024)   # Receives data from the server
    reply = "Error"

    data = data.strip().decode() # removes extra characters, and decodes it to string.
    
    print ("Request: ", data)  #Prints the received data (request from the server).
    match data:                #Handle Server Requests
        case "<BTT?>":         #<BTT?> case:
            battery_voltage = float(GetBattLvl())

            #events based on battery voltage
            if battery_voltage < 11.5:
                print("*Battery Voltage is low")
            elif battery_voltage > 13.5:
                print("*Battery Voltage is high")
            
                
            electrical_current = float(GetCurrLvl()) # Simulated function to get electrical current
    
            # event if electrical current threshold is crossed
            if electrical_current > 20.0:
                print("*Electrical Current Threshold is crossed")
            
            reply = f"<BATT voltage: {battery_voltage}>"   
            creply = f"<Electric current: {electrical_current}>"  
            szasSrv.sendall(reply.encode())     # Send battery voltage to server
            szasSrv.sendall(creply.encode())    # Send electrical_current to server

        case "<END>":        #<END> case:
            reply = "<ACK>"   #An acknowledgement message is prepared.
            szasSrv.sendall(reply.encode())
            session = False  # end the session.
            break   #loop breaks

        case _:     #_ case (default):
            reply = "Unknown Req"    #An unknown request message is prepared
            szasSrv.sendall(reply.encode())   #The reply message is sent to the server.

print("Session has ended")


# Wait for Message from Server
class SigncontrollerNum:
    def __init__(self, tag, response):
        self.tag = tag
        self.response = response

szasSrv.close()