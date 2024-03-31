"""
Documentation:

    1.The client script (client.py) connects to the server using the specified IP and port. 
    It sends user-input commands to the server and displays the server's response.

    2.The server script (server.py) listens on a specified port for incoming connections.
    It processes commands received from clients, reads or updates data in a JSON file (data.json),
    and sends back appropriate responses.

    3.Data is stored in a JSON file for simplicity. If the file doesn't exist, 
    the server initializes an empty data dictionary. The server saves data after each write operation.

    4.Error handling is implemented to handle file not found and invalid command scenarios.

    5.Both scripts are structured, readable, and include comments to explain major functions and components.
"""

"""
Instructions for Running:

    1.Save the client.py and server.py scripts in separate files.

    2.Create an empty file named data.json in the same directory as server.py.

    3.Run the server script (python server.py) in one terminal.

    4.Run the client script (python client.py) in another terminal.

    5.Enter commands on the client side, and observe the server responses.
"""

import socket   #used for network communication.
import json     # which allows working with data in JSON format.

"""
Name : read_data
In this function we performed this following work:

    1.This function tries to open a file named "data.json" in read mode ("r").

    2.If the file exists and can be opened successfully, it uses json.load(file) 
    to read the JSON data from the file and store it in a variable called data.

    3.If there's an error opening the file (either FileNotFoundError or a JSONDecodeError),
    it sets data to an empty dictionary ({}).

    4.Finally, the function returns the data dictionary.

:return : data dictionary. 
"""
def read_data():
    try:
        with open("data.json", "r") as file:
            data = json.load(file)
    except (FileNotFoundError, json.decoder.JSONDecodeError):
        data = {}
    return data

"""
Name : write_data
In this function we have follwoing performed:

    1.This function opens the file "data.json" in write mode ("w").
    2.It uses json.dump(data, file) to write the provided data dictionary into the JSON file.
"""

def write_data(data):
    with open("data.json", "w") as file:
        json.dump(data, file)

"""
Name : process_command
In this function :
    1.This function takes a string command as input.
    2.It starts by cleaning up the command string using strip() to remove leading/trailing whitespace and splits it by double quotes (") using split('")').
    3.It then checks if the command has at least 4 parts and if the first part (converted to lowercase using strip().lower()) is either "read" or "write". If not, it returns "Invalid command".
    4.If the command seems valid, it extracts the action (read or write), attribute name, and person name from the command parts.
    5.Based on the action:
        1.If action is "read":
            1.It calls read_data() to get the data.
            2.It tries to access the data for the specific person and attribute using nested dictionary lookups with get().
            3.If the attribute or person is not found, it sets the response to "Attribute not found".
        2.If action is "write":
            1.It checks if the command has at least 5 parts (including the value). If not, it returns "Invalid command".
            2.It extracts the value to be written.
            3.It calls read_data() to get the existing data.
            4.If the person doesn't exist in the data yet, it creates an empty dictionary for them.
            5.It updates the specific attribute for the person with the provided value in the data dictionary.
            6.It calls write_data(data) to save the updated data back to the JSON file.
            7.It sets the response to a success message indicating the attribute update.
    6.If the action is neither "read" nor "write", it sets the response to "Invalid command".
    7.Finally, the function returns the processed response string.
"""
def process_command(command):
    command_parts = command.strip().split('"')
    
    if len(command_parts) < 4 or command_parts[0].strip().lower() not in ['read', 'write']:
        return "Invalid command"

    action = command_parts[0].strip().lower()
    attribute = command_parts[1].strip()
    person_name = command_parts[2].strip()

    if action == 'read':
        data = read_data()
        response = data.get(person_name, {}).get(attribute, "Attribute not found")
    elif action == 'write':
        if len(command_parts) < 5:
            return "Invalid command"
        value = command_parts[3].strip()
        data = read_data()
        if person_name not in data:
            data[person_name] = {}
        data[person_name][attribute] = value
        write_data(data)
        response = f"Attribute '{attribute}' for '{person_name}' updated successfully"
    else:
        response = "Invalid command"

    return response

"""
Name : start_server
In this function:

enters loop (while True):
    1.It waits for a connection using server_socket.accept(). This method blocks until a client connects and returns a new socket object representing the connection and the address of the client.
    2.It enters a context manager block using with connection:. This ensures the connection is properly closed even if there are exceptions.
        1.It prints a message indicating a connection from the client's address.
        2.It receives data from the connection using connection.recv(1024). This receives at most 1024 bytes of data from the client and decodes it using decode() to get a string.
        3.If the received command is empty (not command), it breaks out of the inner loop, likely to close the connection.
        4.It calls process_command(command) to process the received command and get the response.
        5.It sends the response back to the client using `connection.sendall(
"""
def start_server():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:   #creates a server socket
        server_socket.bind(('localhost', 12345))    #binds the socket to the local host and port.
        server_socket.listen()    #socket to listen for incoming connections 

        print("Server is listening on port 12345...")

        while True:
            connection, address = server_socket.accept()
            with connection:
                print(f"Connected by {address}")
                command = connection.recv(1024).decode()
                if not command:
                    break
                response = process_command(command)
                connection.sendall(response.encode())

if __name__ == "__main__":
    start_server()
