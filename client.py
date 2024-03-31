import socket   #used for network communication.

"""
Name : send_command

In This function handles sending a command to a server and receiving the response.
    1.It creates a TCP socket (socket.socket(socket.AF_INET, socket.SOCK_STREAM)) for communication.
    2.It connects the socket to the server running on the local machine ('localhost') at port 12345 (client_socket.connect(('localhost', 12345))).
    3.It sends the provided command string to the server in encoded form (client_socket.sendall(command.encode())).
    4.It receives up to 1024 bytes of data from the server (client_socket.recv(1024)) and decodes it back into a string (.decode()).
    5.It returns the server's response as a string.
"""
def send_command(command):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
        client_socket.connect(('localhost', 12345))
        client_socket.sendall(command.encode())
        response = client_socket.recv(1024).decode()
    return response

"""
Name: while loop

The while True loop creates a continuous interaction loop:
    1.It prompts the user to enter a command.
    2.If the user enters "exit" (case-insensitive), the loop breaks, ending the program.
    3.Otherwise, it calls the send_command function to send the entered command to the server and receives the response.
    4.It prints the server's response to the user (print(f"Server response: {response}")).
"""
while True:
    command = input("Enter command (e.g., 'Read \"Attribute\" \"Person Name\"' or 'Write \"Attribute\" \"Person Name\" \"Value\"'): ")
    if command.lower() == 'exit':
        break
    response = send_command(command)
    print(f"Server response: {response}")
